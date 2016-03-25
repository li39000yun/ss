<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/oa/employee!update.sy';
		} else {
			url = sy.contextPath + '/oa/employee!save.sy';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/oa/employee!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.name' : result.name,
						'data.age' : result.age,
						'data.sex' : result.sex,
						'data.remark' : result.remark
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>员工基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" style="background-color:#A1A1A1" value="<%=id%>" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>姓名</th>
					<td><input name="data.name" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>年龄</th>
					<td><input name="data.age" class="easyui-validatebox" data-options="validType:'number'"/></td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<select class="easyui-combobox" name="data.sex" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input name="data.remark" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>