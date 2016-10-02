package com.auction.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.exceptions.ConnectionException;
import com.auction.exceptions.DataNotFoundException;

public class CheckUserIDAvailbility extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		boolean flag = false;

		try {
			flag = new RegistrationDaoManager().getUserAvailability(userid);
			if (StringUtils.isBlank(userid)) {

				out.println("<center><font color=red>Enter user id</font></center>");
			} else {
				if (flag) {
					out.println("<center><font color=red>User name Not available</font></center>");
				} else
					out.println("<center><font color=red>User name Available</font></center>");
			}
			out.flush();
			out.close();

		} catch (ConnectionException e) {
			out.println("<center><font color=red>Please Try Again.</font></center>");

		} catch (DataNotFoundException e) {
			out.println("<center><font color=red>Please Try Again.</font></center>");
		} catch (Exception e) {
			out.println("<center><font color=red>Please Try Again.</font></center>");
		}

	}
}
