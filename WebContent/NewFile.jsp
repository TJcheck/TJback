<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/webtest/back/js/jquery.min.js"></script>
<script type="text/javascript" src="/webtest/back/js/LodopFuncs.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<form name="Form2" action="/webtest/file/fileUpload.action"
		method="post" enctype="multipart/form-data">
		<h1>采用multipart提供的file.transfer方法上传文件</h1>
		<input type="file" name="file"> <input type="submit"
			value="upload" />
	</form>
	<a href="javascript:show()">显示条形码</a>
	<a href="/webtest/admin/adminMainPage.action">登录</a>
	<object id="LODOP1"
		classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=800
		height=400>
		<param name="Caption" value="显示区">
		<param name="Border" value="0">
		<param name="Color" value="white">
		<embed id="LODOP_EM1" TYPE="application/x-print-lodop" width=800
			height=400 border=0 Color="white" PLUGINSPAGE="install_lodop.exe">
	</object>

</body>
<script type="text/javascript">
	var LODOP;
	function show() {
		LODOP = getLodop(document.getElementById('LODOP1'), document
				.getElementById('LODOP_EM1'));
		LODOP.PRINT_INITA(0, 0, 800, 1600, "打印控件功能演示_Lodop功能_打印条码");
		LODOP.ADD_PRINT_BARCODE(28, 34, 227, 147, "QRCode", "http://www.baidu.com");
		LODOP.SHOW_CHART();

	}
	function print() {
		show();
		LODOP.PREVIEW();
	}
</script>
</html>