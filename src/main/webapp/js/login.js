$(function() {
	
	// 防止iframe框架局部跳转到登录页面
	if (window != top) {
		top.location.href = location.href;
	}
	
	// 随机获取一个验证码
	$("img.checkCode").prop(
			"src",
			"http://localhost:8080/Information_cms/login/getCheckCode.do?number="
					+ Math.random());

	// 用户登录 
	$("#login")
			.click(
					function() {
						var username = $("#username").val(); // 用户名
						var password = $("#password").val(); // 密码
						var checkCode = $("#checkCode").val(); // 验证码
						var rememberMe = $("#rememberMe").prop("checked"); // 记住我

						if (username == null || username == "") {
							promptMessage("form", "用户名不能为空。");
							return;
						} else if (password == null || password == "") {
							promptMessage("form", "密码不能为空。");
							return;
						} else if (checkCode == "" || checkCode == null) {
							promptMessage("form", "验证码不能为空。");
							return;
						} else {
							$(".alert").remove();
							// 异步登录
							$
									.ajax({
										type : "POST",
										url : "http://localhost:8080/Information_cms/login/toLogin.do",
										data : {
											username : username,
											password : password,
											rememberMe : rememberMe,
											checkCode : checkCode
										},
										dataType : "json",
										success : function(data) {

											var state = data.success; // 服务器响应状态
											var message = data.message; // 服务器响应的信息

											if (state) { // 登录成功，跳转到主页

												// 设置cookie
												// setCookie("username",username,30);

												// 页面跳转到主页
												$(location)
														.prop('href',
																'http://localhost:8080/Information_cms/html/index.html');
											} else { // 登录失败，提示错误信息
												// 将错误信息输出到登录页面提示信息中
												promptMessage("form", message);
											}
										},
										error : function(data) {
											promptMessage("form", "请刷新页面后重新登录");
										}
									});
						}

					});

	/* 验证码刷新 */
	$("#updateCode").click(
			function() {
				// 此处加上随机数是为了告诉浏览器要发送一个新的请求
				$("img.checkCode").prop(
						"src",
						"http://localhost:8080/Information_cms/login/getCheckCode.do?number="
								+ Math.random());
			});
});
