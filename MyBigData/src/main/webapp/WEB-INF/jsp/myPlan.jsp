<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的计划</title>
<link rel="stylesheet" href="static/css/element-ui.css">
<link rel="stylesheet" href="static/css/myPlan.css">
<script type="text/javascript" src="static/js/vue.js"></script> 
<script type="text/javascript" src="static/js/element-ui.js"></script>
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
				    <el-select v-model="filter.selectState" @change="getPlanDatas" clearable placeholder="请选择" class="state-select">
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
					<el-button icon="el-icon-search" circle @click="getPlanDatas"></el-button>
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
				      <template slot-scope="scope">
				      	<el-button v-for="item in stateBtns" :type="item.color" size="mini" :icon="item.ico" circle 
				        @click="updateState(item.value,scope.$index,scope.row)"></el-button>
				      </template>
    				</el-table-column>
			    </el-table>
			</el-container>
			<el-footer>
			    <el-pagination
			      @size-change="handleSizeChange"
			      @current-change="handleCurrentChange"
			      :current-page="filter.page"
			      :page-sizes="[50, 100, 200, 300, 400]"
			      :page-size="filter.size"
			      layout="total, sizes, prev, pager, next, jumper"
			      :total="totalPlan">
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
	<script type="text/javascript">
		//移到公共插件中去
		String.prototype.replaceAll = function(s1,s2){ 
			return this.replace(new RegExp(s1,"gm"),s2); 
			}
		Date.prototype.format = function (fmt) {
		    var o = {
		        "M+": this.getMonth() + 1, 
		        "d+": this.getDate(),
		        "h+": this.getHours(),
		        "m+": this.getMinutes(),
		        "s+": this.getSeconds(), 
		        "q+": Math.floor((this.getMonth() + 3) / 3),
		        "S": this.getMilliseconds()
		    };
		    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		    return fmt;
		}
		var vm = new Vue({
			el:'#app',
			data:{
				planData:[],
				filter:{
					startDate:'',
					endDate:'',
					size:50,
					page:0,
					selectState:'0',
					selectKey:''
				},
				totalPlan:0,
				tableLoading:false,
				dateRange:null,
				finishTypes:null,
				finishValues:[],
				addItemDialog:false,
				itemForm:{
					planId:'',
					startTime:'',
					planTime:'',
					remindOn:'1',
					itemContent:''
				},
				plans:[],
				planDate:'',
				stateBtns:[],
				confirmMsg:'',
				confirmDialog:false,
				itemDescr:'',
				currentRow:null,
				toState:''
			},
			created:function(){
				this.getFinishState();
				this.getPlanDatas();
			},
			methods:{
				getPlanDatas:function(){
					var _self = this;
					this.tableLoading = true;
					$.ajax({
		                type: 'get',
		                url: 'user/searchByConds',
		                async: true,
		                data:_self.filter,
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
			                    _self.planData = data.value.content;
			                    _self.totalPlan = data.value.totalElements;
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '计划查询失败，失败原因：'+data.msg,
		                	          type: 'error'
		                	        });
		                	}
		                },
		                complete:function(){
		                	_self.tableLoading = false;
		                },
		                error:function(e){
		                	console.log(e);
		                	_self.$message({
	                	          showClose: true,
	                	          message: '计划查询失败，失败原因：'+e.status,
	                	          type: 'error'
	                	        });
		                }
		            });
				},
				handleSizeChange:function(size){
					this.filter.size = size;
				},
				handleCurrentChange:function(page){
					this.filter.page = page;
				},
				updateState:function(state,index,row){
					this.currentRow = row;
					this.currentState = state;
					if(state==2){
						this.confirmMsg = '完成';
						this.confirmDialog = true;
					}else if(state==3){
						this.confirmMsg = '废弃';
						this.confirmDialog = true;
					}else{
						this.doUpdateState();
					}
				},
				doUpdateState:function(){
					var _self = this;
					$.ajax({
		                type: 'post',
		                url: 'user/updateItemState',
		                async: true,
		                data:{itemId:_self.currentRow.id,state:_self.currentState,descr:_self.itemDescr},
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
		                		_self.getPlanDatas();
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '更新状态失败，失败原因：'+data.msg,
		                	          type: 'error'
		                	        });
		                	}
		                	_self.confirmDialog = false;
		                },
		                error:function(e){
		                	console.log(e);
		                	_self.$message({
	                	          showClose: true,
	                	          message: '保存状态失败，失败原因：'+e.status,
	                	          type: 'error'
	                	        });
		                }
		            });
				},
				//TODO 移到插件中统一管理
				getFinishState:function(){
					var _self = this;
					$.ajax({
		                type: 'get',
		                url: 'subject/finishType',
		                async: false,
		                data:{type:'finish_state'},
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
			                    _self.finishTypes = new Map();
			                    var temp = data.value;
			                    if(temp&&temp.length>0){
			                    	temp.forEach(function(item){
				                    	_self.finishTypes.set(item.value,item.label);
			                    	});
			                    }
			                    _self.finishValues = data.value;
			                    var first = false;
			                    for(var i in data.value){
			                    	if(!first){
			                    		first = true;
			                    	}else{
				                    	_self.stateBtns.push(data.value[i]);
			                    	}
			                    }
			                    _self.finishValues.push({label:'全部',value:'-1'});
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '完成状态查询失败，失败原因：'+data.msg,
		                	          type: 'error'
		                	        });
		                	}
		                },
		                error:function(e){
		                	console.log(e);
		                	_self.$message({
	                	          showClose: true,
	                	          message: '完成状态查询失败，失败原因：'+e.status,
	                	          type: 'error'
	                	        });
		                }
		            });
				},
				formatFinishState:function(row,col,val){
					return this.finishTypes.get(val.toString());
				},
				addItem:function(){
					this.addItemDialog = true;
					var date = new Date();
					this.planDate = date;
					var end = new Date();
					end.setDate(end.getDate()+1);
					end.setHours(0);
					end.setMinutes(0);
					end.setSeconds(0);
					this.itemForm.startTime = date;
					this.itemForm.planTime = end;
				},
				createSubmit:function(){
					var _self = this;
					$.ajax({
		                type: 'post',
		                url: 'user/addPlanItem',
		                async: true,
		                data:_self.itemForm,
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
		                		_self.$message({
		                	          showClose: true,
		                	          message: '保存计划项成功',
		                	          type: 'info'
		                	        });
		                		_self.getPlanDatas();
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '保存计划项失败，失败原因：'+data.msg,
		                	          type: 'error'
		                	        });
		                	}
		                },
		                error:function(e){
		                	console.log(e);
		                	_self.$message({
	                	          showClose: true,
	                	          message: '保存计划项失败，失败原因：'+e.status,
	                	          type: 'error'
	                	        });
		                }
		            });
					this.addItemDialog = false;
				},
				getPlanByDate:function(date){
					var _self = this;
					var res = null;
					$.ajax({
		                type: 'get',
		                url: 'user/getPlanByDate',
		                async: false,
		                data:{date:date},
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
			                    res = data.value;
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '按日查询计划状态失败，失败原因：'+data.msg,
		                	          type: 'error'
		                	        });
		                	}
		                },
		                complete:function(){
		                },
		                error:function(e){
		                	console.log(e);
		                	_self.$message({
	                	          showClose: true,
	                	          message: '按日查询计划失败，失败原因：'+e.status,
	                	          type: 'error'
	                	        });
		                }
		            });
					return res;
				}
			},
			watch:{
				dateRange:function(newVal, oldVal){
					if(newVal&&newVal.length>1){
						this.filter.startDate = newVal[0].toLocaleDateString();
						this.filter.endDate = newVal[1].toLocaleDateString();
						this.getPlanDatas();
					}
				},
				'filter.size':function(newVal, oldVal){
					this.getPlanDatas();
				},
				'filter.page':function(newVal, oldVal){
					this.getPlanDatas();
				},
				planDate:function(newVal, oldVal) {
					if(newVal){
						var plan = this.getPlanByDate(newVal.format('yyyy-MM-dd'));
							console.log(plan);
						if(plan){
							this.itemForm.planId = plan.id;
						}
					}
				}
			}
		});
	</script>
</body>
</html>