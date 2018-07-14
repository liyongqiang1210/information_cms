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

// 打开背景幕布
function openBackdrop() {
	$("body").prepend("<div class='modal-backdrop fade in'></div>");
};

// 打开模态框
function openModel(html) {
	openBackdrop();
	$("body").prepend(html);
};

// 关闭背景幕布
function closeBackdrop() {
	$(".modal-backdrop").remove();
};

// 关闭模态框
function closeModel() {
	closeBackdrop(); // 关闭背景幕布
	$(".in").remove(); // 移除模态框
};