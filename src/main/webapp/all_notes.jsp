<%@page import="Users.User"%>
<%@page import="org.hibernate.query.Query"%>
<%@page import="Notes.Note"%>
<%@page import="java.util.List"%>
<%@page import="helper.FactoryProvider"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="all_css_js.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="Header.jsp"%>
<div style="padding-left: 40px">
	<h2>All Notes</h2>
	<%
	SessionFactory factory = FactoryProvider.getSessionFactory();
	Session s = null;
	List<Note> allNotes = null;
	try {
		s = factory.openSession();
		System.out.println(45151);
		Query q = s.createQuery("from Note where user= : User");
		Query userGetQuery=  s.createQuery("from User where id= : id");
		HttpSession httpSession= request.getSession();
		int id= (int) httpSession.getAttribute("id");
		userGetQuery.setParameter("id",id);
		User user=(User) userGetQuery.uniqueResult();
		if(user !=null){
			q.setParameter("User",user);
			allNotes = q.list();
		}else{
			response.sendRedirect("Error.jsp");
		}
		
		for (Note note : allNotes) {
	%>
	<div class="card" style="width: 18rem;margin:1rem">
		<img class="card-img-top" src=".../100px180/" alt="Card image cap">
		<div class="card-body">
			<h5 class="card-title">Title: <%=note.getTitle() %></h5>
			<p class="card-text">Content: <%=note.getContent() %></p>
			<p  >Date: <%=note.getCreatedOn() %></p>
			<a href="Add_notes.jsp?noteId=<%= note.getId()%>" class="btn btn-info">Update</a>
			<a href="deleteNote?noteId=<%= note.getId()%>" class="btn btn-danger">Delete</a>
		</div>
	</div>
	<%
	}
	} catch (Exception e) {
	System.out.println(e.getMessage());
	}
	s.close();
	factory.close();
	%>

</div>
<%@include file="Footer.jsp"%>
</html>