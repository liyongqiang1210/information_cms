// 设置cookie
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}

// 获取cookie
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name) == 0)
			return c.substring(name.length, c.length);
	}
	return "";
}

// 关闭模态框
function closeModal() {
	$("div.main", window.parent.document).css("z-index", ""); // 将父页面main容器层级设置为
	$(".modal-backdrop", window.parent.document).remove(); // 删除父页面的背景幕布
	$(".in").remove(); // 移除模态框
};

// 打开模态框
function openModal(title, content, buttonClass, methodName) {
	var a, b, c, d;
	title == undefined || title == "" ? a = "请填写标题" : a = title;
	content == undefined || content == "" ? b = "请填写内容" : b = content;
	buttonClass == undefined || buttonClass == "" ? c = "btn-primary"
			: c = buttonClass;
	methodName == "" || methodName == undefined ? d = "" : d = methodName;
	// 弹出框html代码
	var html = "<div class='modal fade in' id='' role='dialog' aria-labelledby='myModalLabel' aria-hidden='false' style='display: block;'>"
			+ "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><button type='button' class='close' onclick='closeModal()'"
			+ " data-dismiss='modal' aria-hidden='true'>×</button><h4 class='modal-title' id=''>"
			+ a
			+ "</h4></div><div class='modal-body'>"
			+ b
			+ "</div><div class='modal-footer'><button type='button' class='btn btn-default' onclick='closeModal()' data-dismiss='modal'>取消</button>"
			+ " <button type='button' class='btn "
			+ c
			+ "' onclick='"
			+ d
			+ "'>确认</button></div></div></div></div>";

	$("body", window.parent.document).prepend(
			"<div class='modal-backdrop fade in'></div>"); // 弹窗时将父页也设置一个背景幕布
	$("div.main", window.parent.document).css("z-index", "1040"); // 将子页面容器的层级设为1040
	$("body").prepend("<div class='modal-backdrop fade in'></div>");// 打开子页面的背景幕布
	$("body").prepend(html);// 将页面内容添加到弹框中
};

// 表单验证提示错误信息方法
function promptMessage(odj, errorMessage) {
	$(".alert").remove();
	$(odj)
			.prepend(
					"<div class='alert alert-danger alert-dismissable'><button type='button'"
							+ "class='close' data-dismiss='alert' aria-hidden='true'> &times;</button>"
							+ errorMessage + "</div>");
	// 2秒后删除提示信息
	setTimeout(function() {
		$(".alert").remove();
	}, 2000);
};

var t = setTimeout(closePromptBox, 5000);// 设置定时关闭信息提示窗口函数

// 打开右下角信息提示框
function openPromptBox(content, type, iconClass) {

	// 打开信息提示窗口之前如果存在定时器先清理一次定时函数如果存在提示框就先删除一次提示框
	$(".prompt-box").remove();
	clearTimeout(t);

	var a, b, c, d;
	content == "" || content == undefined ? a = "请填写提示信息" : a = content; // 提示内容
	type == "" || type == undefined ? b = "panel-primary" : b = type; // 提示框类型
	iconClass == "" || iconClass == undefined ? c = "" : c = iconClass; // 提示图标类型
	// 提示图标颜色
	if (iconClass == "icon-check-circle") {
		d = "#00FF66";
	} else if (iconClass == "icon-warning-circle") {
		d = "#FF9900";
	} else if (iconClass == "icon-close-circle") {
		d = "#CC0033";
	}
	$("body")
			.append(
					"<div class='prompt-box' style='position: absolute; width: 260px; height: 0px; bottom: 40px; right: 0px; box-shadow:0px 0px 5px #888888; border:1px solid #888888;'>"
							+ "<div class='panel "
							+ b
							+ "' style='margin-bottom: 0px; '><div class='panel-heading' style='height: 40px;'>提示信息"
							+ "<button type='button' class='close' onclick='closePromptBox()' data-dismiss='alert' aria-label='Close'>"
							+ "<span aria-hidden='true'>&times;</span></button></div><div class='panel-body' style='height: 70px;'> <div style='float:left; height:40px; line-height:40px;'>"
							+ "<i class='icon iconfont "
							+ c
							+ "' style='font-size:40px; color:"
							+ d
							+ "'>&nbsp;</i></div><div style='float:left; height:40px; line-height:40px;'>"
							+ a + "</div></div></div>");
	$(".prompt-box").animate({
		height : "110px"
	}, 400);

	t = setTimeout(closePromptBox, 5000); // 重新设置定时函数(必须重新设置否则提示框不会自动关闭)

}

// 移除信息提示框
function removePromptBox() {
	setTimeout(closePromptBox, 5000);
};

// 关闭右下角信息提示框
function closePromptBox() {
	$(".prompt-box").animate({
		height : "0px"
	}, 400, function() {
		$(".prompt-box").remove();
	});

}
