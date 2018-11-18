<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的计划</title>
<link rel="stylesheet" href="static/css/element-ui.css">
<script type="text/javascript" src="static/js/vue.js"></script> 
<script type="text/javascript" src="static/js/element-ui.js"></script>
<script type="text/javascript" src="static/js/jquery.js"></script>
</head>
<body>
	<div id="app">
		<el-container>
			<el-header style="text-align: right; font-size: 12px">
				<el-button-group>
				  <el-button type="primary" icon="el-icon-edit"></el-button>
				  <el-button type="primary" icon="el-icon-share"></el-button>
				  <el-button type="primary" icon="el-icon-delete"></el-button>
				</el-button-group>
			</el-header>
		  	<el-container>
			    <el-table :data="planData" style="width: 100%">
			      <el-table-column prop="createDate" label="日期" width="180"></el-table-column>
			      <el-table-column prop="itemContent" label="内容" width="180"></el-table-column>
			      <el-table-column prop="planTime" label="计划时间"></el-table-column>
			      <el-table-column prop="finishTime" label="完成时间"></el-table-column>
			      <el-table-column prop="finishType" label="完成结果"></el-table-column>
			      <el-table-column prop="finishDescr" label="备注"></el-table-column>
			    </el-table>
			</el-container>
		</el-container>
	</div>
	<script type="text/javascript">
		var vm = new Vue({
			el:'#app',
			data:{
				planData:[
					{
						createDate:'2018-11-18',
						itemContent:'计划项',
						planTime:'2018-11-18',
						finishTime:'2018-11-18',
						finishType:'完成',
						finishDescr:'完美'
					}
				],
				filter:{
					startDate:'2018-11-01',
					endDate:'2018-11-19',
					size:10,
					page:0
				},
				tableLoading:false
			},
			created:function(){
				this.getPlanDatas();
			},
			methods:{
				getPlanDatas:function(){
					var _self = this;
					this.tableLoading = true;
					$.ajax({
		                type: 'get',
		                url: 'user/search.do',
		                async: true,
		                data:_self.filter,
		                dataType: 'json',
		                success: function(data) {
		                	if(data.success){
			                    _self.planData = data.value.content;
		                	}else{
		                		_self.$message({
		                	          showClose: true,
		                	          message: '计划查询失败，失败原因：'+data.msg,
		                	          type: 'error'
		                	        });
		                	}
		                },
		                complete:function(){
		                	this.tableLoading = false;
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
				}
			}
		});
	</script>
</body>
</html>