package com.auction.daomgr;

import java.util.ArrayList;

import com.auction.daoimpl.RegisterDAOImpl;
import com.auction.db.AuctionDbConnection;
import com.auction.exceptions.ConnectionException;
import com.auction.exceptions.DataNotFoundException;
import com.auction.pojo.UserDetails;

public class RegistrationDaoManager {
	static RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl) AuctionDbConnection.auctionDatabseContext()
			.getBean("registerDAOImpl");

	public boolean getUserAvailability(String userid) throws DataNotFoundException, ConnectionException {

		return registerDAOImpl.getUserId(userid);
	}

	public boolean registerUser(UserDetails ud) throws DataNotFoundException, ConnectionException {

		return registerDAOImpl.registerUser(ud);
	}
	
	public String getRole(UserDetails ud){
		return registerDAOImpl.getRole(ud);
	}

	public ArrayList<UserDetails> getUsers() {
		return registerDAOImpl.getUsers();
	}

	public boolean changeStatus(String status, String userId) {
		return registerDAOImpl.changeStatus(status,userId);
	}

	public ArrayList<UserDetails> getWinners() {
		return registerDAOImpl.getWinners();
	}

	public String recoveryPassword(String userName, String question, String answer) {
		return registerDAOImpl.recoveryPassword(userName,question,answer);
	}

	public boolean changePassword(String userid, String oldpassword, String newpassword) {
		return registerDAOImpl.changePassword(userid, oldpassword, newpassword);
	}

	public ArrayList<UserDetails> getUsers(String userid) {
		return registerDAOImpl.getUsers(userid);
	}

	public boolean updateUser(UserDetails ud) {
		return registerDAOImpl.updateUser(ud);
	}
}
