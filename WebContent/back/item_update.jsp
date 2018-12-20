<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.sorted.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/ckform.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.serializejson.min.js"></script>
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
	<form id="myForm" class="definewidth m20">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">细项名称</td>
				<td>
				<input type="hidden" name="itemId" value='${item.itemId}'>
				<input type="text" name="itemName" value='${item.itemName}' />
				</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">细项单位</td>
				<td>
				<input type="text" name="itemUnit" value='${item.itemUnit}' />	
				</td>
			</tr>
				<tr>
				<td width="10%" class="tableleft">细项下限值</td>
				<td>
				<input type="text" name="itemLess" value='${item.itemLess}' />	
				</td>
			</tr>
				<tr>
				<td width="10%" class="tableleft">细项上限值</td>
				<td>
				<input type="text" name="itemHigh" value='${item.itemHigh}' />	
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="button" class="btn btn-primary" type="button" onclick="addItem()">提交</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" onclick="backItemList()">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	function addItem()
	{
		$.ajax(
		{
			type : "post",
			url : '${pageContext.request.contextPath}/admin/updateItem.action',
			contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
			dataType : "text",
			data : JSON.stringify($('#myForm').serializeJSON()),//表单序列化成json
			success : function(data)
			{
				window.alert(data);
				if (data == "success")
				{
					alert("修改成功");
				} else
				{
					alert("修改失败");
				}
			},
			error : function(data)
			{
				window.alert("与服务器失去连接");
			}

		});
	}
	function backItemList()
	{
		window.location.href = "${pageContext.request.contextPath}/admin/itemManagerPage.action";
	}
</script>
</html>