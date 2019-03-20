package com.ck.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ck.annotation.Enums;
/**
 * 枚举值获取类
 * @author Chengk
 */
@RestController
@RequestMapping("/enums/")
public class EnumsController {

	private static final Log logger = LogFactory.getLog(EnumsController.class);

	/**
	 * 基本路径
	 */
	private static final String BASE_PATH = "com/ck/enums";
	/**
	 * 枚举类扫描路径
	 */
	private static final String ENUMS_PACKAGE = "classpath*:com/ck/enums/*.class";
	/**
	 * 枚举集合映射
	 */
	protected static Map<String, List<Map<String,Object>>> enumMap = new HashMap<>();

	/**
	 * 加载枚举值到map
	 */
	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initEnums() {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource[] resources;
		try {
			resources = resourcePatternResolver.getResources(ENUMS_PACKAGE);
			for (Resource resource : resources) {
				String uri = resource.getURI().toString();
				String className = uri.substring(uri.indexOf(BASE_PATH),uri.length()-6).replace('/', '.');
				Class cls = Class.forName(className);
				if(cls.isAnnotationPresent(Enums.class)){
					String enumName = ((Enums)cls.getAnnotation(Enums.class)).value();
					Class<Enum> clzz = (Class<Enum>)cls;
					Enum[] enumConstants = clzz.getEnumConstants();
					Method getCode = clzz.getMethod("getCode");
					Method getLabel = clzz.getMethod("getLabel");
					List<Map<String, Object>> list = new ArrayList<>();
					for (Enum enumEntry : enumConstants) {
						Map<String,Object> tempMap = new HashMap<>();
						tempMap.put("label", getLabel.invoke(enumEntry));
						tempMap.put("value", getCode.invoke(enumEntry));
						list.add(tempMap);
					}
					enumMap.put(enumName, list);
				}
			}
		} catch (Exception e) {
			logger.error("初始化枚举类异常！", e);
		}
	}
	
	/**
	 * 根据枚举类id查询枚举值
	 * @param id
	 * @return
	 */
	@GetMapping(value="{id}")
	public Object getEnum(@PathVariable String id) {
		return enumMap.get(id);
	}
	
}
