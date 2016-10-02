package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.pojo.UserDetails;

public class UpdateProfileAction extends HttpServlet{

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
		String target = "/ViewProfileAction";
		try {
			UserDetails ud = new UserDetails();
			String userid = request.getParameter("userId");
			ud.setUserId(Integer.parseInt(userid));
			ud.setFirstName(request.getParameter("firstName"));
			ud.setLastName(request.getParameter("lastName"));
			ud.setGender(request.getParameter("gender"));
			ud.setEmailId(request.getParameter("emailId"));
			ud.setPhone(request.getParameter("phone"));
			ud.setBankName(request.getParameter("bankName"));
			String cardno = request.getParameter("cardNo");
			ud.setCardNo(Long.parseLong(cardno));
			String securityCode = request.getParameter("securityCode");
			String expYear = request.getParameter("expYear");
			String expMonth = request.getParameter("expMonth");
			ud.setSecurityCode(Integer.parseInt(securityCode));
			ud.setExpYear(Integer.parseInt(expYear));
			ud.setExpMonth(Integer.parseInt(expMonth));
			ud.setHouseNo(request.getParameter("houseNo"));
			ud.setStreet(request.getParameter("street"));
			ud.setCity(request.getParameter("city"));
			ud.setState(request.getParameter("state"));
			ud.setCountry(request.getParameter("country"));
			ud.setPin(request.getParameter("pin"));
			boolean flag = new RegistrationDaoManager().updateUser(ud);
			if(flag){
			request.setAttribute("status", "Udated Successfully.");
			}else{
				request.setAttribute("status", "Udated Failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Please try later.");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}