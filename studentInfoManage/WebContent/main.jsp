<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="com.mushuijie.model.User" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统主界面</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		//shuju
		var treeData=[{
			text:"root",
			children:[{
				text:"班级信息管理",
				attributes:{
					url:"gradeInfoManage.jsp"
				}
			},{
				text:"学生信息管理",
				attributes:{
					url:"studentInfoManage.jsp"
				}
			}]
		}];
		
		//实例化树菜单
		$("#tree").tree({
			data:treeData,
			line:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
		
		//add tab
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);//解决重复opentab地问题
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;' src="+url+"/>";
				$("#tabs").tabs('add',{
					title:text,
					closable:true,//注意是closable 可关闭
					content:content
				});
			}
			
		}
	});
</script>
</head>
<body class="easyui-layout">
	<!-- <div region="north" style="height:80px;background-color: #E0EDEF">北</div>
	<div region="center">中</div>
	<div region="west" style="width:150px;">西</div>
	<div region="south" style="height:20px">南</div> -->
	<div region="north" style="height:80px;background-color: #E0EDEF">
		<div align="left" style="width: 80%;float: left"><img src="images/main.jpg"></div>
		<div style="padding-top: 50px;padding-right: 20px;">当前用户：&nbsp;<font color="red" >${currentUser.username }</font></div>
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页">
				<div align="center" style="padding-top:100px"><font color="red" size="20">欢迎使用管理系统</font></div>
			</div>
		</div>
	</div>
	<div region="west" style="width:150px;" title="导航菜单" split="true">
		<ul id="tree"></ul>
	</div>
	<div region="south" style="height:20px" align="center">&copy;版权所有<a href="http://www.baidu.com">www.mushuijie.com</a></div>
</body>
</html>