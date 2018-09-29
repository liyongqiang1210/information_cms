$(function() {

	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

	// 2.初始化Button的点击事件
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();

	// 添加权限弹框
	$("#btn_add")
			.on(
					"click",
					function() {
						// 弹出框标题
						var title = "添加权限";
						// 弹出框内容
						var content = "<form id='addUser' class='form-horizontal' role='form'> <div class='form-group'> <label for='username' class='col-lg-2 col-lg-offset-1 control-label'>权限名:</label> <div class='col-lg-8'><input type='text' class='form-control' id='username' placeholder='请输入权限名'></div> </div>"
								+ " <div class=' form-group'> <label for='password' class='col-lg-2 col-lg-offset-1 control-label'>密码:</label> <div class='col-lg-8'><input type='password' class='form-control' id='password' placeholder='请输入密码'> </div></div> "
								+ "<div class='form-group'> <label for='password' class='col-lg-2 col-lg-offset-1 control-label'>确认密码:</label> <div class='col-lg-8'><input type='password' class='form-control' id='password2' placeholder='请再次输入密码'> </div></div>"
								+ " <div class='form-group'> <label for='email' class='col-lg-2 col-lg-offset-1 control-label'>邮箱:</label> <div class='col-lg-8'><input type='text' class='form-control' id='email' placeholder='请输入邮箱'> </div></div>"
								+ " <div class='form-group'> <label for='sex' class='col-lg-2 col-lg-offset-1 control-label'>性别:</label> <div class='col-lg-8'><input type='radio' name='sex' value='0' checked> 男  <input type='radio' name='sex' value='1'> 女 </div> </div>"
								+ " <div class='form-group'> <label for='phone' class='col-lg-2 col-lg-offset-1 control-label'>手机号:</label> <div class='col-lg-8'><input type='text' class='form-control' id='phone' placeholder='请输入手机号'> </div></div> </form>"

						openModal(title, content, "", "addUser()");

					});

	// 删除选中权限
	$("#btn_delete_select").on(
			"click",
			function() {
				// 使用getSelections即可获得选中行数据，row是json格式的数据
				var getSelectRows = $("#table").bootstrapTable('getSelections',
						function(row) {
							return row;
						});

				if (getSelectRows.length == 0) {
					window.parent.openPromptBox("请选择要删除的数据", "panel-warning",
							"icon-warning-circle");
				} else {
					openModal("删除选中权限", "确认删除选中的权限吗？", "btn-danger",
							"deleteSelectedUser()");
				}
			});

});

// table对象
var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$('#table').bootstrapTable({
			url : 'http://localhost:8080/Information_cms/resource/findAll.do', // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : function(params) {
				var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					limit : params.limit, // 页面大小
					offset : params.offset, // 页码
					rolename : $("#search_name").val()
				};
				return temp;
			}, // 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 5, 10, 15, 20 ], // 可供选择的每页的行数（*）
			search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : false, // 是否启用点击选中行
			height : 600, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ID", // 每一行的唯一标识，一般为主键列
			showToggle : false, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			columns : [ {
				checkbox : true,
				valign : 'middle',
			}, {
				field : 'id',
				title : '权限ID',
				valign : 'middle'
			}, {
				field : 'name',
				title : '权限名',
				valign : 'middle'
			}, {
				field : 'type',
				title : '权限类型',
				valign : 'middle'
			}, {
				field : 'url',
				title : '权限url',
				valign : 'middle'
			}, {
				field : 'permission',
				title : '权限字符串',
				valign : 'middle'
			}, {
				field : 'available',
				title : '是否启用',
				width : '200px',
				align : "center",// 数据对齐方式
				halign : "center",// 表头的对齐方式
				valign : 'middle',// 数据垂直居中方式
				formatter : availableFormatter
			}, {
				field : 'operate',
				title : '操作',
				width : '400px',
				align : "center",// 数据对齐方式
				halign : "center",// 表头的对齐方式
				valign : 'middle',// 数据垂直居中方式
				events : operateEvents,
				formatter : operateFormatter
			}, ],
			onPostBody : function() {
				
				// 用于开关组件初始化        
				$('.status').each(function(){
					
					var status = $(this).data('status');             
					$(this).bootstrapSwitch({                
						onColor : "success",                
						offColor : "warning",
					}).bootstrapSwitch('size', "mini").bootstrapSwitch('state', status);
				});
				
				// 监听开关按钮,按钮状态发生变化时触发此事件
				// 此事件不能与开关按钮组件初始化放到一起，否则会造成事件的重复调用
				$('.status').bootstrapSwitch('onSwitchChange',function(event,state){
					var id = $(this).data('id');// 当前行数据id
					// 修改状态
					var available = 1;
					if(!state){
						available = 0;
					}
					$.ajax({
						url : "http://localhost:8080/Information_cms/resource/updateResourceState.do",
						data : {id : id, available : available},
						type : "POST",
						dataType : "json",
						success : function(data){
							console.log(data.message);
						},
						error : function(data){
							console.log(data.message);
						}
						
					});
				});

			}
		});
	};
	return oTableInit;

};

