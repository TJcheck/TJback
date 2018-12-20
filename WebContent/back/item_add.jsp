<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加细项</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/jquery.sorted.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/ckform.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/jquery.serializejson.min.js"></script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<td width="10%" class="tableleft">细项名称</td>
			<td><input type="text" name="itemName" id="itemName" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">测量单位</td>
			<td><input type="text" name="itemUnit" id="itemUnit" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">下限值</td>
			<td><input type="text" name="itemLess" id="itemLess" /></td>
		</tr>
			<tr>
			<td width="10%" class="tableleft">上限值</td>
			<td><input type="text" name="itemHigh" id="itemHigh" /></td>
		</tr>
		<tr>
			<td class="tableleft"></td>
			<td>
				<button type="button" class="btn btn-primary" type="button"
					onclick="addItem()">提交</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid"
					onclick="backItemList()">返回列表</button>
			</td>
		</tr>
	</table>
</body>
<script>
	function addItem() {
		var item={"itemName" : $("#itemName").val(),
				"itemUnit": $("#itemUnit").val(),
				"itemLess": $("#itemLess").val(),
				"itemHigh": $("#itemHigh").val()	
		};
		$.ajax({
			type : "post",
			url : '${pageContext.request.contextPath}/admin/addItem.action',
			contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
			dataType : "text",
			data :JSON.stringify(item),
			success : function(data) {
				if (data == "success") {
					window.alert('添加成功');
				} else {
					window.alert('添加失败');
				}
			},
			error : function(data) {
				window.alert("与服务器失去连接");
			}
		});
	}
	function backItemList() {
		window.location.href = "${pageContext.request.contextPath}/admin/itemManagerPage.action";
	}
</script>
</html>