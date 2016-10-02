package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.auction.daomgr.RegistrationDaoManager;

public class ChangeStatusAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userStatus = request.getParameter("userStatus");
		String userId = request.getParameter("userId");
		String target = "/adminhomepage.jsp";
		try {
				boolean flag = false;
				flag = new RegistrationDaoManager().changeStatus(userStatus,userId);
				if (flag) {
					target = "/adminhomepage.jsp";
					request.setAttribute("status", "Status changed successfully");
				} else {
					target = "/adminhomepage.jsp";
					request.setAttribute("status", "Status not changed . Please try again. ");
				}
		} catch (Exception e) {
			target = "/adminhomepage.jsp";
			request.setAttribute("status", "Status not changed . Please try again. ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}
	}
	}

