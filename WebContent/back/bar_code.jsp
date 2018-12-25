<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/JsBarcode.all.min.js"></script>
<meta charset="UTF-8">
<title>打印条形码</title>
</head>
<body>
<hr>
请输入要转成条形码的数字：<input type="text"  id="barcodeValue"> &nbsp;<a href="#" onClick="barcodeGen();" ><span> 生成条形码</span></a><br>
<!--startprint-->
<img id="bcode"/>
<!--endprint-->
<hr>
<a href="#" onClick="myprint();" ><span> 打  印</span></a><br>
</body>
<script type="text/javascript">
 	function myprint(){ 
	  //直接调用浏览器打印功能
	   bdhtml=window.document.body.innerHTML; 
	   //定义打印区域起始字符，根据这个截取网页局部内容     
	   sprnstr="<!--startprint-->"; //打印区域开始的标记
	   eprnstr="<!--endprint-->";   //打印区域结束的标记  
	   prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
	   prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));     
	   window.document.body.innerHTML=prnhtml;   
	   //开始打印
	   window.print();
	   //还原网页内容     
	   window.document.body.innerHTML=bdhtml; 
	} 
	function barcodeGen(){
    	var barvalue=$("#barcodeValue").val();
    	if(barvalue==""){
    		alert("请输入条形码字符串！！")
    	}else{
        	$("#bcode").JsBarcode(barvalue);
    	}
	}
</script>
</html>