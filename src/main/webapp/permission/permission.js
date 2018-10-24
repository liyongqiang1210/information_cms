// 记录当前页码,当前页显示数据条数和总数据数
var page, limit, total;

layui
		.use(
				[ 'table', 'laypage', 'form', 'layer' ],
				function() {
					var table = layui.table, laypage = layui.laypage, form = layui.form, layer = parent.layer === undefined ? layui.layer
							: parent.layer;
					// 给选择框赋默认值
					$('#resourceType').val('0');
					// 刷新select选择框渲染
					form.render('select');
					// 表格顶部工具栏
					var toolbarHtml = '<div class="layui-btn-container">'
							+ '<button class="layui-btn layui-btn-sm" lay-event="add">添加功能</button>'
							+ '<button class="layui-btn layui-btn-sm" lay-event="delSelected">删除选中</button></div>';
					// 首次渲染表格
					var tableIns = table
							.render({
								elem : '#resourceTable',
								height : 650,
								url : 'getAll.do', // 数据接口
								page : {
									prev : '上一页',
									next : '下一页',
									groups : 3
								}, // 开启分页
								limit : 10, // 页面显示数据量
								limits : [ 10, 20, 30, 40, 50 ],
								loading : true, // 是否显示加载效果
								toolbar : toolbarHtml,
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
											field : 'name',
											title : '功能名称',
											width : 150
										},
										{
											field : 'type',
											title : '功能类型',
											width : 200,
											templet : function(d) {
												if (d.type === 'menu') {
													return '菜单';
												} else {
													return '按钮';
												}
											}
										},
										{
											field : 'url',
											title : '功能url路径',
											width : 300
										},
										{
											field : 'parentId',
											title : '父级功能id',
											width : 100
										},
										{
											field : 'permission',
											title : '权限字符串',
											width : 300
										},
										{
											field : 'available',
											title : '是否启用',
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
									// 当前页码
									page = curr;
									// 当前显示数据量
									limit = parseInt($(
											'.layui-laypage-limits>select>option:selected')
											.val());
									// 当前数据总数
									total = count;
								}
							});

					// 查询按钮绑定点击事件
					$("#search").on('click', function() {
						// 执行重载
						tableIns.reload({
							// 重载条件
							where : {
								name : $('#resourceName').val(),
								type : $('#resourceType>option:selected').val()
							},
							page : {
								curr : 1,
								prev : '上一页',
								next : '下一页',
								groups : 3
							}
						});
					});

					// 监听开关按钮
					form.on('switch(available)', function(data) {
						var status = data.elem.checked; // 得到开关的状态
						var id = data.elem.id; // 获取权限id
						if (status) { // 启用
							updateResourceAvailable(id, 1);// 修改功能状态为启用
						} else { // 关闭
							updateResourceAvailable(id, 0);// 修改功能状态为关闭
						}
					});

					// 监听头工具栏
					table.on('toolbar(resourceTable)', function(obj) {
						var checkStatus = table.checkStatus('resourceTable'); // 获取表格的选中行
						switch (obj.event) {
						case 'add': // 添加功能
							bindingAddEvent(form, table);
							break;
						case 'delSelected': // 删除选中功能
							var data = checkStatus.data; // 获取选中功能集合
							var ids = ''; // 功能id字符串
							// 将选中功能id拼接到一起
							for (var i = 0; i < data.length; i++) {
								ids += data[i].id + ',';
							}
							bindingDelSelectedEvent(ids, data.length); // 删除选中功能
							break;
						}

					});

					// 监听操作列按钮
					table.on('tool(resourceTable)', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
						var id = obj.data.id; // 获得当前行数据id

						switch (obj.event) {
						case 'permission': // 分配权限
							break;
						case 'del': // 删除
							layer.confirm('确认删除这个功能信息吗？', function(index) {
								// 向服务端发送删除指令
								bindingDelEvent(id, index);
							});
							break;
						case 'edit': // 编辑
							bindingEditEvent(form, obj);
						}
					});
				});

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
			+ id
			+ ' name="del" lay-event="del">删除</a><a class="layui-btn layui-btn-normal layui-btn-xs" id='
			+ id + ' name="permission" lay-event="permission">分配权限</a>';

	return toolBar;
}

/**
 * 修改功能状态方法
 * 
 * @param id
 *            功能id
 * @param available
 *            功能状态
 * @returns
 */
