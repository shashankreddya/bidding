package com.auction.daomgr;

import java.util.List;

import com.auction.daoimpl.UserDaoImpl;
import com.auction.db.AuctionDbConnection;
import com.auction.pojo.UserDetails;

public class DaoMgrTest {
	
	static UserDaoImpl dao= (UserDaoImpl) AuctionDbConnection.auctionDatabseContext().getBean("userDetailsDao");
	public static void main(String[] args) {
		UserDetails ud = new UserDetails();
		ud.setFirstName("name in a1");
		int s= dao.insertUserDetails(ud);
		System.out.println("inserted rows "+s);
		ud.setFirstName("name in a1");
		int s1= dao.updateUserDetails(ud);
		System.out.println("inserted rows "+s1);
		
		ud.setFirstName("sankar");
		dao.getName(ud);
		
		List<UserDetails> udd = dao.getAll();
		for(UserDetails u : udd){
			System.out.println(u.getFirstName());
		}
	}

}
