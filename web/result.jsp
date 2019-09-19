<%@page import="java.io.UncheckedIOException"%>
<%@ page import="java.util.*" %>

<html>

<body>
	<h1 align="center">Product Recommendation JSP</h1>
	
	<p>
	
	<% 
		@SuppressWarnings("unchecked")
		List<String> styles = (List<String>)request.getAttribute("styles");
		Iterator<String> iterator = styles.iterator();
		while(iterator.hasNext()){
			out.print(String.format("<br> try: %s", iterator.next()));
		}
	%>
</body>

</html>