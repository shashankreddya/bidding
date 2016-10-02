package com.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionDaoImpl {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean transferMoney(double bidAmount, long cardNo) {
		
		String sql = "UPDATE ACCOUNT SET BALANCE=(BALANCE-?) WHERE ACNUMBER=?";
		Connection conn = null;
		boolean flag = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, bidAmount);
			stmt.setLong(2, cardNo);
			int updated = stmt.executeUpdate();
			if (updated > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return flag;
	}

}
