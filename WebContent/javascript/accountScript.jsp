<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script language="javascript"> 

	// 验证用户名
	function checkUserName()
	{
		var userName = document.getElementById("userName").value;
		var uname = document.getElementById("uname");
		var un=/^[A-Za-z0-9]+$/;
	if(userName==null || userName=="")
		{
			uname.innerHTML = "用户名不能为空!";
			uname.style.color="#FF0000";
			return false;
		}
	if(!un.test(userName))
			{
			uname.innerHTML = "用户名必须是数字或英文,长度为6到20个字符!";
			uname.style.color="#FF0000";
			return false;
			}
		if(userName.length>20||userName.length<6)
		{
			uname.innerHTML = "用户名长度为6到20个字符!";
			uname.style.color="#FF0000";
			return false;
		}
	uname.innerHTML="OK"; 
	uname.style.color="#00CC66";
	return true;
}
	//验证真实姓名
	function checkRealName()
	{
		var realnames=document.getElementById("realname").value;
		var urealname=document.getElementById("urealname");
		if(realnames==null || realnames=="")
			{
			urealname.innerHTML="真实姓名不能为空！";
			urealname.style.color="#FF0000";
				return false;
			}
		var un=/^[0-9]{1,20}$/;
		if(un.test(realnames))
		{
			urealname.innerHTML = "真实姓名必须不能为纯数字!";
			urealname.style.color="#FF0000";
			return false;
		}
		if(realnames.length>20)
		{
			uname.innerHTML = "真实姓名不能超过20个字符!";
			uname.style.color="#FF0000";
			return false;
		}
			urealname.innerHTML="OK";
			urealname.style.color="#00CC66";
			return true;
	}
	// 验证密码
	function checkPassword()
	{
		var pass = document.getElementById("password").value;

		var upass = document.getElementById("upass");
		if(pass==null || pass=="" )
		{
			upass.innerHTML = "密码不能为空！";
			upass.style.color="#FF0000";
			return false;
		}
		//var passPattern = /^[0-9a-zA-Z]{6,15}$/;
		var passPattern=/^[a-zA-Z]+$/;
		if(pass.length<6||pass.length>15)
		{
			upass.innerHTML = "密码必须是6至15位!";
			upass.style.color="#FF0000";
			return false;
		}
		upass.innerHTML="OK"; 
		upass.style.color="#00CC66";
		return true;
	}
	//验证手机号
	function checkTelephone()
	{
		var tele = document.getElementById("mobile_code").value;
		var mid = document.getElementById("mid");
		if(tele==null || tele=="")
		{
			mid.innerHTML="请您输入手机号码!";
			mid.style.color="#FF0000";
			return false;
		}
		//var telePattern = /^1[3,5]{1}[0-9]{9}$/;
		var telePattern=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(!telePattern.test(tele))
		{
			mid.innerHTML="手机号码格式错误!";
			mid.style.color="#FF0000";
			return false;
		}
		if(telePattern.length>11)
		{
			uname.innerHTML = "手机不能超过11个字符!";
			uname.style.color="#FF0000";
			return false;
		}
		
		mid.innerHTML="OK"; 
		mid.style.color="#00CC66";
		return true;
	}
	
	// 验证Email
	function checkEmail()
	{
		var email = document.getElementById("email").value;
		var uemail = document.getElementById("uemail");
		var emialPattern = /^[0-9a-zA-Z]+([\.\-\_][0-9a-zA-Z]+)*@[0-9a-zA-Z]+([\.\-][0-9a-zA-Z]+)*.[a-zA-Z]$/;
		if(email!=null && email!="")
		{
		
			if(! emialPattern.test(email))
			{
				uemail.innerHTML="请您输入正确的Email地址！";
				uemail.style.color="#FF0000";
				return false;
			}
		}
		//uemail.innerHTML=""; 
		//uemail.style.color="#00CC66";
		return true;
	}

	// 整体验证
	function checkAll()
	{
		return  checkUserName()&& checkRealName() && checkPassword() && checkTelephone() && checkEmail();
	}
</script> 
