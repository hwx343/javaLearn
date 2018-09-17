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
	function searchStudent(){
		$("#dg").datagrid('load',{
			stuNo:$("#s_stuNo").val(),
			stuName:$("#s_stuName").val(),
			sex:$("#s_sex").combobox('getValue'),
			begbirthday:$("#s_begbirthday").datebox('getValue'),
			endbirthday:$("#s_endbirthday").datebox("getValue"),
			gradeId:$("#s_gradeId").combobox('getValue'),
		})
	}
	
	function studentDelete(){
		var selectRows=$("#dg").datagrid('getSelections');
		if(selectRows.length==0){
			$.messager.alert("系统提示","你选择的数据为空！");
			return;
		}
		var strids=[];
		for(var i=0;i<selectRows.length;i++){
			strids.push(selectRows[i].stuId);
		}
		var ids=strids.join(",");
		console.log(ids);
		$.messager.confirm("系统提示","您确认想要删除<font color=red>"+selectRows.length+"</font>条记录吗？",function(r){
			if(r){
				$.post("studentdel",{stuId:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","你已成功删除<font color=red>"+result.delNums+"</font>条记录");
						$("#dg").datagrid("reload");  
					}else{
						$.messager.alert("系统提示",result.errormsg);// 重新载入当前页面数据  
					}
				},"json")
			}
		});		
	}
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
		<th field="stuId" width="50" align="center">编号</th>
		<th field="stuNo" width="100" align="center">学号</th>
		<th field="stuName" width="150" align="center">学生姓名</th>
		<th field="sex" width="50" align="center">性别</th>
		<th field="birthday" width="150" align="center">出生日期</th>
		<th field="className" width="150" align="center">班级名称</th>
		<th field="email" width="150" align="center">电子邮件</th>
		<th field="stuDesc" width="150" align="center">备注</th>
	</tr>
</thead>		
</table>

<div id="tb">
	<div  align="left" style="float:left">
		<a href="javascript:openStudentDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openModifyStudentDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:studentDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div align="right">
		&nbsp;学号:&nbsp;<input type="text" id="s_stuNo" name="s_stuNo" size="10"/>
		&nbsp;姓名:&nbsp;<input type="text" id="s_stuName" name="s_stuName" size="8"/>
		&nbsp;性别:&nbsp;<select class="easyui-combobox" id="s_sex" name="s_sex" editable="false" panelHeight="auto">
			<option value="">请选择...</option>
			<option value="男">男</option>
			<option value="女">女</option>
		</select>
		&nbsp;出生日期:&nbsp;<input type="text" class="easyui-datebox" id="s_begbirthday" name="s_begbirthday" size="12"/>->
		<input type="text" class="easyui-datebox" id="s_endbirthday" name="s_endbirthday" size="12"/>
		&nbsp;所属班级:&nbsp;<input type="text" class="easyui-combobox" id="s_gradeId" name="s_gradeId" panelHeight="auto" size="8" 
		data-options="editable:false,valueField:'id',textField:'className',url:'gradeCombolist'"/>
		<a id="addbtn" href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true"></a>
		
	</div>		
</div>
</body>
</html>