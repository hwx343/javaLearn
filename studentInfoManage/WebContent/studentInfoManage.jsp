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
	var url;
	function openStudentDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
		url="studentSave";
	}
	function openModifyStudentDialog(){
		var selectRows=$("#dg").datagrid('getSelections');
		if(selectRows.length!=1){
			$.messager.alert("系统提示","请选择一条数据！");
			return;
		};
		var row=selectRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
		$('#fm').form("load",row);
		url="studentSave?stuId="+row.stuId;
		
		
	}
	function closeDialog(){
		$("#dlg").dialog("close");
		$("#stuNo").val("");
		$("#stuName").val("");
		$("#sex").combobox("setValue","");
		$("#birthday").datebox("setValue","");
		$("#gradeId").combobox("setValue","");
		$("#email").val("");
		$("#stuDesc").val("");
	}
	function saveStudent(){
		$.messager.progress();	// 显示进度条
		$('#fm').form('submit', {
			url: url,
			onSubmit: function(){
				if($("#sex").combobox('getValue')==""){
					$.messager.progress('close');
					$.messager.alert("系统提示","请选择性别！");
					return false;
				}
				if($("#gradeId").combobox('getValue')==""){
					$.messager.progress('close');
					$.messager.alert("系统提示","请选择所属班级！");
					return false;
				}
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(result){
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
				if(result.errormsg){
					$.messager.alert("系统提示",result.errormsg)
				}else{
					closeDialog();
					$.messager.alert("系统提示","保存成功！")
					$("#dg").datagrid("reload");					
				}				
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
		<th field="stuName" width="100" align="center">学生姓名</th>
		<th field="sex" width="50" align="center">性别</th>
		<th field="birthday" width="100" align="center">出生日期</th>
		<th field="id" width="100" align="center" hidden="true">班级ID</th>
		<th field="className" width="100" align="center">班级名称</th>
		<th field="email" width="100" align="center">电子邮件</th>
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
		<a id="resetBtn" href="javascript:#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">重置</a>
	</div>		
</div>

<div id="dlg" class="easyui-dialog" style="width:600px;height:300px; padding:15px 20px" 
	closed="true" buttons="#dlg_buttons">
	<form id="fm" method="post">
		<table>
			<tr>
				<td>学生号码：</td>
				<td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" required="true"></td>
				<td width="5%"></td>
				<td>学生名称：</td>
				<td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"></td>
			</tr>
			<tr>
				<td>学生性别：</td>
				<td><select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width:173px">
						<option value="">请选择...</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select></td>
				<td width="5%"></td>
				<td>生日日期：</td>
				<td><input type="text" class="easyui-datebox" id="birthday" name="birthday" /></td>
			</tr>
			<tr>
				<td>所属班级：</td>
				<td><input type="text" class="easyui-combobox" id="gradeId" name="gradeId" panelHeight="auto" 
				data-options="editable:false,valueField:'id',textField:'className',url:'gradeCombolist'"/></td>
				<td width="5%"></td>
				<td>邮箱地址：</td>
				<td><input type="text" name="email" id="email" class="easyui-validatebox" validtype="email"></td>
			</tr>
			<tr>
				<td valign="top">学生备注：</td>
				<td colspan="4"><textarea rows="7" cols="60" name="stuDesc" id="stuDesc"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg_buttons">
	<a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
</div>
</body>
</html>