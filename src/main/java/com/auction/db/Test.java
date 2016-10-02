package com.auction.db;

public class Test {

	public static void main(String[] args) {
		SampleDao dao= (SampleDao) AuctionDbConnection.bankDatabseContext().getBean("edao");
		int i=dao.saveB1();
		System.out.println("inserted rows"+i);
		
	}

}
