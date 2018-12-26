<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back/css/zTreeStyle.css">
<meta charset="UTF-8">
<title>权限列表管理</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/back/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/back/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/back/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/back/js/jquery.ztree.excheck.js"></script>
</head>
<body>
	<H3 style="text-align: center;">菜单权限管理</H3>
	<div style="float: left; border: solid 3px; margin-left: 400px;">
		<ul>
			<li>
				角色
				<c:if test="${not empty requestScope.roleList}">
					<ul>
						<c:forEach items="${requestScope.roleList}" var="role">
							<li>
								<a href="#" style="text-decoration: none;" onclick="showMenu(${role.roleId})">${role.roleName}</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</ul>
	</div>
	<div style="float: left">
		<ul id="ztree" class="ztree"></ul>
		<p id="addButton"></p>
	</div>
	<input type="hidden" id="roleTypeId">
</body>
<script>

function updateRoleMenu(){
	 // 获得所有被选中的菜单
	 
	 var myTree = $.fn.zTree.getZTreeObj("ztree");
	 var selectVal = myTree.getCheckedNodes(true)
	 if(selectVal.length==0){
		 alert("请至少选择一个菜单");
		 return;
	 }
	 var menuIdData = "";
	 for(var i = 0; i < selectVal.length;i++){
		 menuIdData += selectVal[i].menuId + ":";
	 }
	 $.ajax({
		 type : "post",
		 url : "${pageContext.request.contextPath}/admin/updateRoleLimits.action",
		 data :
		 {
			"roleId" : $("#roleTypeId").val(),
			"roleMenuId" : menuIdData
		 },
		 success :function(data)
		 {
			 console.log(data);
			 if (data == "success"){
			 	window.alert("修改成功,要立即生效请重新登入");
			 }else if(data == "fail"){
				window.alert("修改失败");
			 }
		  }
		 
	 });
}
	
function showMenu(roleId){
	$.ajax(
			{
				type : "post",
				url : "${pageContext.request.contextPath}/admin/roleMenuInfo.action" ,
				data :
				{
					"roleId" : roleId
				},
				success : function(data)
				{
					var zTreeNodes = data;
					console.log(data);
					var setting = {
							data:{
								simpleData: {
									enable: true,// 设置用父子关系构造树  
									idKey: "menuId",//子ID
									pIdKey: "parentId",//父ID
									rootPId: 0// 最上级ID
								},
							key: {
									name: "menuName",
									checked: "selected"
								}
								
							},
							check: {
								enable: true
							}
						
					};
					var myTree = $.fn.zTree.init($("#ztree"), setting, zTreeNodes);
					myTree.expandAll(true);
					if(!($("#saveButton").length > 0)){
						$("#addButton").append("<input type='Button' id='saveButton' value='提交' onclick='updateRoleMenu()'>");
					}
					$("#roleTypeId").val(roleId);
					
				}
		});

	 }
</script>
</html>