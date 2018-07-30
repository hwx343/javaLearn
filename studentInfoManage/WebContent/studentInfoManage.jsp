<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
th{
 align:center;
}
</style>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="学生信息管理" fitColumns="true" pagination="true" 
  rownumbers="true" url="studentlist" fit="true" toolbar="#tb">
  <thead>
	<tr>
		<th field="cb" checkbox="true"></th>
		<th field="id" width="50">编号</th>
		<th field="stuNo" width="100">学号</th>
		<th field="stuName" width="150">学生姓名</th>
		<th field="sex" width="50">性别</th>
		<th field="birthday" width="150">出生日期</th>
		<th field="className" width="150">班级名称</th>
		<th field="email" width="150">电子邮件</th>
		<th field="stuDesc" width="150">备注</th>
	</tr>
</thead>		
</table>
</body>
</html>