function updateResourceAvailable(id, available) {
	$.ajax({
		type : 'POST',
		url : 'updateResourceState.do',
		data : {
			id : id,
			available : available
		},
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
 * 添加功能
 * 
 * @param form
 * @param table
 * @returns
 */
function bindingAddEvent(form, table) {

	parent.layer.open({
		type : 2,
		title : '添加权限',
		area : [ '500px', '450px' ],
		offset : '160px',
		shadeClose : true, // 点击遮罩关闭
		btn : [ '保存', '取消' ],
		content : '../permission/permission_add.html',
		success : function(layero, index) { // 成功弹出后回调
			$(':focus').blur(); // 解决按enter键重复弹窗问题
		},
		yes : function(index, layero) { // 保存按钮回调函数
			var body = parent.layer.getChildFrame('body', index); // 找到子页面body标签内容
			body.find('#resourceSubmit').click(); // 获取到提交按钮点击
		},
		btn2 : function(index, layero) { // 取消按钮回调函数
			parent.layer.close(index); // 关闭弹出层
		}
	});
}

/**
 * 删除选中功能
 * 
 * @param ids
 *            功能id拼接的字符串
 * @param delCount
 *            删除功能数量
 * @returns
 */
function bindingDelSelectedEvent(ids, delCount) {

	if (null === ids || '' === ids) {
		layer.msg('请选择要删除的功能', {
			icon : 7
		});
	} else {
		parent.layer.confirm('确认删除选中的功能吗？', {
			icon : 6,
			title : '删除选中功能'
		}, function(index) {
			$.ajax({
				type : 'POST',
				url : 'deleteSelectedResource.do',
				data : {
					ids : ids
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						layer.msg('删除成功', {
							icon : 6
						});
					} else {
						layer.msg('删除失败', {
							icon : 5
						});
					}
					parent.layer.close(index);
					delDataRefreshTable(delCount); // 刷新表格
				},
				error : function(data) {
					layer.msg('删除失败', {
						icon : 5
					});
					parent.layer.close(index);
				}
			});
		});
	}
}

/**
 * 编辑权限
 * 
 * @param id
 * @param obj
 * @param index
 * @returns
 */
function bindingEditEvent(form, obj) {

	var id = obj.data.id; // 获得当前行数据id
	parent.layer.open({
		id : 'editResource',
		type : 2,
		title : '编辑权限',
		area : [ '500px', '450px' ],
		offset : '160px',
		shadeClose : true, // 点击遮罩关闭
		content : '../permission/permission_edit.html',
		btn : [ '保存', '取消' ],
		success : function(layero, index) { // 成功弹出后回调
			// 获取iframe页面
			var iframe = $(window.parent.document).contents().find(
					"#editResource>iframe")[0].contentWindow;
			// 调用iframe页面的formInit方法
			iframe.formInit();
		},
		yes : function(index, layero) { // 保存按钮回调函数
			var body = layer.getChildFrame('body', index); // 找到子页面body标签内容
			body.find('#resourceSubmit').click(); // 获取到提交按钮点击
		},
		btn2 : function(index, layero) { // 取消按钮回调函数
			layer.close(index); // 关闭弹出层
		}
	});

}

/**
 * 分配权限
 * 
 * @param id
 * @param obj
 * @param index
 * @returns
 */
function bindingPermissionEvent(form, authtree, id) {

	var html = '<form class="layui-form" id="form" style="margin:20px;">'
			+ '<div class="layui-form-item"><label class="layui-form-label">选择权限</label><div class="layui-input-block"><div id="LAY-auth-tree-index"></div></div></div>'
			+ '</form>';

	layer.open({
		type : 1,
		title : '分配权限',
		area : [ '500px', '700px' ],
		shadeClose : true, // 点击遮罩关闭
		content : html,
		btn : [ '保存', '取消' ],
		success : function(layero, index) { // 成功弹出后回调
			modifyButton(layero, 'permission'); // 修改按钮
			// 初始化
			$.ajax({
				url : 'http://localhost:8080/Information_cms/role/tree.json',
				dataType : 'json',
				success : function(data) {
					// 渲染时传入渲染目标ID，树形结构数据（具体结构看样例，checked表示默认选中），以及input表单的名字
					authtree.render('#LAY-auth-tree-index', data.data.trees, {
						inputname : 'authids[]',
						layfilter : 'lay-check-auth',
						autowidth : true
					});
					// 修改权限树的默认样式
					$(".auth-icon").css({
						'color' : '#C8C8C8',
						'margin-right' : '2px'
					});
					$('.auth-status').css('margin-top', '10px');
					$('.layui-unselect').css('margin-top', '-2px');
					// 使用 authtree.on() 不会有冒泡延迟
					authtree.on('change(lay-check-auth)', function(data) {
						// 修改权限树的默认样式
						$(".auth-icon").css({
							'color' : '#C8C8C8',
							'margin-right' : '2px'
						});
						$('.auth-status').css('margin-top', '10px');
						$('.layui-unselect').css('margin-top', '-2px');
					});
					authtree.on('deptChange(lay-check-auth)', function(data) {
						console.log('监听到显示层数改变', data);
					});
				}
			});
		},
		yes : function(index, layero) { // 保存按钮回调函数

			var roleName = $('#roleName').val();
			var roleDesc = $('#roleDesc').val();
			// 监听提交按钮
			form.on('submit(permission)', function(data) {
				$.ajax({
					type : 'POST',
					url : 'allotPermission.do',
					data : {
						roleId : id,
						roleName : roleName,
						roleDesc : roleDesc
					},
					dataType : 'json',
					success : function(data) {
						layer.msg('权限分配成功', {
							icon : 6
						});
						// 关闭弹出层
						layer.close(index);
						// 模拟点击确定按钮刷新页面数据
						$('.layui-laypage-btn').click();
					},
					error : function(data) {
						layer.msg('权限分配失败', {
							icon : 5
						});
						// 关闭弹出层
						layer.close(index);
						console.log(data);
					}
				});
			});
		},
		btn2 : function(index, layero) { // 取消按钮回调函数
			layer.close(index); // 关闭弹出层
		}
	});

}

/**
 * 绑定删除按钮事件
 * 
 * @param id
 *            要删除数据的id
 * @param index
 *            弹出层标识
 * @returns
 */
function bindingDelEvent(id, index) {
	$.ajax({
		type : 'POST',
		url : 'deleteResource.do',
		data : {
			id : id
		},
		dataType : 'json',
		success : function(data) {
			layer.msg('删除成功', {
				icon : 6,
			});
			parent.layer.close(index); // 关闭弹出层
			delDataRefreshTable(1); // 刷新表格
		},
		error : function(data) {
			layer.msg('删除失败', {
				icon : 5,
			});
		}
	});
}

/**
 * 添加数据之后自动刷新表格并跳转到新添加数据那页的方法
 * 
 * @returns
 */
function addDataRefreshTable() {
	// 模拟点击确定按钮刷新页面数据确保当前页面时最新的数据
	$('.layui-laypage-btn').click();
	// 计算出最后一页页码向上取整
	var lastPage = Math.ceil((total + 1) / limit);
	// 等待100ms页面加载完成后在跳转页数输入框中输入最后一页页码然后点击确定跳转到最后一页
	setTimeout(function() {
		$('.layui-laypage-skip>input').attr('value', lastPage);
		$('.layui-laypage-btn').click();
	}, 100);
}

/**
 * 此方法用于解决layui默认分页删除最后一页数据时出现bug的情况
 * 
 * @returns
 * @param delCount
 *            删除数据条数
 */
function delDataRefreshTable(delCount) {
	// 获取最后一页页码
	var lastPage = Math.ceil(total / limit);
	// 获取最后一页数据条数
	var lastPageCount = total % limit;
	// 如果删除的是最后一页的最后一条或者最后一页的所有数据的情况
	if (page === lastPage && lastPageCount === delCount) {
		$('.layui-laypage-skip>input').attr('value', lastPage - 1);
		$('.layui-laypage-btn').click();
	} else { // 不是最后一页的情况
		$('.layui-laypage-btn').click();
	}
}

/**
 * 此方法用于解决重复弹框并且将保存按钮类型变成提交
 * 
 * @param layero
 * @param layFilter
 *            lay-filter属性值
 * @returns
 */
function modifyButton(layero, layFilter) {
	// 解决按enter键重复弹窗问题
	$(':focus').blur();
	// 添加form标识
	layero.addClass('layui-form');
	// 将保存按钮改变成提交按钮
	layero.find('.layui-layer-btn0').attr({
		'lay-filter' : layFilter,
		'lay-submit' : ''
	});
}
