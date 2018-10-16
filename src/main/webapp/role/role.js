$(function() {
	// 调用layui数据表格渲染
	layuiTable(10, 1);
});

/**
 * layui数据表格渲染函数
 * 
 * @param limit
 * @param offset
 * @returns
 */
function layuiTable(limit, offset) {
	layui.config({
		base : '../plugin/layui-v2.4.3/extends/',
	}).extend({
		authtree : 'authtree',
	});
	// layui模块初始化
	layui
			.use(
					[ 'authtree', 'table', 'laypage', 'form', 'layer' ],
					function() {
						var table = layui.table, laypage = layui.laypage, form = layui.form, layer = layui.layer, authtree = layui.authtree;

						// 渲染表格
						table
								.render({
									elem : '#role_table',
									height : 600,
									url : 'http://localhost:8080/Information_cms/role/getAll.do?offset='
											+ offset + '&limit=' + limit, // 数据接口
									page : false, // 不开启分页
									toolbar : '<div class="layui-btn-container">'
											+ '<button class="layui-btn layui-btn-sm" lay-event="add">添加角色</button>'
											+ '<button class="layui-btn layui-btn-sm" lay-event="delSelected">删除选中</button></div>',
									defaultToolbar : [],
									cols : [ [ // 表头
											{
												type : 'checkbox',
												fixed : 'left'
											},
											{
												field : 'id',
												title : 'ID',
												width : 80,
												sort : true,
												fixed : 'left'
											},
											{
												field : 'roleName',
												title : '角色名',
												width : 150
											},
											{
												field : 'roleDesc',
												title : '角色描述',
												width : 500
											},
											{
												field : 'available',
												title : '是否可用',
												width : 150,
												align : 'center',
												templet : function(d) {
													var on = '<input type="checkbox" id="'
															+ d.id
															+ '" lay-skin="switch" lay-filter="available" lay-text="启用|关闭" checked>';
													var off = '<input type="checkbox" id="'
															+ d.id
															+ '" lay-skin="switch" lay-filter="available" lay-text="启用|关闭">';
													if (d.available === 1) {
														return on;
													} else {
														return off;
													}
												}
											}, {
												field : 'operate',
												title : '操作',
												align : 'left',
												templet : function(d) {
													return operateToolBar(d);
												}
											} ] ],
									done : function(res, curr, count) { // 数据渲染完的回调函数
										// 分页
										laypage.render({
											elem : 'laypage',
											count : count,
											curr : offset,
											limit : limit,
											layout : [ 'prev', 'page', 'next',
													'skip', 'count', 'limit' ],
											jump : function(obj, first) {
												// 首次不执行(这里必须加上这个判断)
												if (!first) {
													offset = obj.curr; // 当前页
													limit = obj.limit; // 当前页面显示数据条数
													layuiTable(limit, offset);
												}
											}
										})
									}
								});

						// 头工具栏事件
						table.on('toolbar(role_table)', function(obj) {
							var checkStatus = table.checkStatus(obj.config.id);
							switch (obj.event) {
							case 'add': // 添加角色
								bindingAddEvent(form, authtree);
								break;
							case 'delSelected': // 删除选中
								var data = checkStatus.data;
								layer.alert(JSON.stringify(data));
								break;
							}

						});

						// 监听开关按钮
						form.on('switch(available)', function(data) {
							var status = data.elem.checked; // 得到开关的状态
							var id = data.elem.id; // 获取角色id
							if (status) { // 启用
								updateRoleAvailable(id, 1);// 修改角色状态为启用
							} else { // 关闭
								updateRoleAvailable(id, 0);// 修改角色状态为关闭
							}
						});

						// 监听操作列按钮
						table.on('tool(role_table)', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
							// lay-filter="对应的值"
							var id = obj.data.id; // 获得当前行数据
							var layEvent = obj.event; // 获得 lay-event
							// 对应的值（也可以是表头的 event
							// 参数对应的值）
							var tr = obj.tr; // 获得当前行 tr 的DOM对象

							if (layEvent === 'detail') { // 查看
								bindingDetailEvent(id);
							} else if (layEvent === 'del') { // 删除
								layer.confirm('确认删除这个角色信息吗？', function(index) {
									// 向服务端发送删除指令
									bindingDelEvent(id, obj, index);
									// 重新加载表格
									table.reload('role_table', {});
								});
							} else if (layEvent === 'edit') { // 编辑
								// do something
								bindingEditEvent(id);
								// 同步更新缓存对应的值
								obj.update({
									username : '123',
									title : 'xxx'
								});
							}
						});
					});
}

/**
 * 数据操作工具栏
 * 
 * @param obj
 * @returns
 */
function operateToolBar(obj) {
	var id = obj.id;
	var toolBar = '<a class="layui-btn layui-btn-xs" id='
			+ id
			+ ' name="edit" lay-event="edit">编辑</a><a class="layui-btn layui-btn-danger layui-btn-xs" id='
			+ id + ' name="del" lay-event="del">删除</a>';
	return toolBar;

}

/**
 * 编辑角色状态方法
 * 
 * @param id
 *            角色id
 * @param available
 *            角色状态
 * @returns
 */
function updateRoleAvailable(id, available) {
	$.ajax({
		type : 'POST',
		url : 'http://localhost:8080/Information_cms/role/updateRoleState.do',
		data : {
			id : id,
			available : available
		},
		async : true,
		dataType : "json",
		success : function(data) {
			if (data.success) {
				layer.msg('状态修改成功');
			} else {
				layer.msg('状态修改失败');
				$('#' + id + '').prop('checked', ''); // 将状态改为关闭
			}
		},
		error : function(data) {
			layer.msg('状态修改失败');
			$('#' + id + '').prop('checked', ''); // 将状态改为关闭
		}
	});
}

