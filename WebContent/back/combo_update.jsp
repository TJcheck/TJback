<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/zTreeStyle/zTreeStyle.css">
<head>
<title></title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.ztree.excheck.js"></script>
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
	<div class="container">
		<div class="rows">
			<div class="col-md-6">
					<table class="table table-bordered table-hover definewidth m10">
						<tr>
							<td width="10%" class="tableleft">套餐名称</td>
							<td><input type="hidden" id="comboId"
								value='${combo.comboId}'> <input type="text"
								id="comboName" value='${combo.comboName}' /></td>
						</tr>
						<tr>
							<td width="10%" class="tableleft">套餐价格</td>
							<td><input type="text" id="comboCost"
								value='${combo.comboCost}' /></td>
						</tr>
						<tr>
							<td class="tableleft"></td>
							<td><input type="button" class="btn btn-primary" id="choose"
								value="查看项目">
								<button type="button" class="btn btn-primary"
									onclick="updateCombo()">提交更改</button> &nbsp;&nbsp;
								<button type="button" class="btn btn-success" name="backid"
									onclick="backComboList()">返回列表</button></td>
						</tr>
					</table>
			</div>
			<div class="col-md-6" id="ztree">
				<h3>选择项目:</h3>
				<ul id="dataTree" class="ztree"></ul>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
			createTree();//加载
			$("#choose").click(function(){
				$("#ztree").toggle();
			});
	});
	//创建细项树
	function createTree() {
		var setting = {
			check : {
				enable : true,
				chkStyle : "checkbox"
			},
			data : {
				key : {
					name : "projectName",
					checked : "checked"
				},
				simpleData : {
					enable : true,
					idKey : "projectId",
				//pIdKey:"PARENT_ID",
				//rootId:0 
				}
			}
		}

		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/admin/queryProjects.action",
			data : {
				"comboId" : $("#comboId").val()
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				zTree = $.fn.zTree.init($("#dataTree"), setting, data);
				zTree.expandAll(true);
			}
		});
	}

	//提交更改
	function updateCombo() {
		alert('点击提交按钮');
		var treenode = $.fn.zTree.getZTreeObj("dataTree");//获取数组
		var chkNodeArr =treenode.getCheckedNodes(true);
		var projects = new Array();
		for(var i=0;i<chkNodeArr.length;i++){
			projects[i]=chkNodeArr[i].projectId;
			console.log(chkNodeArr[i]);
		}
		var combo={
				"comboId":$("#comboId").val(),
				"comboName" : $("#comboName").val(),
				"companyId": $("#companyId").val(),
				"comboCost": $("#comboCost").val(),
				"projects":projects
		};
		$.ajax({
					type : "post",
					url : '${pageContext.request.contextPath}/admin/updateCombo.action',
					contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
					dataType : "text",
					data : JSON.stringify(combo),//表单序列化成json
					success : function(data) {
						window.alert(data);
						if (data == "success") {
							alert("修改成功");
						} else {
							alert("修改失败");
						}
					},
					error : function(data) {
						window.alert("与服务器失去连接");
					}

				});
	}
	function backComboList() {
		window.location.href = "${pageContext.request.contextPath}/admin/comboManagerPage.action";
	}
	</script>
</html>