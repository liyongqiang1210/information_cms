// 初始化表单
layui.use([ 'form', 'layer' ], function() {
	var form = layui.form, layer = layui.layer, $ = layui.$;
	$.ajax({
		type : 'POST',
		url : 'queryAllMenu.do',
		data : {
			type : 'menu'
		},
		dataType : 'json',
		success : function(data) {
			var array = data.data; // 取出后台返回值中的数据
			for (var i = 0; i < array.length; i++) {
				// 为Select追加一个Option(下拉项)
				$('#parentId').append(
						'<option name="parentName" value="' + array[i].id
								+ '">' + array[i].name + '</option>');
			}
			form.render('select');
			// 设置下拉框高度
			$('dl.layui-anim').css('max-height', '100px');
		},
		error : function(data) {
			console.log('网络异常');
		}
	});
	// 更新页面渲染，否则会造成页面加载元素不完整
	form.render();
	// 自定义表单验证
	form
			.verify({
				name : function(value, item) { // 验证功能名
					var defaultName = $('#defaultName').val(); // 默认的name
					if (value !== defaultName || 'default' === defaultName) { // 判断功能名字是否发生改变
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
					if (value !== defaultUrl || 'default' === defaultUrl) { // 判断功能url是否发生改变
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
					if (value !== defaultPermission
							|| 'default' !== defaultPermission) { // 判断功能权限字符串是否发生改变
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
	form.on('submit(save)', function(data) {
		$
				.ajax({
					type : 'POST',
					url : 'createResource.do',
					data : {
						name : $('#name').val(),
						url : $('#url').val(),
						type : $('input[name]:checked').val(),
						parentId : $('#parentIdInput').val() === '' ? 0 : $(
								'#parentIdInput').val(),
						permission : $('#permission').val(),
						available : $('#available').is(':checked') === true ? 1
								: 0
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) { // 成功
							// 成功提示框
							parent.layer.msg('添加成功', {
								icon : 6,
							});
							// 调用id为left-content的iframe页面的js方法
							$(window.parent.document).contents().find(
									"#left-content")[0].contentWindow
									.addDataRefreshTable();
						} else { // 失败
							// 失败提示框
							parent.layer.msg('添加失败', {
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