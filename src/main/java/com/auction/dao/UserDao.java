package com.auction.dao;

import com.auction.pojo.UserDetails;

public interface UserDao {
	
	public int insertUserDetails(UserDetails ud);
    public int updateUserDetails(UserDetails ud);
    public int deleteUserdetails(UserDetails ud);
    public String getRole(UserDetails ud);
    
}
