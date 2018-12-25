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
			<td width="10%" class="tableleft">套餐名称</td>
			<td><input type="text"  id="comboName" /></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">公司Id</td>
			<td>
			<input type="text"  id="companyId" value="0"/>系统
			</td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">套餐价格</td>
			<td><select id="discount">
			<option value="1">100%</option>
			<option value="0.9">90%</option>
			<option value="0.8">80%</option>
			</select>
			<input type="text"  id="comboCost" readonly="readonly"/>
			<input type="button" class="btn btn-primary" id="price" value="手动输入" />
			</td>
		</tr>
		<tr>
			<td class="tableleft"></td>
			<td>
			<input type="button" class="btn btn-primary" id="choose" value="查看项目" />
				<button type="button" class="btn btn-primary" 
					onclick="addCombo()">提交</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid"
					onclick="backComboList()">返回列表</button>
			</td>
		</tr>
	</table>
	</div>
	<div class="col-md-6" id="data">
	<h3>套餐价格:</h3><span id="comboPrice"></span>
	<h3>选择项目:</h3>
	<ul id="dataTree" class="ztree"></ul>
</div>
</div>
</div>
</body>
<script>
$(document).ready(function(){
	var flag=true;
	createTree();//加载
	$("#choose").click(function(){//隐藏项目
		$("#data").toggle();
	});

	$("#price").click(function(){
		if(flag){
			flag=false;
		}else{
			flag=true;
		}
		$("#comboCost").attr("readOnly",flag); 
	});
	$("#discount").change(function(){
		comboPrice();
	});
});	

//计算价格
function comboPrice(){
	var treenode = $.fn.zTree.getZTreeObj("dataTree");//获取数组
	var chkNodeArr =treenode.getCheckedNodes(true);
	var comboPrice = 0;
	for(var i=0;i<chkNodeArr.length;i++){
		comboPrice+=chkNodeArr[i].projectPrice;
	}
	var discount = $("#discount").val();
	comboPrice = parseInt(comboPrice*discount);
	var str = discount==1?'':'   折扣:'+discount;
	$("#comboPrice").text(comboPrice+'元'+str);
}
//创建项目树
function createTree(){
	var setting={
			check:{
				enable:true,
				chkStyle:"checkbox"
			},
			 callback: {//ztree触发事件
		            onCheck: comboPrice
		        },
			data:{
				key:{name:"projectName",
				},
				simpleData:{
					enable: true,
					idKey:"projectId",
					//pIdKey:"PARENT_ID",
					//rootId:0 
				}
			}	
	}
		
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/admin/queryProjects.action",
		data:{"comboId":0},
		dataType:"json",
		success:function(data){
			console.log(data);
			zTree = $.fn.zTree.init($("#dataTree"),setting,data);
			zTree.expandAll(true);
		}
	});
}

//添加
	function addCombo() {
	    if($("#comboName").val()==''){
	    	window.alert("套餐名不能为空");
	    	return;
	    }
		var treenode = $.fn.zTree.getZTreeObj("dataTree");//获取数组
		var chkNodeArr =treenode.getCheckedNodes(true);
		var projects = new Array();
		var comboCost = 0;
		for(var i=0;i<chkNodeArr.length;i++){
			projects[i]=chkNodeArr[i].projectId;
			comboCost+=chkNodeArr[i].projectPrice;
		}
		if(projects.length==0){
			window.alert('请选择合适的项目');
			return;
		}
		//计算套餐价格 手动输入或者自动计算
		if($("#comboCost").val()==''){
			var discount=$("#discount").val();//折扣
			comboCost = parseInt(comboCost*discount);
			$("#comboCost").val(comboCost);
		}
		window.alert($("#comboCost").val());
		var combo={
				"comboName" : $("#comboName").val(),
				"companyId": $("#companyId").val(),
				"comboCost": $("#comboCost").val(),
				"projects":projects
		};
		$.ajax({
			type : "post",
			url : '${pageContext.request.contextPath}/admin/addCombo.action',
			contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
			dataType : "text",
			data :JSON.stringify(combo),
			success : function(data) {
				if (data == "success") {
					window.alert('添加成功');
					createTree();//加载
					$("#comboName").val('');
					$("#comboCost").val('');
				} else {
					window.alert('添加失败');
				}
			},
			error : function(data) {
				window.alert("与服务器失去连接");
			}
		});
	}
	function backComboList() {
		window.location.href = "${pageContext.request.contextPath}/admin/comboManagerPage.action?currentPage=1";
	}
</script>
</html>