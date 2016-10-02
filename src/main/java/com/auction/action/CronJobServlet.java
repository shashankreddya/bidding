package com.auction.action;

import javax.servlet.http.HttpServlet;

import com.auction.daomgr.CronJobToGetBidWinner;

public class CronJobServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void init(){
		CronJobToGetBidWinner.startJob();
	}

}
