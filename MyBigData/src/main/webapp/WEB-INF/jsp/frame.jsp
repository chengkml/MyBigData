<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的大数据</title>
<link rel="stylesheet" href="static/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="static/css/frame.css">
<script type="text/javascript" src="static/vue/dist/vue.js"></script> 
<script type="text/javascript" src="static/element-ui/lib/index.js"></script>
<script type="text/javascript" src="static/js/jquery.js"></script>
</head>
<body>
	<div id="app">
		<el-container style="height: 640px; border: 1px solid #eee">
		  <el-header style="text-align: right; font-size: 12px">
		  	<el-menu mode="horizontal" class="el-menu-demo">
		      <el-menu-item v-for="item in parentMenus" index="item.index">{{item.menuLabel}}</el-menu-item>
		    </el-menu>
		  </el-header>
		  <el-container>
			<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
			    <el-menu>
			      <el-menu-item v-for="item in subMenus" index="item.index">{{item.menuLabel}}</el-menu-item>
			    </el-menu>
			</el-aside>			    
		    <el-main style="padding:0px;">
		      <iframe :src="pageUrl" style="width:calc(100% - 4px);height:calc(100% - 8px);"></iframe>
		    </el-main>
			 </el-container>
		</el-container>
	</div>
	<script type="text/javascript">
		var vm = new Vue({
			el:'#app',
			data:{
				"pageUrl":'',
				currParentMenu:null,
				currSubMenu:null,
				parentMenus:[
					{
						index:1,
						menuLabel:'工作计划',
						menuCode:'plan',
						url:'',
						parent:null
					},
					{
						index:2,
						menuLabel:'知识积累',
						menuCode:'knowledge',
						url:'',
						parent:null
					},
					{
						index:3,
						menuLabel:'长远规划',
						menuCode:'targets',
						url:'',
						parent:null
					},
					{
						index:4,
						menuLabel:'自我管控',
						menuCode:'selfControl',
						url:'',
						parent:null
					},
					{
						index:1,
						menuLabel:'统计分析',
						menuCode:'stastics',
						url:'',
						parent:null
					}
				],
				subMenus:[
					{
						index:1,
						menuLabel:'我的计划',
						menuCode:'my_plan',
						url:'myPlan',
						parent:null
					},
					{
						index:2,
						menuLabel:'正在执行',
						menuCode:'runningPlan',
						url:'',
						parent:null
					},
					{
						index:3,
						menuLabel:'今日计划',
						menuCode:'planToday',
						url:'',
						parent:null
					}
				]
			},
			created:function(){
				if(this.parentMenus&&this.parentMenus.length>0){
					this.currParentMenu = this.parentMenus[0];
					this.currSubMenu = this.subMenus[0];
				}
			},
			methods:{
				
			},
			watch:{
				currSubMenu:function(newVal, oldVal) {
					this.pageUrl = newVal.url;
				}
			}
		});
	</script>
</body>
</html>