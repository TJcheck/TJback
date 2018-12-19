//function showDialog(title,msg) {
//	$("#myModal").find(".modal-header h3").html(title);
//    $("#myModal").attr('class','modal');
//    $("#myModal").find(".modal-body p").html(msg);
//    setTimeout(function(){
//        $("#myModal").attr('class','modal hide');
//    },3000);
//}

//function keydown(e)
//{
//    var e = e||event;
//    var currKey = e.keyCode||e.which||e.charCode;
//    if(currKey == 13)
//    {
//        checkLogin();
//    }
//}

//function checkLogin(){
//    var userName = $('#userName').val();;
//    var userPwd = $('#userPwd').val();
//    $.ajax({
//		url : "${pageContext.request.contextPath}/user/login.action",
//		data : $('#form').serialize(),
//		//非表单提交数据 
//	//	data:{
//	//		"adminName":adminName,
//	//		"adminPwd":adminPwd
////	},
//		type : "post",
//		success : function(date) {
//			//根据后台返回前台的msg给提示信息加HTML
//			if (date==1) {
//				alert("登录成功");
//				window.location.href="${pageContext.request.contextPath}/front/main.jsp";
//			} else if(date==2){
//				window.alert("该账户已被禁用");
//			} else {
//				window.alert("登录失败，请输入正确的登录信息")
//			}
//		}
//	});
//}

function validatePwd(str){   
    if(str.length!=0){    
        reg=/^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$/;     
        if(!reg.test(str)){    
            return false;  
        } else {
            return true;
        }   
    } 

    return false;    
}


$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});

	$('#to-login').click(function(){
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});

    $("#changePwd").click(function(){

        var username = $("input[name=re_username]").val();
        var password = $("input[name=re_password]").val();
        var confirmpassword = $("input[name=re_confirmpassword]").val();


        var flag = validatePwd(password);

        if (!flag || password.length<6) {
            showDialog("重置密码信息","<span style='color:red;'>密码必须为英文字符和数字组合，且不能低于6位！</span>");
            return false;
        }

        if (password != confirmpassword) {
            showDialog("重置密码信息","<span style='color:red;'>密码和确认密码项不一致，请检查！</span>");
            return false;
        }

        $.ajax({    
            url:'?act=admin.forgetPassword',
            data: {
                username : username,
                password : password
            },
            type:'post',   
            success:function(data) {
                if (data == 1) {
                    showDialog("重置密码信息","<span style='color:red;'>你好，密码重置成功，请返回登陆！</span>");
                }else if(data==0){
                    showDialog("重置密码信息","<span style='color:red;'>你好，密码重置失败，请稍后重试！</span>");
                }else{
                    showDialog("重置密码信息","<span style='color:red;'>你好，参数异常，请重新填写表单！</span>");
                }
            },    
            error : function() {     
            }    
        });
    });
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
	        var input = $(this);       
	       
	        $(input).val(input.attr('placeholder'));
	               
	        $(input).focus(function(){
	             if (input.val() == input.attr('placeholder')) {
	                 input.val('');
	             }
	        });
	       
	        $(input).blur(function(){
	            if (input.val() == '' || input.val() == input.attr('placeholder')) {
	                input.val(input.attr('placeholder'));
	            }
	        });
	    });    
    }

    $("#modelClose").click(function(){
        $("#myModal").attr('class','modal hide');
    });
    
});