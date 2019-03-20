<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的计划</title>
<link rel="stylesheet" href="static/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="static/css/myPlan.css">
<script type="text/javascript" src="static/vue/dist/vue.js"></script> 
<script type="text/javascript" src="static/element-ui/lib/index.js"></script>
<script type="text/javascript" src="static/js/jquery.js"></script>
</head>
<body>
	<div id="app">
		<el-container>
			<el-header style="text-align: right; font-size: 12px">
				<div class="head-button-group">
					<el-date-picker
				      v-model="dateRange"
				      type="daterange"
				      range-separator="至"
				      start-placeholder="开始日期"
				      end-placeholder="结束日期">
				    </el-date-picker>
				    <el-select v-model="filter.selectState" @change="getPlanItems" clearable placeholder="请选择" class="state-select">
					    <el-option
					      v-for="item in finishValues"
					      :key="item.value"
					      :label="item.label"
					      :value="item.value">
					    </el-option>
					</el-select>
					<el-input size="large" class="select-input"
					  placeholder="请输入内容"
					  v-model="filter.selectKey"
					  clearable>
					</el-input>
					<el-button icon="el-icon-search" circle @click="getPlanItems"></el-button>
					<el-button class="item-add-button" size="medium" type="success" round 
					@click="addItem">新增计划项</el-button>
				</div>
			</el-header>
		  	<el-container v-loading='tableLoading'>
			    <el-table :data="planData" show-overflow-tooltip="true" stripe height="450" style="width: 100%">
			      	<el-table-column type="expand">
			      		<template slot-scope="props">
							<label>备注:&nbsp&nbsp</label><span>{{props.row.finishDescr}}</span>
			      		</template>
				    </el-table-column>
			      	<el-table-column prop="createDate" label="日期" width="100"></el-table-column>
			     	<el-table-column prop="itemContent" label="内容" width="300"></el-table-column>
			      	<el-table-column prop="planTime" label="计划时间" width="170"></el-table-column>
			      	<el-table-column prop="finishTime" label="完成时间" width="170"></el-table-column>
			      	<el-table-column prop="finishType" label="完成情况" :formatter="formatFinishState"></el-table-column>
			    	<el-table-column label="操作">
    				</el-table-column>
			    </el-table>
			</el-container>
			<el-footer>
			    <el-pagination
			      @size-change="handleSizeChange"
			      @current-change="handleCurrentChange"
			      :current-page="filter.page"
			      :page-sizes="[50, 100, 200, 300, 400]"
			      :page-size="filter.pageSize"
			      layout="total, sizes, prev, pager, next, jumper"
			      :total="planItemNum">
			    </el-pagination>
			</el-footer>
		</el-container>
		<el-dialog
		  title="新增计划项"
		  :visible.sync="addItemDialog"
		  width="35%" :close-on-click-modal="false" :close-on-press-escape="false"
		  center>
		  <el-form ref="Form" :model="itemForm" label-width="80px" size="medium">
			  <el-form-item label="计划日期">
			    <el-col :span="11">
			      <el-date-picker type="date" style="width:150px;" placeholder="选择日期" 
			      v-model="planDate"></el-date-picker>
			    </el-col>
			  </el-form-item>
			  <el-form-item label="计划开始">
			    <el-col :span="11">
			      <el-date-picker type="datetime" style="width:200px;" placeholder="选择时间" 
			      v-model="itemForm.startTime"></el-date-picker>
			    </el-col>
			  </el-form-item>
			  <el-form-item label="计划完成" >
			    <el-col :span="11">
			      <el-date-picker type="datetime" style="width:200px;" placeholder="选择时间" 
			      v-model="itemForm.planTime"></el-date-picker>
			    </el-col>
			  </el-form-item>
			  <el-form-item label="开启提醒">
			    <el-switch v-model="itemForm.remindOn" active-value="1" inactive-value="0"></el-switch>
			  </el-form-item>
			  <el-form-item label="计划内容" style="width:300px;">
			    <el-input type="textarea" v-model="itemForm.itemContent"></el-input>
			  </el-form-item>
		</el-form>
			<span slot="footer" class="dialog-footer">
			    <el-button @click="addItemDialog = false">取 消</el-button>
			    <el-button type="primary" @click="createSubmit">创建</el-button>
			</span>
		</el-dialog>
		<el-dialog
		  :title="confirmMsg"
		  :visible.sync="confirmDialog"
		  width="30%" center>
		  <el-input type="textarea" v-model="itemDescr"></el-input>
		  <span slot="footer" class="dialog-footer">
		    <el-button @click="confirmDialog = false">取 消</el-button>
		    <el-button type="primary" @click="doUpdateState">确 定</el-button>
		  </span>
		</el-dialog>
	</div>
	<script type="text/javascript" src="static/js/common_util.js"></script>
	<script type="text/javascript" src="static/js/plan_item_list.js"></script>
</body>
</html>