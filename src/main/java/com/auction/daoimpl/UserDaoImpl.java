package com.auction.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.auction.dao.UserDao;
import com.auction.pojo.UserDetails;

public class UserDaoImpl implements UserDao{
	
	private JdbcTemplate jdbcTemplate;  

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	} 

	public int insertUserDetails(UserDetails ud) {
		String sql = "INSERT INTO A1(NAME) VALUES(?)";
		int insertedRows = jdbcTemplate.update(sql,new Object[]{ud.getFirstName()});
		return insertedRows;
	}

	public int updateUserDetails(UserDetails ud) {
		String sql = "UPDATE A1 SET NAME = ? where NAME = ?";
		int updatedRows = jdbcTemplate.update(sql,new Object[]{ud.getFirstName(),ud.getFirstName()});
		return updatedRows;
	}

	public int deleteUserdetails(UserDetails ud) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getName(UserDetails ud){
		String sql = "select name from  a1 where name = ?";
		String name = jdbcTemplate.queryForObject( sql, new Object[] { ud.getFirstName() }, String.class);
        System.out.println(name);
		return name;
	}
	
	@SuppressWarnings("rawtypes")
	public List<UserDetails> getAll(){
		String sql = "select name from  a1";
		List<UserDetails> userDetails = new ArrayList<UserDetails>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		 for (Map row : rows) {
			 UserDetails ud = new UserDetails();
			 ud.setFirstName((String)row.get("name"));
			 userDetails.add(ud);
		 }
		 return userDetails;
		 }

	public String getRole(UserDetails ud) {
		// TODO Auto-generated method stub
		return null;
	}

	}

