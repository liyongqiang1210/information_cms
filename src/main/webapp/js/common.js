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

// 关闭背景幕布
function closeBackdrop() {
	$("div.main",window.parent.document).css("z-index","");
	$(".modal-backdrop",window.parent.document).remove();
};

// 关闭模态框
function closeModal() {
	closeBackdrop(); // 关闭背景幕布
	$(".in").remove(); // 移除模态框
};


// 打开模态框
function openModal(title, content, buttonClass, methodName) {
	var a, b, c, d;
	title == undefined || title == "" ? a = "请填写标题" : a = title;
	content == undefined || content == "" ? b = "请填写内容" : b = content;
	buttonClass == undefined || buttonClass == "" ? c = "btn-primary"
			: c = buttonClass;
	methodName == "" || methodName == undefined ? d = "add()"
			: d = methodName;
	// 弹出框html代码
	var html = "<div class='modal fade in' id='' role='dialog' aria-labelledby='myModalLabel' aria-hidden='false' style='display: block;'>"
			+ "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><button type='button' class='close' onclick='closeModal()'"
			+ " data-dismiss='modal' aria-hidden='true'>×</button><h4 class='modal-title' id=''>"
			+ a
			+ "</h4></div><div class='modal-body'>"
			+ b
			+ "</div><div class='modal-footer'><button type='button' class='btn btn-default' onclick='closeModal()' data-dismiss='modal'>关闭</button>"
			+ " <button type='button' class='btn "
			+ c
			+ "' onclick='addUser()'>保存</button></div></div></div></div>";

	$("body",window.parent.document).prepend("<div class='modal-backdrop fade in'></div>"); // 弹窗时将父页也设置一个背景幕布
	$("div.main",window.parent.document).css("z-index","1040"); // 将子页面容器的层级设为1040
	$("body").prepend("<div class='modal-backdrop fade in'></div>");// 打开子页面的背景幕布
	$("body").prepend(html);// 将页面内容添加到弹框中
};


