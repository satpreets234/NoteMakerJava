package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Users.User;
import helper.FactoryProvider;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	doGet(request, response);
		
		try {
			SessionFactory factory= FactoryProvider.getSessionFactory();
			Session session= factory.openSession();
			Transaction tx= session.beginTransaction();
			Query q= session.createQuery("from User where email =: email and password=:password");
		
			String email= request.getParameter("email");
			String password= request.getParameter("password");
			q.setParameter("email", email);
			q.setParameter("password", password);
			User isUser=(User) q.uniqueResult();
			if(isUser == null) {
				System.out.println(q);
				response.sendRedirect("Register.jsp");
			}else {
				HttpSession httpSession= request.getSession();
				factory.close();
				httpSession.setAttribute("id", isUser.getId());
				response.sendRedirect("Add_notes.jsp");
				session.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			response.sendRedirect("Login.jsp");
		}
		
	}

	}


