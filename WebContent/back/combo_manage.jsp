<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
	<!-- 当前页信息、总页数信息 -->
	<input type="hidden" id="comboName" value=""> 项目名称：
	<input type="text" id="query" class="abc input-default" placeholder=""
		value=""> &nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="query($(this))">查询</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="addnew">新增套餐</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" onclick="query($(this))">编辑</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="del"
		style="display: none">批量删除</button>
	<table class="table table-bordered table-hover definewidth m10" id="comboTable">
		<thead>
			<tr>
				<th style="display: none" id="delManage"><input type="checkbox"
					name="delboxs" id="delboxs"></th>
				<th>序号</th>
				<th>套餐Id</th>
				<th>套餐名称</th>
				<th>公司</th>
				<th>套餐价格</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.comboList}">
					<c:forEach items="${requestScope.pageInfo.datasets.comboList}"
						var="combo" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${combo.comboId}</td>
							<td>${combo.comboName}</td>
							<td>${combo.companyName}</td>
							<td>${combo.comboCost}</td>
							<td>
								<button type="button" class="btn btn-primary"
									onclick="delCombo(${combo.comboId})">删除</button>
								<button type="button" class="btn btn-primary"
									onclick="updateCombo(${combo.comboId})">修改套餐</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" align="center">暂无数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<p align="center">
		每页条数 <select id="onePage">
			<option value="5" selected>5</option>
			<option value="10">10</option>
			<option value="15">15</option>
		</select> &nbsp;&nbsp;
		<button type="button" class="btn btn-primary" onclick="query($(this))">刷新</button>
		<button type="button" class="btn btn-primary" onclick="query($(this))">上一页</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" onclick="query($(this))">下一页</button>
		当前页： <span id="currentPage">${requestScope.pageInfo.currentPage}</span>
		总条数： <span id="totalNum">${requestScope.pageInfo.totalNum}</span> 总页：
		<span id="totalPage">${requestScope.pageInfo.totalPage}</span>
	</p>
</body>
<script>
$(document).ready(function(){
	// 跳转到添加套餐列表
	$('#addnew').click(function()
			{
				window.location.href = "${pageContext.request.contextPath}/admin/addComboPage.action";
			});
	//条数变化
	$("#onePage").change(function(){
		responseData();
	});
	//复选框
	$("#delboxs").click(function(){
		delboxs();
	});
	//批量删除
	$("#del").click(function(){
		del();
	});
});

//批量删除
function del(){
    <!--获取选中复选框的值-->
		var delboxs = new Array();
	   $("input:checkbox[name = 'delbox']:checked").each(function(i){ 
		   delboxs[i] = $(this).val();	
		})
		//var delboxs = $("input[type=checkbox][name = delbox]:checked").val();
		console.log(delboxs);
		if(delboxs.length==0){
			window.alert('请选择要删除的对象');
			return;
		}
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delCombos.action',
				dataType : "text",
				data : {delboxs:delboxs},
				success : function(data)
				{
					if (data == "success")
					{
						alert("成功");
						responseData();
					} else
					{
						alert("失败");
					}
				},
				error : function(data)
				{
					window.alert("与服务器失去连接");
				}
			});
		}  
}
//批量选中复选框
	function delboxs(){
	var flag = $("#delboxs").is(':checked');
	$("[name='delbox']").attr("checked",flag);
}
	// 删除项目
	function delCombo(comboId)
	{
		window.alert(comboId);
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delCombo.action',
				dataType : "text",
				data : {"comboId" : comboId},
				success : function(data)
				{
					if (data == "success")
					{
						alert("成功");
						 $("#delboxs").attr("checked",false);//不管用
						responseData();
					} else
					{
						alert("失败");
					}
				},
				error : function(data)
				{
					window.alert("与服务器失去连接");
				}
			});
		}

	}
	// 跳转到更新项目页面
	function updateCombo(comboId)
	{
		window.location.href = "${pageContext.request.contextPath}/admin/updateComboPage.action?comboId="+ comboId+"&&currentPage="+$("#currentPage").text();
	}
	
	// 接收后台端改变的页面信息数据
	function query(btn)
	{
		var currentPage = $('#currentPage').text();
		var totalPage = $('#totalPage').text();
		var btnv=btn.text();
		if(btnv=="下一页"){
			currentPage = ++currentPage;
			if(currentPage>totalPage){
			window.alert('抱歉没有下一页了');
			return;
			}
		}else if(btnv=="上一页"){
			currentPage = --currentPage;
			if(currentPage<1){
			window.alert('抱歉没有上一页了');
			return;
			}
		}else if(btnv=="查询"){
			currentPage=1;
		}else if(btn="编辑"){
			$("#delManage").toggle();
			$("#del").toggle();
		}
		$('#currentPage').text(currentPage);
		responseData();
	}
	
	function responseData(){
		var flag=$("#delManage").is(":visible");//th是否是可显示
       // var temp1=$("#delManage").is(":hidden");//是否隐藏
		$.ajax(
		{
			type : "post",
			url : "${pageContext.request.contextPath}/admin/comboListPaging.action",
			dataType : 'json',
			data :
			{
				'currentPage' : $('#currentPage').text(),
				'onePage' :$("#onePage").val(),
				'comboName' : $('#query').val()//查询条件
			},
			success : function(data)
			{
				if(data.totalNum == 0)
				{
					$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
					return;
				}
				var str = "";
				var dataList = data.datasets.comboList;
				//删除过后本页没有条数
				if(dataList.length==0){
					var currentPage = parseInt($('#currentPage').text())-1;
					$('#currentPage').text(currentPage);
					responseData();
				}
				for(var i = 0; i < dataList.length; i++) 
				{
				  	str += "<tr>";
					str += flag==false?"":"<td><input type='checkbox' name='delbox' id='delbox' value='"+dataList[i].comboId+"'></td>";
					str += "<td>" + (i+1) + "</td>";
					str += "<td>" + dataList[i].comboId+ "</td>";
					str += "<td>" + dataList[i].comboName + "</td>";
					str += "<td>" + dataList[i].companyName+ "</td>";
					str += "<td>" + dataList[i].comboCost + "</td>";
					str += "<td><button type='button' class='btn btn-primary' onclick=\"delCombo('"+dataList[i].comboId+"')\">删除</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"updateCombo("+dataList[i].comboId+")\">修改套餐</button></td>";
					str += "</tr>";
				}
				$('#myTbody').html(str);
				// 设置当前页和总页信息
				$('#totalPage').text(data.totalPage);
				$('#totalNum').text(data.totalNum);
			},
			error : function(data)
			{
				window.alert("与服务器失去连接！！");
			}
		});
	}
</script>
</html>