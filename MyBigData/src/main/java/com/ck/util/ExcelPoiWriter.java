package com.ck.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Chengk
 * @date 2018-11-02
 */
public class ExcelPoiWriter {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExcelPoiWriter.class);
	
	private OutputStream os;
	
	private HSSFWorkbook book;
	
	private boolean closeStream = true;
	
	private static final boolean BY_STREAM = true;
	
	public void createBook(String excelName, String path) {
		createBook(path + File.separator + excelName);
	}
	
	public void createBook(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			try {
				if(!file.createNewFile()) {
					LOG.warn("Excel文件创建失败，原因为该文件已经存在！");
				}
			} catch (IOException e) {
				LOG.error("Excel文件创建失败,失败原因：{}", e.getMessage());
			}
		}
		createBook(file);
	}
	
	public void createBook(File file) {
		try {
			createBook(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			LOG.error("Excel文件流获取失败,失败原因：{}", e.getMessage());
		}
	}
	
	public void createBook(OutputStream os) {
		this.closeStream = BY_STREAM;
		this.os = os;
		if(null==book) {
			book = new HSSFWorkbook();
		}
	}
	
	public void flush() {
		try {
			book.write(os);
		} catch (IOException e) {
			LOG.error("Excel文件流写出失败,失败原因：{}", e.getMessage());
		}
	}
	
	/**
	 * 关闭工作簿
	 */
	public void closeBook() {
		flush();
		if(closeStream&&null!=os) {
			try {
				os.close();
			} catch (IOException e) {
				LOG.error("Excel文件流关闭失败,失败原因：{}", e.getMessage());
			}
		}
	}
	
	/**
	 * 创建表单
	 * @param name
	 * @param headers
	 * @param contents
	 * @return 如果表单已经存在，则返回false
	 */
    public boolean createSheet(String name,String[][] headers,String [][] contents){
    	if(checkSheetName(name)) {
    		book.createSheet(name);
    		if(null!=headers) {
    			addHeaders(name, headers, 0, 0);
    		}
            if(null!=contents) {
            	addContentsByRow(name, contents, 0, headers.length, getDefaultContentStyle(), true);
            }
            return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * 添加一个表头
     * @param name
     * @param header
     * @param colStart
     * @param rowStart
     * @param colEnd
     * @param rowEnd
     */
    public void addOneHeader(String name, String header, int colStart, int rowStart, int colEnd, int rowEnd) {
    	HSSFSheet sheet = book.getSheet(name);
    	for(int i = rowStart;i<=rowEnd;i++) {
    		HSSFRow row = null;
    		row = sheet.getRow(i);
    		if(null==row) {
    			row = sheet.createRow(i);
    		}
    		for(int j = colStart;j<=colEnd;j++) {
    			HSSFCell cell = row.createCell(j);
    			cell.setCellValue(header);
    			cell.setCellStyle(getDefaultHeaderStyle());
    		}
    	}
    	if(colStart!=colEnd||rowStart!=rowEnd) {
    		mergeRange(name, colStart, rowStart, colEnd, rowEnd);
    	}
    }
    
    /**
     * 添加表头(自动合并相同表头)
     * @param name
     * @param contents
     * @param colStart
     * @param rowStart
     */
    public void addHeaders(String name, String[][] contents, int colStart, int rowStart) {
    	addContentsByRow(name, contents, colStart, rowStart, getDefaultHeaderStyle(), true);
    	autoMerge(name, contents, colStart, rowStart);
    }
    
    /**
     * 自动合并内容相同的单元格(目前仅支持横向合并)
     * @param name
     * @param contents
     * @param colStart
     * @param rowStart
     */
    private void autoMerge(String name, String[][] contents, int colStart, int rowStart) {
    	String temp = null;
    	for(int i = 0;i<contents.length;i++) {
    		int mergeStart = colStart;
        	int mergeEnd = colStart;
        	temp = null;
    		for(int j = 0;j<contents[i].length;j++) {
    			if(temp!=null&&StringUtils.isNotBlank(contents[i][j])&&temp.equals(contents[i][j])) {
    				mergeEnd = colStart+j;
    			}else {
    				if(mergeEnd>mergeStart) {
    					mergeRange(name, mergeStart, rowStart+i, mergeEnd, rowStart+i);
    				}
    				temp = contents[i][j];
    				mergeStart = colStart+j;
    			}
    		}
    		if(mergeEnd>mergeStart) {
				mergeRange(name, mergeStart, rowStart+i, mergeEnd, rowStart+i);
			}
    	}
		
	}

	/**
	 * 横向添加内容
	 * @param name
	 * @param contents
	 * @param colStart
	 * @param rowStart
	 * @param style
	 * @param fitWidth 宽度自适应
	 */
    private void addContentsByRow(String name, String[][] contents, int colStart, int rowStart, HSSFCellStyle style, boolean fitWidth) {
    	HSSFSheet sheet = book.getSheet(name);
    	Map<Integer, Integer> colMaxWidths = new HashMap<>();
    	for(int i = 0;i<contents.length;i++) {
    		String[] rowContents = contents[i];
    		HSSFRow row = null;
    		row = sheet.getRow(rowStart+i);
    		if(null==row) {
    			row = sheet.createRow(rowStart+i);
    		}
    		for(int j = 0;j<rowContents.length;j++) {
    			Integer temp = colMaxWidths.get(colStart+j);
    			if(null==temp) {
    				colMaxWidths.put(colStart+j, rowContents[j].getBytes().length);
    			}else {
    				if(temp<rowContents[j].getBytes().length) {
    					colMaxWidths.put(colStart+j, rowContents[j].getBytes().length);
    				}
    			}
    			HSSFCell cell = row.createCell(colStart+j);
    			cell.setCellValue(rowContents[j]);
    			cell.setCellStyle(style);
    		}
    	}
    	if(fitWidth) {
    		fitWidth(sheet, colMaxWidths);
    	}
    }
    
    /**
     * 调整宽度
     * @param colMaxWidths
     */
    private void fitWidth(HSSFSheet sheet, Map<Integer, Integer> colMaxWidths) {
    	Set<Entry<Integer, Integer>> set = colMaxWidths.entrySet();
    	for(Entry<Integer, Integer> e : set) {
    		int width = sheet.getColumnWidth(e.getKey());
    		if(e.getValue()*2*256>width) {
    			sheet.setColumnWidth(e.getKey(), e.getValue()*2*256);
    		}
    	}
	}

	/**
     * 纵向添加内容
     * @param name
     * @param contents
     * @param colStart
     * @param rowStart
     * @param style
     * @param fitWidth 宽度自适应
     */
    private void addContentsByCol(String name, String[][] contents, int colStart, int rowStart, HSSFCellStyle style, boolean fitWidth) {
    	HSSFSheet sheet = book.getSheet(name);
    	List<HSSFRow> rows = new ArrayList<>();
    	Map<Integer, Integer> colMaxWidths = new HashMap<>();
    	for(int i = 0;i<contents.length;i++) {
    		String[] colContents = contents[i];
    		for(int j = 0;j<colContents.length;j++) {
    			Integer temp = colMaxWidths.get(colStart+i);
    			if(null==temp) {
    				colMaxWidths.put(colStart+i, colContents[j].getBytes().length);
    			}else {
    				if(temp<colContents[j].getBytes().length) {
    					colMaxWidths.put(colStart+i, colContents[j].getBytes().length);
    				}
    			}
    			HSSFRow row = null;
    			if(rows.size()<=j) {
    				row = sheet.getRow(rowStart+j);
    				if(null==row) {
    					row = sheet.createRow(rowStart+j);
    				}
    				rows.add(row);
    			}else {
    				row = rows.get(j);
    			}
    			HSSFCell cell = row.createCell(colStart+i);
    			cell.setCellValue(colContents[j]);
    			cell.setCellStyle(style);
    		}
    	}
    	if(fitWidth) {
    		fitWidth(sheet, colMaxWidths);
    	}
    }
    
    /**
     * 单元格合并
     * @param name
     * @param colStart
     * @param rowStart
     * @param colEnd
     * @param rowEnd
     */
    public void mergeRange(String name, int colStart, int rowStart, int colEnd, int rowEnd) {
    	HSSFSheet sheet = book.getSheet(name);
    	sheet.addMergedRegion(new CellRangeAddress(rowStart,rowEnd,colStart,colEnd));
    }
    
    /**
     * 获取默认的标题样式
     * @return
     */
    private HSSFCellStyle getDefaultHeaderStyle() {
    	HSSFCellStyle style = book.createCellStyle();
    	style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    	HSSFFont font = book.createFont();
    	font.setFontHeightInPoints((short) 11);
        font.setBold(true);
        font.setFontName("ARIAL");
        style.setFont(font);
		return style;
	}
    
    /**
     * 获取默认的内容样式
     * @return
     */
    private HSSFCellStyle getDefaultContentStyle() {
    	HSSFCellStyle style = book.createCellStyle();
    	style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    	return style;
    }

    /**
     * 校验表单名称
     * @param sheetName
     * @return
     */
	private boolean checkSheetName(String sheetName) {
		HSSFSheet sheet = book.getSheet(sheetName);
		return null==sheet;
	}
	
	/**
	 * 添加一列
	 * @param sheetName
	 * @param colIndex
	 * @param rowIndex
	 * @param header
	 * @param colValues
	 */
	public void addCol(String name, int colStart, int rowStart, String header, List<Object> colValues) {
		addOneHeader(name, header, colStart, rowStart, colStart, rowStart);
		Object[] objs = colValues.toArray();
		String[][] contents = new String[1][objs.length];
		for(int i = 0;i<objs.length;i++) {
			contents[0][i] = String.valueOf(objs[i]);
		}
		addContentsByCol(name, contents, colStart, rowStart+1, getDefaultContentStyle(), true);
	}
	
	/**
	 * 获取单元格引用的字符串形式
	 * @param sheetName
	 * @param col
	 * @param row
	 * @param colAbsolute 列是否相对引用
	 * @param rowAbsolute 行是否相对引用
	 * @return
	 */
	public String getCellReference(String sheetName, int col, int row, boolean colAbsolute, boolean rowAbsolute) {
		return new CellReference(sheetName, row, col, rowAbsolute, colAbsolute).formatAsString();
	}
	
	/**
	 * 获取单元格区域引用
	 * @param sheetName
	 * @param colStart
	 * @param rowStart
	 * @param colEnd
	 * @param rowEnd
	 * @param colAbsolute
	 * @param rowAbsolute
	 * @return
	 */
	public String getAreaReference(String sheetName, int colStart, int rowStart,int colEnd, int rowEnd, boolean colAbsolute, boolean rowAbsolute) {
		CellReference start = new CellReference(sheetName, rowStart, colStart, rowAbsolute, colAbsolute);
		CellReference end = new CellReference(sheetName, rowEnd, colEnd, rowAbsolute, colAbsolute);
		return new AreaReference(start, end).formatAsString();
	}
	
	/**
	 * 创建命名区域
	 * @param name
	 * @param referenceRange
	 * @return 名称重复时，返回false
	 */
	public boolean createName(String name, String referenceRange) {
		if(null==book.getName(name)) {
			HSSFName newName = book.createName();
			newName.setNameName(name);
			newName.setRefersToFormula(referenceRange);
		}
		return false;
	}
	
	/**
	 * 通过公式添加区域验证
	 * @param name
	 * @param colStart
	 * @param rowStart
	 * @param colEnd
	 * @param rowEnd
	 * @param formula
	 */
	public void addValidationByFormula(String name, int colStart, int rowStart, int colEnd, int rowEnd, String formula) {
		HSSFSheet sheet = book.getSheet(name);
		CellRangeAddressList regions = new CellRangeAddressList(rowStart, rowEnd, colStart, colEnd);
		DVConstraint constraint = DVConstraint.createFormulaListConstraint(formula);
		DataValidation validation = new HSSFDataValidation(regions, constraint);
		validation.setSuppressDropDownArrow(false);
		validation.createErrorBox("错误提示", "请从下拉列表中选择，不要随意输入！");
		sheet.addValidationData(validation);
	}
	
	/**
	 * 隐藏表单
	 * @param name
	 * @return 找不到表单，返回false
	 */
	public boolean hiddenSheet(String name) {
		if(null!=book.getSheet(name)) {
			book.setSheetHidden(book.getSheetIndex(name), true);
		}
		return false;
	}
	
	public static void main(String[] args) {
		ExcelPoiWriter writer = new ExcelPoiWriter();
		writer.createBook("D:\\app_temp\\out.xls");
		String[][] headers = new String[][] {{"表头1", "表头1", "表头2","表头1", "表头2","表头1", "表头1","表头1", "表头2"},{"表头1", "表头1"},{"表头1", "表头1", "表头2","表头1", "表头2","表头1", "表头1","表头1", "表头2", "表头2"}};
		String[][] contents = new String[][] {{"德","b","c"},{"智","e","f"}};
		writer.createSheet("新表单", headers, contents);
		writer.createSheet("表单2", headers, contents);
		List<Object> vals = new ArrayList<>();
		vals.add("值1");
		vals.add("值2");
		writer.addCol("新表单", 5, 5, "新增列", vals);
		String range = writer.getAreaReference("表单2", 0, 0, 0, 2, true, true);
		writer.createName("德",range);
		String range2 = writer.getAreaReference("表单2", 0, 3, 0, 4, true, true);
		writer.createName("智",range2);
		String validRange = writer.getAreaReference("新表单", 0, 3, 0, 4, true, true);
		writer.addValidationByFormula("新表单", 10, 5, 10, 5, validRange);
		String validCell = writer.getCellReference("新表单", 10, 5, true, true);
		writer.addValidationByFormula("新表单", 10, 6, 10, 6, "INDIRECT("+validCell+")");
		writer.hiddenSheet("表单2");
		writer.closeBook();
	}
}
