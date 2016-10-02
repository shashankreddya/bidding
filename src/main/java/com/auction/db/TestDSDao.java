package com.auction.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TestDSDao {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		        this.dataSource = dataSource;
	}
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void selectA1(){
		String userid = "SELECT MAX(USERID) FROM USERDETAILS";
		int value = 0;
		Connection conn = null;
		try {
			 conn =dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(userid);
			if (!rs.next()) {
				System.out.println(value);
				int i =jdbcTemplate.update("insert into a1(name,dd)values('axyz','1980-3-9')");
				System.out.println(i);
			} else {
				int i =jdbcTemplate.update("insert into a1(name,dd)values('axyz','1980-3-9')");
				System.out.println(i);
				value = rs.getInt(1);
				value++;
				System.err.println(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		System.out.println(value);
	}
	

	public static void main(String[] args) {

	}

}
