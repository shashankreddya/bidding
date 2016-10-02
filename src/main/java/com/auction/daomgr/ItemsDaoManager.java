package com.auction.daomgr;

import java.util.ArrayList;

import com.auction.daoimpl.ItemsDaoImpl;
import com.auction.db.AuctionDbConnection;
import com.auction.pojo.Bid;
import com.auction.pojo.Category;
import com.auction.pojo.Feedback;
import com.auction.pojo.Items;
import com.auction.pojo.Transaction;
import com.auction.pojo.UserDetails;

public class ItemsDaoManager {

	static ItemsDaoImpl itemsDaoImpl = (ItemsDaoImpl) AuctionDbConnection.auctionDatabseContext()
			.getBean("itemsDaoImpl");

	public ArrayList<Category> getCategories() {
		return itemsDaoImpl.getCategories();
	}

	public boolean addItems(Items items) {
		return itemsDaoImpl.addItems(items);
	}

	public boolean updateItems(Items items) {
		return itemsDaoImpl.updateItems(items);
	}

	public ArrayList<Items> getItems(String path) {
		return itemsDaoImpl.getItems(path);
	}

	public boolean deleteItems(int itemId) {
		return itemsDaoImpl.deleteItems(itemId);
	}

	public boolean addCategory(String categoryname) {
		return itemsDaoImpl.addCategory(categoryname);
	}

	public ArrayList<Items> getItems(String path, String categoryName) {
		return itemsDaoImpl.getItems(path, categoryName);
	}

	public boolean addItems(Items bidItems, String categoryName) {
		return itemsDaoImpl.addItems(bidItems, categoryName);
	}

	public boolean addAuctionDates(Bid bid) {
		return itemsDaoImpl.addAuctionDates(bid);
	}

	public ArrayList<Bid> getBid(String user, int itemId) {
		return itemsDaoImpl.getBid(user, itemId);
	}

	public boolean changeStatus(Bid bid) {
		return itemsDaoImpl.changeStatus(bid);
	}

	public ArrayList<Bid> getBid(String user) {
		return itemsDaoImpl.getBid(user);
	}

	public ArrayList<Items> getBidItems(String category, String path, String check) {
		return itemsDaoImpl.getBidItems(category, path,check);
	}

	public boolean addBiddingPrice(String userid, String bidPrice, String itemId) {
		return itemsDaoImpl.addBiddingPrice(userid, bidPrice, itemId);
	}

	public ArrayList<UserDetails> getBidderWinners() {
		return itemsDaoImpl.getBidderWinners();
	}
	
	public double getMaxBidAmount(int itemid) {
		return itemsDaoImpl.getMaxBidAmount(itemid);
	}

	public UserDetails makeTransaction(UserDetails ud) {
		return itemsDaoImpl.makeTransaction(ud);
	}
	
	public Transaction getTransactionInfo(int bidid) {
		return itemsDaoImpl.getTransactionInfo(bidid);
	}

	public boolean sendMessage(int user, String message, String subject) {
		return itemsDaoImpl.sendMessage(user,message,subject);
	}

	public ArrayList<Transaction> viewTransaction(String userid, String role) {
		return itemsDaoImpl.viewTransaction(userid,role);
	}
	public ArrayList<Feedback> viewFeedBack(String userid, String role) {
		return itemsDaoImpl.viewFeedBack(userid,role);
	}

	public boolean sendMessage(String userid, String message, String subject) {
		return itemsDaoImpl.sendMessage(userid,message,subject);
	}

	public boolean updateCategory(String categoryname, String categoryId) {
		return itemsDaoImpl.updateCategory(categoryname,categoryId);
	}

	public boolean deleteCategory(String categoryId) {
		return itemsDaoImpl.deleteCategory(categoryId);
	}

}
