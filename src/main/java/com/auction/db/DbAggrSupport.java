package com.auction.db;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.nativejdbc.SimpleNativeJdbcExtractor;

public class DbAggrSupport  extends JdbcDaoSupport{
	
	protected void initDao() throws Exception {
	    super.initDao();
	    getJdbcTemplate().setNativeJdbcExtractor(new SimpleNativeJdbcExtractor());
	  }
     
	public List<Map<String, Object>> getMaxUserId() {
	    return getJdbcTemplate().queryForList("SELECT MAX(USERID) FROM USERDETAILS");
	  }
}
