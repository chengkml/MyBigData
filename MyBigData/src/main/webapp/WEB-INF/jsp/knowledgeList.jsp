<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>知识列表</title>
<link rel="stylesheet" href="static/css/element-ui.css">
<link rel="stylesheet" href="static/css/knowledgeList.css">
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
					<el-input size="large" class="select-input"
					  placeholder="请输入内容"
					  v-model="filter.selectKey"
					  clearable>
					</el-input>
					<el-button icon="el-icon-search" circle @click="getKnowledgeDatas"></el-button>
					<el-button class="item-add-button" size="medium" type="success" round 
					@click="addItem">新增知识点</el-button>
				</div>
			</el-header>
		  	<el-container v-loading='tableLoading'>
				<el-table
				  :data="knowledgeDatas"
				  style="width: 100%">
				  <el-table-column
				    label="创建时间"
				    width="180">
				    <template slot-scope="scope">
				      <i class="el-icon-time"></i>
				      <span style="margin-left: 10px">{{ scope.row.createDate }}</span>
				    </template>
				  </el-table-column>
				  <el-table-column
				    label="主题"
				    width="180">
				    <template slot-scope="scope">
				      <el-popover trigger="hover" placement="top">
				        <p>姓名: {{ scope.row.name }}</p>
				        <p>住址: {{ scope.row.address }}</p>
				        <div slot="reference" class="name-wrapper">
				          <el-tag size="medium">{{ scope.row.name }}</el-tag>
				        </div>
				      </el-popover>
				    </template>
				  </el-table-column>
				  <el-table-column label="操作">
				    <template slot-scope="scope">
				      <el-button
				        size="mini"
				        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
				      <el-button
				        size="mini"
				        type="danger"
				        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
			      :total="totalknowledge">
			    </el-pagination>
			</el-footer>
		</el-container>
		<el-dialog
		  title="新增知识项"
		  :visible.sync="addItemDialog"
		  width="35%" :close-on-click-modal="false" :close-on-press-escape="false"
		  center>
		  <el-form ref="Form" :model="itemForm" label-width="80px" size="medium">
		  	  <el-form-item label="主题" style="width:300px;">
			    <el-input type="textarea" v-model="itemForm.keyWord"></el-input>
			  </el-form-item>
			  <el-form-item label="详细描述" style="width:300px;">
			    <el-input type="textarea" v-model="itemForm.itemContent"></el-input>
			  </el-form-item>
		</el-form>
			<span slot="footer" class="dialog-footer">
			    <el-button @click="addItemDialog = false">取 消</el-button>
			    <el-button type="primary" @click="createSubmit">创建</el-button>
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
					selectKey:''
				},
				totalknowledge:0,
				tableLoading:false,
				dateRange:null,
				addItemDialog:false,
				itemForm:{
					keyWord:'',
					itemContent:''
				},
				knowledges:[],
				currentRow:null,
			},
			created:function(){
				this.getKnowledgeDatas();
			},
			methods:{
				getKnowledgeDatas:function(){
					var _self = this;
					this.tableLoading = true;
					$.ajax({
		                type: 'get',
		                url: 'user/searchKnowledgeByConds',
		                async: true,
		                data:_self.filter,
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
			                    _self.knowledgeDatas = data.value.content;
			                    _self.totalknowledge = data.value.totalElements;
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '计划知识列表失败，失败原因：'+data.msg,
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
	                	          message: '知识列表查询失败，失败原因：'+e.status,
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
				//TODO 移到插件中统一管理
				addItem:function(){
					this.itemForm.keyWord = '';
					this.itemForm.itemContent = '';
					this.addItemDialog = true;
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
					}else{
						this.filter.startDate = null;
						this.filter.endDate = null;
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