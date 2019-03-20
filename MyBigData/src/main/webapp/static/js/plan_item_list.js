var vm = new Vue({
	el:'#app',
	data:{
		planData:[],
		filter:{
			startDate:'',
			endDate:'',
			pageNum:0,
			pageSize:50,
			state:'',
			keyWord:'',
			createBy:'ck'
		},
		planItemNum:0,
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
		confirmMsg:'',
		confirmDialog:false,
		itemDescr:'',
		currentRow:null,
		toState:''
	},
	created:function(){
		this.getFinishState();
		this.getPlanItems();
	},
	methods:{
		getPlanItems:function(){
			var _self = this;
			this.tableLoading = true;
			$.ajax({
				type: 'get',
				url: 'planItem/selectByConds',
				async: true,
				data:_self.searchParams,
				dataType: 'json',
				success: function(res) {
					if(res.success){
						_self.planData = res.data.list;
						_self.planItemNum = res.data.total;
					}else{
						_self.$message({
							showClose: true,
							message: '计划查询失败，失败原因：'+res.msg,
							type: 'error'
						});
					}
				},
				complete:function(){
					_self.tableLoading = false;
				},
				error:function(e){
					_self.$message({
						showClose: true,
						message: '计划查询失败!',
						type: 'error'
					});
				}
			});
		},
		handleSizeChange:function(pageSize){
			this.filter.pageSize = pageSize;
			this.getPlanItems();
		},
		handleCurrentChange:function(pageNum){
			this.filter.pageNum = pageNum;
			this.getPlanItems();
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
						_self.getPlanItems();
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
		getFinishState(){
			var _self = this;
			$.ajax({
				type: 'get',
				url: 'enums/finishType',
				async: false,
				data:{},
				dataType: 'json',
				success: function(res) {
					if(res.success){
						_self.finishTypes = new Map();
						_self.finishValues = [{label:'全部',value:'-1'}];
						if(res.data&&res.data instanceof Array){
							res.data.forEach(function(item){
								_self.finishTypes.set(item.value,item.label);
								_self.finishValues.push({label:item.label,value:item.value});
							});
						}
					}else{
						_self.$message({
							showClose: true,
							message: '完成状态查询失败，失败原因：'+res.msg,
							type: 'error'
						});
					}
				},
				error:function(e){
					console.log(e);
					_self.$message({
						showClose: true,
						message: '完成状态查询失败!',
						type: 'error'
					});
				}
			});
		},
		formatFinishState:function(row,col,val){
			return this.finishTypes.get(val.toString());
		},
		addItem:function(){
			var date = new Date();
			this.planDate = date;
			var end = new Date();
			end.setDate(end.getDate()+1);
			end.setHours(0);
			end.setMinutes(0);
			end.setSeconds(0);
			this.itemForm.startTime = date;
			this.itemForm.planTime = end;
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
						_self.getPlanItems();
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
				this.getPlanItems();
			}else{
				this.filter.startDate = null;
				this.filter.endDate = null;
				this.getPlanItems();
			}
		},
		planDate:function(newVal, oldVal) {
			if(newVal){
				var plan = this.getPlanByDate(newVal.format('yyyy-MM-dd'));
				if(plan){
					this.itemForm.planId = plan.id;
				}
			}
		}
	},
	computed:{
		searchParams:function(){
			var result = $.extend({},this.filter);
			return result;
		}
	}
});