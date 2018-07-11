
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	function searchData(){
		$("#dg").datagrid('load',{
			className:$("#s_className").val()
		});		
	}
	function gradedelete(){
		var selectRows=$("#dg").datagrid('getSelections');
		if(selectRows.length==0){
			$.messager.alert("系统提示","你选择的数据为空！");
			return;
		}
		var strids=[];
		for(var i=0;i<selectRows.length;i++){
			strids.push(selectRows[i].id);
		}
		var ids=strids.join(",");
		$.messager.confirm("系统提示","您确认想要删除<font color=red>"+selectRows.length+"</font>条记录吗？",function(r){
			if(r){
				$.post("gradedel",{classID:ids},function(result){
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
	
	function openGradeDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加班级信息");
		url="gradeSave";
	}
	function openModifyGradeDialog(){
		var selectRows=$("#dg").datagrid('getSelections');
		if(selectRows.length!=1){
			$.messager.alert("系统提示","请选择一条数据！");
			return;
		};
		var row=selectRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑班级信息");
		$('#fm').form("load",row);
		url="gradeSave?id="+row.id;
		
		
	}
	function closeDialog(){
		$("#dlg").dialog("close");
		$("#className").val("");
		$("#classDesc").val("");
	}
	function saveGrade(){
		$.messager.progress();	// 显示进度条
		$('#fm').form('submit', {
			url: url,
			onSubmit: function(){
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
</head>
<body style="margin:5px">
	<table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true" pagination="true" 
	  rownumbers="true" url="gradelist" fit="true" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true"></th>
			<th field="id" width="50">编号</th>
			<th field="className" width="100">班级名称</th>
			<th field="classDesc" width="150">班级描述</th>
		</tr>
	</thead>			
	</table>
	<div id="tb">
		<div  align="left" style="float:left">
			<a href="javascript:openGradeDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openModifyGradeDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:gradedelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div align="right">
			<font align="right">&nbsp;班级名称:&nbsp;</font><input type="text" id="s_className" name="s_className"/><a id="addbtn" href="javascript:searchData()" class="easyui-linkbutton" iconCls="icon-search" plain="true"></a>
		</div>		
	</div>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:250px; padding:15px 20px" 
	closed="true" buttons="#dlg_buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>班级名称</td>
					<td><input type="text" name="className" id="className" class="easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td valign="top">班级描述</td>
					<td><textarea rows="7" cols="30" name="classDesc" id="classDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg_buttons">
		<a href="javascript:saveGrade()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
	</div>
</body>
</html>