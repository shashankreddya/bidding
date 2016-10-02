package com.auction.daomgr;

import com.auction.daoimpl.TransactionDaoImpl;
import com.auction.db.AuctionDbConnection;

public class TransactionMgr {
	
	static TransactionDaoImpl transactionDaoImpl = (TransactionDaoImpl) AuctionDbConnection.bankDatabseContext()
			.getBean("transactionDaoImpl");

	public boolean transferMoney(double bidAmount, long cardNo) {
		return transactionDaoImpl.transferMoney(bidAmount, cardNo);
	}
	
	

}
