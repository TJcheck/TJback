<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/zTreeStyle/zTreeStyle.css">
<head>
<title>添加项目</title>
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
			<td width="10%" class="tableleft">项目名称</td>
			<td><input type="text" name="projectName" id="projectName" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">科室</td>
			<td>
				<select id="deptId">
				<option value="-1">-请选择-</option>
				<c:if test="${not empty deptList}">
				<c:forEach items='${deptList}' var="dept" varStatus="st">
				<option value='${dept.deptId}'>${dept.deptName}</option>
				</c:forEach>
				</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">项目单价</td>
			<td><input type="text" name="projectPrice" id="projectPrice" /></td>
		</tr>
		<tr>
			<td class="tableleft"></td>
			<td>
			<input type="button" class="btn btn-primary" id="choose" value="选择细项">
				<button type="button" class="btn btn-primary" 
					onclick="addProject()">提交</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid"
					onclick="backProjectList()">返回列表</button>
			</td>
		</tr>
	</table>
	</div>
	<div class="col-md-6">
	<h3>选择细项:</h3>
	<ul id="menuTree" class="ztree"></ul>
</div>
</div>
</div>
</body>
<script>
$(document).ready(function(){
	$("#choose").click(function(){
		createTree();//加载
	});
});	
function createTree(){
	var setting={
			check:{
				enable:true,
				chkStyle:"checkbox"
			},
			data:{
				key:{name:"itemName",
				},
				simpleData:{
					enable: true,
					idKey:"itemId",
					//pIdKey:"PARENT_ID",
					//rootId:0 
				}
			}	
	}
		
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/admin/queryItems.action",
		data:{"projectId":0},
		dataType:"json",
		success:function(data){
			console.log(data);
			zTree = $.fn.zTree.init($("#menuTree"),setting,data);
			zTree.expandAll(true);
		}
	});
}

//添加
	function addProject() {
		alert('点击提交按钮');
		var treenode = $.fn.zTree.getZTreeObj("menuTree");//获取数组
		var chkNodeArr =treenode.getCheckedNodes(true);
		//var items = "";
		var items = new Array();
		for(var i=0;i<chkNodeArr.length;i++){
			//str+=chkNodeArr[i].itemId+":";
			items[i]=chkNodeArr[i].itemId;
			console.log(chkNodeArr[i]);
		}
		window.alert(items);
		var project={
				"projectName" : $("#projectName").val(),
				"deptId": $("#deptId").val(),
				"projectPrice": $("#projectPrice").val(),
				"items":items
		};
		$.ajax({
			type : "post",
			url : '${pageContext.request.contextPath}/admin/addProject.action',
			contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
			dataType : "text",
			data :JSON.stringify(project),
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
	function backProjectList() {
		window.location.href = "${pageContext.request.contextPath}/admin/projectManagerPage.action";
	}
</script>
</html>