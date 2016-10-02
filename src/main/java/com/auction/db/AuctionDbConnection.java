package com.auction.db;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuctionDbConnection {
	private static ConfigurableApplicationContext datactx;
	private static ConfigurableApplicationContext bankctx;

	public static void main(String arg[]) {
		/*
		 * ConfigurableApplicationContext datactx = new
		 * ClassPathXmlApplicationContext("/database/dataSource.xml");
		 * ConfigurableApplicationContext bankctx = new
		 * ClassPathXmlApplicationContext("/database/bankSource.xml");
		 */
		TestDSDao ds = (TestDSDao) auctionDatabseContext().getBean("ds");
		ds.selectA1();
		/*SampleDao dao = (SampleDao) auctionDatabseContext().getBean("edao");
		int a = dao.saveA1();
		SampleDao bdao = (SampleDao) bankDatabseContext().getBean("edao");
		int b = bdao.saveB1();
		System.out.println(a + " bbbb " + b);*/
		/*
		 * datactx.close(); bankctx.close();
		 */
	}

	/**
	 * Context for Auction Database
	 */
	public static ConfigurableApplicationContext auctionDatabseContext() {

		datactx = new ClassPathXmlApplicationContext("/database/dataSource.xml");
		return datactx;
	}
	/**
	 * Context for Bank Database
	 */
	public static ConfigurableApplicationContext bankDatabseContext() {

		bankctx = new ClassPathXmlApplicationContext("/database/bankSource.xml");
		return bankctx;
	}

}
