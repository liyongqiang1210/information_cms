layui.use([ 'form', 'layer' ], function() {
	var form = layui.form, layer = layui.layer, $ = layui.$;
	// 取出父页面隐藏的id值
	var id = parent.document.getElementById('left-content').contentDocument
			.getElementById('edit-id').defaultValue;
	$.ajax({
		type : 'POST',
		url : 'queryResourceById.do',
		data : {
			id : id
		},
		dataType : 'json',
		success : function(data) {
			var resource = data.data; // 获取功能对象
			echo(form, $, resource); // 数据回显
			form.render();
		},
		error : function(data) {
			console.log(data);
		}
	});
	// 更新页面渲染，否则会造成页面加载元素不完整
	form.render();
	// 自定义表单验证
	form.verify({
		name : function(value, item) { // 验证功能名
			var defaultName = $('#defaultName').val(); // 默认的name
			if (value !== defaultName) { // 判断功能名字是否发生改变
				if (!new RegExp("^[a-zA-Z0-9|\u4e00-\u9fa5\]{2,10}$")
						.test(value)) {
					return '权限名必须是2-10位中英文或者数字的字符';
				} else {
					code = 0; // 用来判断功能名是否存在
					$.ajax({
						type : 'POST',
						url : 'queryNameIsExist.do',
						data : {
							name : value
						},
						async : false,
						dataType : 'json',
						success : function(data) {
							if (!data.success) { // 功能名已经存在
								code = 1;
							}
						},
						error : function(data) {
							code = 2;
						}
					});
					// 根据code判断角色名是否存在
					if (code == 1) {
						return '功能名已经存在,请更换';
					} else if (code == 2) {
						return '出现异常,请联系管理员';
					}
				}
			}
		},
		url : function(value, item) { // 校验功能url
			var defaultUrl = $('#defaultUrl').val(); // 默认的url
			if (value !== defaultUrl) { // 判断功能url是否发生改变
				if (!new RegExp("^[a-zA-Z0-9/]{3,30}$").test(value)) {
					return 'url必须是3-30位英文和/组成的字符串';
				} else {
					code = 0; // 用来判断功能url是否存在
					$.ajax({
						type : 'POST',
						url : 'queryUrlIsExist.do',
						data : {
							name : value
						},
						async : false,
						dataType : 'json',
						success : function(data) {
							if (!data.success) { // 功能url已经存在
								code = 1;
							}
						},
						error : function(data) {
							code = 2;
						}
					});
					// 根据code判断功能url是否存在
					if (code == 1) {
						return '功能url已经存在';
					} else if (code == 2) {
						return '出现异常,请联系管理员';
					}
				}
			}
		},
		permission : function(value, item) { // 校验权限字符串
			var defaultPermission = $('#defaultPermission').val(); // 默认的权限字符串
			if (value !== defaultPermission) { // 判断功能权限字符串是否发生改变
				if (!new RegExp("^[a-zA-Z0-9]{2,20}$").test(value)) {
					return '权限字符串必须是2-20位英文字符';
				} else {
					code = 0; // 用来判断功能权限字符串是否存在
					$.ajax({
						type : 'POST',
						url : 'queryPermissionIsExist.do',
						data : {
							name : value
						},
						async : false,
						dataType : 'json',
						success : function(data) {
							if (!data.success) { // 功能权限字符串已经存在
								code = 1;
							}
						},
						error : function(data) {
							code = 2;
						}
					});
					// 根据code判断功能权限字符串是否存在
					if (code == 1) {
						return '功能权限字符串已经存在';
					} else if (code == 2) {
						return '出现异常,请联系管理员';
					}
				}
			}
		}
	});

	// 监听下拉按钮
	form.on('select(parentId)', function(data) {
		$('#parentIdInput').val(data.value); // 将下拉框选中值赋值给input用于下面取值
	});

	// 监听form表单提交
	form.on('submit(resourceSubmit)', function(data) {

		var id = parent.document.getElementById('left-content').contentDocument
				.getElementById('edit-id').defaultValue;
		var type = $('input[name="type"]:checked').val();
		var parentId = $('#parentIdInput').val() === '' ? 0 : $(
				'#parentIdInput').val();
		var available = $('#available').is(':checked') === true ? 1 : 0
		$
				.ajax({
					type : 'POST',
					url : 'updateResource.do',
					data : {
						id : id,
						name : $('#name').val(),
						url : $('#url').val(),
						type : type,
						parentId : parentId,
						permission : $('#permission').val(),
						available : available
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) { // 成功
							// 成功提示框
							parent.layer.msg('更新成功', {
								icon : 6,
							});
							// 调用父页面刷新页面方法
							$(window.parent.document).contents().find(
									"#left-content")[0].contentWindow
									.editDataRefreshTable();
						} else { // 失败
							// 失败提示框
							parent.layer.msg('更新失败', {
								icon : 5,
							});
						}
						parent.layer.closeAll('iframe'); // 关闭弹框
					},
					error : function(data) {
						console.log(data);
						// 异常提示
						parent.layer.msg('出现网络故障', {
							icon : 5
						});
						parent.layer.closeAll('iframe'); // 关闭弹框
					}
				});
		return false;
	});
});

/**
 * 数据回显
 * 
 * @param form
 * @param $
 * @param resource
 */
function echo(form, $, resource) {

	var type = resource.type; // 得到功能类型
	var parentId = resource.parentId; // 得到功能的父级功能id
	var available = resource.available; // 是否可用
	var name = resource.name; // 功能名
	var url = resource.url; // 功能url
	var permission = resource.permission; // 功能权限字符串
	// 回显功能名
	$('#name').val(name);
	$('#defaultName').val(name);
	// 回显功能url
	$('#url').val(url);
	$('#defaultUrl').val(url);
	// 回显功能字符串
	$('#permission').val(permission);
	$('#defaultPermission').val(permission);
	// 回显功能类型
	if (type === 'menu') {
		$('input[value="menu"]').attr('checked', true); // 选中
	} else {
		$('input[value="button"]').attr('checked', true); // 选中
	}
	// 回显是否可用
	if (parseInt(available) === 1) {
		$('#available').attr('checked', true);
	} else {
		$('#available').attr('checked', false);
	}
	// 下拉菜单回显
	$.ajax({
		type : 'POST',
		url : 'queryAllMenu.do',
		data : {
			type : 'menu'
		},
		dataType : 'json',
		success : function(data) {
			var array = data.data; // 取出后台返回值中的功能对象数据
			for (var i = 0; i < array.length; i++) { // 遍历拼接到select选项中
				// 判断是不是要回显的那个选项,如果是的话默认选中
				if (parseInt(parentId) === parseInt(array[i].id)) { // 选中
					$('#parentId').append(
							'<option name="parentName" selected="selected" value="'
									+ array[i].id + '">' + array[i].name
									+ '</option>');
				} else { // 不选中
					$('#parentId').append(
							'<option name="parentName" value="' + array[i].id
									+ '">' + array[i].name + '</option>');
				}
			}
			// 更新渲染
			form.render('select');
			// 设置下拉框高度
			$('dl.layui-anim').css('max-height', '100px');
		},
		error : function(data) {
			console.log('网络异常');
		}
	});

}
