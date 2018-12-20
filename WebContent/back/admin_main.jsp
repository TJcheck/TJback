<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" href="#" />
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/back/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/back/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/back/css/main-min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/back/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/assets/js/bui-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/assets/js/config-min.js"></script>
</head>
<body>
	<input id="menuList" type="hidden" value="${requestScope.menuList}">
	<div class="header">
		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			欢迎您，
			<span class="dl-log-user">root</span>
			<a href="/chinapost/index.php?m=Public&a=logout" title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected">
					<div class="nav-item-inner nav-home">菜单</div>
				</li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<div style="text-align: center;">
		<p>
			来源：
			<a href="http://www.aspku.com/" target="_blank">源码之家</a>
		</p>
	</div>
</body>
<script>
	var menuList = ${requestScope.menuList};
	var str = new Array();
	var num = 1;
	var flag = new Object();
	var menu = new Array();
	for (var i = 0; i < menuList.length; i++)
	{
		/* 三级菜单数组 */
		var items = new Array();
		/* 二级菜单对象 */
		var obj = new Object();
		for (var j = 0; j < menuList.length; j++)
		{
			var object = new Object();
			/* 找出所有的三级菜单 */
			if (menuList[j].parentId == menuList[i].menuId)
			{
				num += 1;
				object.id = num;
				object.text = menuList[j].menuName;
				object.href = "${pageContext.request.contextPath}" + menuList[j].menuUrl;
				console.log(object.href);
				items.push(object);
			}
		}
		// 
		if (menuList[i].parentId == 0)
		{
			obj.text = menuList[i].menuName;
			obj.items = items;
			menu.push(obj);
		}
	}
	flag.id = 1;
	flag.menu = menu;
	str.push(flag);

	console.log(str);
	BUI.use('common/main', function()
	{

		var config = str;
		new PageUtil.MainPage(
		{
			modulesConfig : config
		});
	});
</script>
</html>