/**
 * 添加角色
 * 
 * @param form
 * @param html
 * @returns
 */
function bindingAddEvent(form, authtree) {

	var html = '<form class="layui-form" action="" style="margin:20px;">'
			+ '<div class="layui-form-item"><label class="layui-form-label">角色名</label><div class="layui-input-block"><input type="text" id="roleName" lay-verify="roleName" placeholder="请输入角色名" autocomplete="off" class="layui-input"></div></div>'
			+ '<div class="layui-form-item layui-form-text"><label class="layui-form-label">角色描述</label><div class="layui-input-block"><textarea id="roleDesc" required  lay-verify="roleDesc" placeholder="请输入角色描述" class="layui-textarea"></textarea></div></div>'
			+ '<div class="layui-form-item"><label class="layui-form-label">选择权限</label><div class="layui-input-block"><div id="LAY-auth-tree-index"></div></div></div>'
			+ '<div class="layui-form-item"><label class="layui-form-label">是否启用</label><div class="layui-input-block"><input type="checkbox" id="available" lay-skin="switch" lay-text="开启|关闭" checked></div></div>'
			+ '<div class="layui-form-item" hidden><div class="layui-input-block"><button id="addRole" class="layui-btn" lay-submit lay-filter="formDemo">提交</button></div></div>'
			+ '</form>';

	layer.open({
		type : 1,
		title : '添加角色',
		area : [ '500px', '600px' ],
		shadeClose : true, // 点击遮罩关闭
		content : html,
		btn : [ '保存', '取消' ],
		success : function(index, layero) { // 成功弹出后回调
			// 初始化
			$.ajax({
				url : 'http://localhost:8080/Information_cms/role/tree.json',
				dataType : 'json',
				success : function(data) {
					// 渲染时传入渲染目标ID，树形结构数据（具体结构看样例，checked表示默认选中），以及input表单的名字
					authtree.render('#LAY-auth-tree-index', data.data.trees, {
						inputname : 'authids[]',
						layfilter : 'lay-check-auth'
						// ,autoclose: false
						// ,autochecked: false
						// ,openchecked: true
						// ,openall: true
						,
						autowidth : true
					});

					// 使用 authtree.on() 不会有冒泡延迟
					authtree.on('change(lay-check-auth)', function(data) {
						console.log('监听 authtree 触发事件数据', data);
						// 获取所有节点
						var all = authtree.getAll('#LAY-auth-tree-index');
						console.log('all', all);
						// 获取所有已选中节点
						var checked = authtree
								.getChecked('#LAY-auth-tree-index');
						console.log('checked', checked);
						// 获取所有未选中节点
						var notchecked = authtree
								.getNotChecked('#LAY-auth-tree-index');
						console.log('notchecked', notchecked);
						// 获取选中的叶子节点
						var leaf = authtree.getLeaf('#LAY-auth-tree-index');
						console.log('leaf', leaf);
						// 获取最新选中
						var lastChecked = authtree
								.getLastChecked('#LAY-auth-tree-index');
						console.log('lastChecked', lastChecked);
						// 获取最新取消
						var lastNotChecked = authtree
								.getLastNotChecked('#LAY-auth-tree-index');
						console.log('lastNotChecked', lastNotChecked);
					});
					authtree.on('deptChange(lay-check-auth)', function(data) {
						console.log('监听到显示层数改变', data);
					});
				}
			});
			form.render('checkbox'); // 刷新checkbox开关渲染(否则开关按钮会不显示)
		},
		yes : function(index, layero) { // 确认按钮回调函数
			layero.find('#addRole').click();
		},
		btn2 : function(index, layero) { // 取消按钮回调函数
			layer.close(index); // 关闭弹出层
		}
	});
	// 表单验证
	form
			.verify({
				roleName : function(value, item) {
					if (!new RegExp("^[a-zA-Z0-9_|\u4e00-\u9fa5\]{1,10}$")
							.test(value)) {
						return '角色名必须为2-10位且不能有特殊字符';
					}
				},
				roleDesc : function(value, item) {
					if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\]{2,200}$")
							.test(value)) {
						return '角色描述必须为2-200位且不能有特殊字符';
					}
				}
			});

}

/**
 * 绑定编辑按钮事件
 * 
 * @param id
 * @param obj
 * @param index
 * @returns
 */
function bindingEditEvent(id) {
	$.ajax({
		type : 'POST',
		url : '',
		data : {
			id : id
		},
		dataType : 'json',
		success : function(data) {
			layer.open({
				type : 0,
				area : [ '500px', '500px' ],
				shadeClose : true, // 点击遮罩关闭
				content : '<div style="padding:20px;">自定义内容</div>'
			});
		},
		error : function(data) {
			layer.alert('获取信息出现异常,请稍后再试', {
				icon : 5
			});
		}
	});

}

/**
 * 绑定删除按钮事件
 * 
 * @returns
 */
function bindingDelEvent(id, obj, index) {
	$.ajax({
		type : 'POST',
		url : 'http://localhost:8080/Information_cms/role/deleteRole.do',
		data : {
			roleId : id
		},
		dataType : 'json',
		success : function(data) {
			obj.del(); // 删除对应行（tr）的DOM结构，并更新缓存
			layer.close(index);
			layer.msg('删除成功', {
				icon : 6,
				time : 1000
			});
		},
		error : function(data) {
			layer.msg('删除失败', {
				icon : 5,
				time : 1000
			});
		}
	});
}
