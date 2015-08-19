<!DOCTYPE html>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Estado"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	Estado est = (Estado)request.getAttribute("est");
%>

	<form action="estadocontroller.do" method="post">
		
		ID: <input type="number" name="id" value = "<%=est.getId()%>">
		Nome: <input type="text" name="nome" value = "<%=est.getNome()%>"> 
		UF: <input type="text" name="uf" value = "<%=est.getUF()%>"> 
		
		<input type="submit" value="Salvar">
	</form>
</body>
</html>