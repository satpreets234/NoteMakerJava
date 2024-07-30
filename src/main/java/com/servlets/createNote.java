package com.servlets;

import org.hibernate.query.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Notes.Note;
import Users.User;
import helper.FactoryProvider;

/**
 * Servlet implementation class createNote
 */
public class createNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createNote() {
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
		HttpSession httpSession= request.getSession();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		String noteContent= request.getParameter("content");
		String noteTitle= request.getParameter("title");
		int id= (int) httpSession.getAttribute("id");
		
		
		SessionFactory factory=FactoryProvider.getSessionFactory();
		Session session=factory.openSession();
		Transaction tx= session.beginTransaction();
		Note note=null;
		Query q=session.createQuery("from User where id=: id");
		q.setParameter("id", id);
		try {
			
			User alreadyUser= (User) q.uniqueResult();
			
			 note=new Note();
			note.setContent(noteContent);
			note.setTitle(noteTitle);
			note.setCreatedOn(dateFormat.parse("16-08-2023"));
			note.setUser(alreadyUser);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		session.save(note);
		tx.commit();
		factory.close();
		session.close();
		response.sendRedirect("all_notes.jsp");
		
	}

}
