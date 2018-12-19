<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="/webtest/back/js/jquery.min.js"></script>
</head>
<body>
	<section class="LiKoH_contact">
		<div class="contact">
			<div class="contactmain"
				data-scroll-reveal="enter bottom over 1s and move 300px after 0.3s">
				<script type="text/javascript"
					src="http://api.map.baidu.com/api?v=2.0&ak=fU2laY39cs563lVIODEEvg1FyyFZ31zC"></script>
				<div id="l-map"
					style="width: 1200px; height: 500px; font-size: 12px"></div>
				<script type="text/javascript">
					var sContent = "<font color='#f10'>厦门市传一信息科技有限公司</font><br>地址：福建省厦门市思明区软件园二期观日路56号1楼1<br><br>邮编：361000<br><br>电话：400-000-000";
					var map = new BMap.Map("l-map"); // 创建地图实例
					var point = new BMap.Point(118.193224, 24.488574); // 创建点坐标
					map.centerAndZoom(point, 19);
					map.enableScrollWheelZoom(); // 初始化地图，设置中心点坐标和地图级别

					map.addControl(new BMap.NavigationControl());
					map.addControl(new BMap.ScaleControl());
					map.addControl(new BMap.OverviewMapControl());
					map.addControl(new BMap.MapTypeControl());

					var mapType2 = new BMap.MapTypeControl({
						anchor : BMAP_ANCHOR_TOP_LEFT
					});
					var overView = new BMap.OverviewMapControl();
					var overViewOpen = new BMap.OverviewMapControl({
						isOpen : true,
						anchor : BMAP_ANCHOR_BOTTOM_RIGHT
					});
					//添加地图类型和缩略图

					map.setCurrentCity("厦门"); //由于有3D图，需要设置城市哦
					map.addControl(overView); //添加默认缩略地图控件
					map.addControl(overViewOpen); //右下角，打开

					var navigationControl = new BMap.NavigationControl({
						// 靠左上角位置
						anchor : BMAP_ANCHOR_TOP_LEFT,
						// LARGE类型
						type : BMAP_NAVIGATION_CONTROL_LARGE,
						// 启用显示定位
						enableGeolocation : true
					// 会多出一个点
					});
					map.addControl(navigationControl);
					// 添加定位控件
					var geolocationControl = new BMap.GeolocationControl();
					geolocationControl.addEventListener("locationSuccess",
							function(e) {
								// 定位成功事件
								var address = '';
								address += e.addressComponent.province;
								address += e.addressComponent.city;
								address += e.addressComponent.district;
								address += e.addressComponent.street;
								address += e.addressComponent.streetNumber;
							});
					geolocationControl.addEventListener("locationError",
							function(e) {
								// 定位失败事件
								alert(e.message);
							});
					map.addControl(geolocationControl);

					var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
					map.openInfoWindow(infoWindow, point); // 开启信息窗口

					var marker = new BMap.Marker(
							point,
							{
								icon : new BMap.Icon(
										"http://api.map.baidu.com/lbsapi/createmap/images/icon.png",
										new BMap.Size(20, 25), {
											imageOffset : new BMap.Size(-46,
													-21)
										})
							});
					map.addOverlay(marker); // 将标注添加到地图中
					marker.addEventListener("click", function() { // 给标注添加点击事件
						this.openInfoWindow(infoWindow);
					});
				</script>
			</div>
		</div>
	</section>
</body>
</html>