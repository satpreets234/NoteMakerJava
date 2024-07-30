package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Users.User;
import helper.FactoryProvider;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		try {
			SessionFactory factory= FactoryProvider.getSessionFactory();
			Session session= factory.openSession();
			Transaction tx= session.beginTransaction();
			Query q= session.createQuery("from User where email =: email");
		
			String email= request.getParameter("email");
			String username= request.getParameter("username");
			String password= request.getParameter("password");
			q.setParameter("email", email);
			Object duplicateEmail=q.uniqueResult();
			if(duplicateEmail != null) {
				System.out.println(q);
				response.sendRedirect("Error.jsp");
			}else {
				User user= new User();
				user.setEmail(email);
				user.setPassword(password);
				user.setUsername(username);
				session.save(user);
				tx.commit();
				session.close();
				factory.close();
				response.sendRedirect("Login.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			response.sendRedirect("Register.jsp");
		}
		
	}

}