window.operateEvents = {
// 'click #btn_edit': function(e, value, row, index) {
// detailmodal.open();
// $("#dev_id").val(row.id);
// $("#seq_no").val(row.seq_no);
// $("#dev_pos").val(row.position);
// $("#dev_type1").val(row.type);
// $("#dev_status").val(row.status);
// $("#fault").val(row.fault);
// $("#buy_time").val(row.purchase_time);
// $("#quality_time").val(row.quality_time);
// $("#inputer").val(row.inputer);
// $("#maintain_unit").val(row.maintain_unit);
// }
};

function operateFormatter(value, row, index) {
	return [
			'<button type="button" onclick="editRole()" class="btn btn-success btn-xs">编辑</button> &nbsp;',
			'<button type="button" onclick="deleteRole()" class="btn btn-danger btn-xs">删除</button> &nbsp;',
			'<button type="button" onclick="addResource()" class="btn btn-info btn-xs">分配权限</button> &nbsp;' ]
			.join('');
}

function availableFormatter(value, row, index) {
	var id = row.id;// 获取当前行数据的id
	if (value == 0) {
		return '<input type="checkbox" class="status" name="status-close" data-id="'+id+'" data-status="0" />';
	} else if (value == 1) {
		return '<input type="checkbox" class="status" name="status-open" data-id="'+id+'" data-status="1" />';
	}

}

var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};

	oInit.Init = function() {
		// 初始化页面上面的按钮事件
	};

	return oInit;
};

// 编辑权限按钮事件
function editRole() {
	openModal();
}

// 删除权限按钮事件
function deleteRole() {
	openModal("删除权限", "确认删除选中的权限吗？", "btn-danger");
}

// 添加权限按钮事件
function addUser() {
	var checkFromState = checkFrom();
	if (checkFromState) {
		$.ajax({
			url : "http://localhost:8080/Information_cms/user/createRole.do",
			data : {
				username : $("#rolename").val(),
				role_desc : $("#role_desc").val(),
				resource_ids : $("#resource_ids").val(),
				available : $("#available").val()
			},
			type : "POST",
			dataType : "json",
			success : function(data) { // 添加成功
				closeModal();
				openPromptBox("权限添加成功", "panel-success", "icon-check-circle");

			},
			error : function(data) { // 添加失败
				promptMessage("#addRole", "添加失败");
			}
		});
	}
}

// 表单验证
function checkFrom() {
	var username = $("#username").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	var email = $("#email").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#phone").val();

	if (username == "") {
		promptMessage("#addUser", "权限名不能为空");
		return false;
	} else if (password == "" || password2 == "") {
		promptMessage("#addUser", "密码不能为空");
		return false;
	} else if (password != password2) {
		promptMessage("#addUser", "两次输入的密码不一致");
		return false;
	} else if (email == "") {
		promptMessage("#addUser", "邮箱不能为空");
		return false;
	} else if (phone == "") {
		promptMessage("#addUser", "手机号不能为空");
		return false;
	}

	return true;

}

function deleteSelectedUser() {

	// 获取选中行的数据
	var getSelectRows = $("#table").bootstrapTable('getSelections',
			function(row) {
				return row;
			});

	$.ajax({
		type : "POST",
		url : "",
		data : "",
		dataType : "json",
		success : function(data) {

		},
		error : function() {

		}

	});
}
