package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.exceptions.ConnectionException;
import com.auction.pojo.UserDetails;
import com.auction.util.UtilConstants;
import org.apache.commons.lang3.StringUtils;

public class RegistrationAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDetails rb = new UserDetails();
		String target="";
		boolean flag;
		try {
			rb.setLoginId(request.getParameter("userName"));
			rb.setPassword(request.getParameter("password"));
			rb.setForgotPwdQue(request.getParameter("question"));
			rb.setForgotPwdAns(request.getParameter("answer"));
			rb.setLoginType(request.getParameter("role"));
			rb.setFirstName(request.getParameter("fname"));
			rb.setLastName(request.getParameter("lname"));
			rb.setDob(request.getParameter("dob"));
			rb.setGender(request.getParameter("gender"));
			rb.setHouseNo(request.getParameter("hno"));
			rb.setStreet(request.getParameter("location"));
			rb.setCity(request.getParameter("city"));
			rb.setState(request.getParameter("state"));
			rb.setCountry(request.getParameter("country"));
			rb.setEmailId(request.getParameter("email"));
			rb.setPhone(request.getParameter("phone"));
			rb.setPin(request.getParameter("pin"));
			rb.setBankName(request.getParameter("bankName"));
			String cardno = request.getParameter("cardno");
			String acno = request.getParameter("accountno");
			if("card".equals(request.getParameter("pmtmode"))){
				rb.setCardNo(Long.parseLong(cardno));
			}else{rb.setCardNo(Long.parseLong(acno));}
			String securityNo = request.getParameter("securityCode");
			if(StringUtils.isNotBlank(securityNo)){
			rb.setSecurityCode(Integer.parseInt(securityNo));
			}
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			if(StringUtils.isNotBlank(month)&& StringUtils.isNotBlank(year)){
			rb.setExpMonth(Integer.parseInt(month));
			rb.setExpYear(Integer.parseInt(year));
			}
			flag = new RegistrationDaoManager().registerUser(rb);
			if (flag) {
				request.setAttribute("status",
						UtilConstants._REGISTRATION_SUCCESS);
				target = "/home.jsp";
			} else
				request.setAttribute("status",
						UtilConstants._REGISTRATION_FAILED);
			target = "/registration.jsp";
		} catch (ConnectionException e) {
			request.setAttribute("status", UtilConstants._EXCEPTON);
			target = "/registration.jsp";
		} catch (Exception e) {
			request.setAttribute("status", UtilConstants._EXCEPTON);
			target = "/registration.jsp";
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}
	}

}
