package com.auction.pojo;

import java.io.Serializable;

public class UserBid implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userBidId;
	private String biddingDate;
	private String status;
	private double price;
	public int getUserBidId() {
		return userBidId;
	}
	public void setUserBidId(int userBidId) {
		this.userBidId = userBidId;
	}
	public String getBiddingDate() {
		return biddingDate;
	}
	public void setBiddingDate(String biddingDate) {
		this.biddingDate = biddingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	private int userid;
	private int bidId;

}
