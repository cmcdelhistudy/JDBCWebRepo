<%@page import="model.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
#movieBox {
	border-width: 1px;
	border-color: black;
	border-style: solid;
	width: 200px;
}
</style>
</head>
<body>
	<center>
		<h1>Movies Dhamaka</h1>

		<table width="80%" bgcolor="yellow">
			<caption>Moview Info</caption>
			<th>ID</th>
			<th>Title</th>
			<th>Cast</th>
			<th>Budget</th>
			<th>Delete</th>

			<%
				ArrayList<Movie> movieList = (ArrayList<Movie>) request
										.getAttribute("MOVIE_LIST");
							for(Movie m:movieList){
								out.println("<tr><td>"+m.getId()+"</td><td><td>"+m.getTitle()+"</td><td>"+m.getActors()+"</td><td>"+m.getBudget()+"</td><td><a href='DeleteMovieServlet?id="+m.getId()+"'><img src='del.png'></a></td></tr>");
							}
			%>
		</table>

	</center>
		<hr>

		<%
			for(Movie m:movieList){
		%>
		<div id="movieBox">
			<img src="pic.jpg" width="100" height="150">
			<h1><%=m.getTitle()%></h1>
			<h4><%=m.getActors()%></h4>
			<a href="">Book Show</a>
			
		</div>
		<%
			}
		%>



</body>
</html>