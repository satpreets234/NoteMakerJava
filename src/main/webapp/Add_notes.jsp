<%@page import="Notes.Note"%>
<%@page import="org.hibernate.Session"%>
<%@page import="helper.FactoryProvider"%>
<%@page import="org.hibernate.SessionFactory"%>
<html>
<%@include file="all_css_js.jsp" %>
<body>
	<%@include file="Header.jsp"%>
	<div style="padding-left:40px">
		<h2>Enter Note Details</h2>
		<% String noteId=request.getParameter("noteId");
		Note noteDetail=new Note();
		if(noteId != null){
			try{
				SessionFactory factory=FactoryProvider.getSessionFactory();
				Session s= factory.openSession();
				
				Note note= s.get(Note.class, noteId);
				noteDetail.setContent(note.getContent());
				noteDetail.setTitle(note.getTitle());
				noteDetail.setCreatedOn(note.getCreatedOn());
				s.close();
				factory.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			%>
			<form action="updateNote" method="POST">

			<label htmlFor="title">Note Title</label>
			<input name="title" id="title" value= "<%= noteDetail.getTitle()   %>">
			<br><br>
			<input name="id" type="hidden" id="id" value=<%= noteId   %>>
			<label htmlFor="content">Note Content</label>
			<textarea name="content" id="content" rows="10" cols="50" ><%= noteDetail.getContent() %></textarea>
			<br><br>
			<button type="submit">Submit</button>
		</form>
		<%
		}else{
			%>
			<form action="createNote" method="POST">

			<label htmlFor="title">Note Title</label>
			<input name="title" id="title">
			<br><br>
			<label htmlFor="content">Note Content</label>
			<textarea name="content" id="content" rows="10" cols="50"></textarea>
			<br><br>
			<button type="submit">Submit</button>
		</form>
		<%}
		%>
		
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>
