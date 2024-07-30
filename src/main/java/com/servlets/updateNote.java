package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Notes.Note;
import helper.FactoryProvider;

/**
 * Servlet implementation class updateNote
 */
public class updateNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		String noteContent= request.getParameter("content");
		String noteTitle= request.getParameter("title");
		String noteId= request.getParameter("id");
		System.out.println(noteId);
		SessionFactory factory=FactoryProvider.getSessionFactory();
		Session session=factory.openSession();
		Transaction tx= session.beginTransaction();
		
		Note note=session.get(Note.class, noteId);
		System.out.println(note.getContent());
		try {
			if(note != null) {
				note.setContent(noteContent);
				note.setTitle(noteTitle);
				note.setCreatedOn(dateFormat.parse("18-08-2023"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		session.update(note);
		tx.commit();
		
		session.close();
		factory.close();
		response.sendRedirect("all_notes.jsp");
		
	}
}


