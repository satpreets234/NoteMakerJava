<html>
<%@include file="all_css_js.jsp" %>
<body>
	<%@include file="Header.jsp"%>
	<div style="padding-left:40px">
		<h2>Enter Note Details</h2>

		<form action="createNote" method="POST">

			<label htmlFor="title">Note Title</label>
			<input name="title" id="title">
			<br><br>
			<label htmlFor="content">Note Content</label>
			<textarea name="content" id="content" rows="10" cols="50"></textarea>
			<br><br>
			<button type="submit">Submit</button>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>

</html>
