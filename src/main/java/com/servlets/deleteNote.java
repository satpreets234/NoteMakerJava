package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Notes.Note;
import helper.FactoryProvider;

/**
 * Servlet implementation class deleteNote
 */
public class deleteNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String noteId= request.getParameter("noteId");
		try {
			if(noteId != null) {
				SessionFactory factory= FactoryProvider.getSessionFactory();
				Session session= factory.openSession();
				Note note= session.get(Note.class, noteId);
				Transaction tx= session.beginTransaction();
				session.delete(note);
				tx.commit();
				session.close();
				factory.close();
				PrintWriter out= response.getWriter();
				out.println("<h2>Note Deleted successfully</h2>");
				out.println("<a href='all_notes.jsp'>All notes page</a>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
