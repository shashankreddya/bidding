package com.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.auction.dao.UserDao;
import com.auction.exceptions.ConnectionException;
import com.auction.exceptions.DataNotFoundException;
import com.auction.pojo.UserDetails;
import com.auction.util.DateUtil;

public class RegisterDAOImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean getUserId(String userid) throws DataNotFoundException, ConnectionException {
		String retriveUserId = null;
		boolean flag = false;
		String userIdAvailability = "SELECT LOGINID FROM USERDETAILS WHERE LOGINID = ?";

		try {
			retriveUserId = jdbcTemplate.queryForObject(userIdAvailability, new Object[] { userid.toUpperCase() },
					String.class);
			if (StringUtils.isNotBlank(retriveUserId)) {
				flag = true;
			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			throw new ConnectionException("Server Busy Try Later");

		} catch (EmptyResultDataAccessException e) {
			return flag;
		} catch (Exception e) {
			return flag;
		}
		return flag;
	}

	public boolean registerUser(UserDetails ud) throws DataNotFoundException, ConnectionException {
		boolean flag = false;
		String insertUserDetails = "INSERT INTO USERDETAILS(USERID,FIRSTNAME,LASTNAME,DOB,FORGOTPWQUESTION,FORGOTPWANSWER,EMAILID,GENDER,DOR,STATUS,LOGINID,PASSWORD,LOGINTYPE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String insertAddress = "INSERT INTO ADDRESS(ADDRESSID,HOUSENO,STREET,CITY,STATE,COUNTRY,PINCODE,PHNO,USERIDREF) VALUES(?,?,?,?,?,?,?,?,?)";
		String inertAccountDetails = "INSERT INTO ACCOUNTDETAILS(CARDNUMBER,EXPMONTH,EXPYEAR,SECURITYCODE,USERIDREF,BANKNAME)VALUES (?,?,?,?,?,?)";
		/*
		 * String userid = "SELECT MAX(USERID) FROM USERDETAILS"; String
		 * addressid = "SELECT MAX(ADDRESSID) FROM ADDRESS";
		 */
		try {
			Calendar calendar = Calendar.getInstance();
			java.sql.Date dateObject = new java.sql.Date(calendar.getTime().getTime());
			ud.setDor(dateObject);
			ud.setUserId(getUserId());
			ud.setAddressId(getAddressId());
			ud.setStatus("active");
			ud.setDob(DateUtil.sqlDateFormat(ud.getDob()));

			int rows = jdbcTemplate.update(insertUserDetails,
					new Object[] { ud.getUserId(), ud.getFirstName(), ud.getLastName(), ud.getDob(),
							ud.getForgotPwdQue(), ud.getForgotPwdAns(), ud.getEmailId(), ud.getGender(), ud.getDor(),
							ud.getStatus(),
							ud.getLoginId(), ud
									.getPassword(),
					ud.getLoginType() });/*
											 * ,ud.getAddressId(),ud.getHouseNo(
											 * ),ud.getStreet(),ud.getCity(),ud.
											 * getState(),ud.getCountry(),ud.
											 * getPin(),ud.getPhone(),ud.
											 * getUserId(),
											 * ud.getCardNo(),ud.getExpMonth(),
											 * ud.getExpYear(),ud.
											 * getSecurityCode(),ud.getUserId(),
											 * ud.getBankName()
											 */
			if (rows > 0) {

				int addr = jdbcTemplate.update(insertAddress,
						new Object[] { ud.getAddressId(), ud.getHouseNo(), ud.getStreet(), ud.getCity(), ud.getState(),
								ud.getCountry(), ud.getPin(), ud.getPhone(), ud.getUserId() });
				if (addr > 0) {
					int acc = jdbcTemplate.update(inertAccountDetails, new Object[] { ud.getCardNo(), ud.getExpMonth(),
							ud.getExpYear(), ud.getSecurityCode(), ud.getUserId(), ud.getBankName() });
					if (acc > 0)
						flag = true;
				}
			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			throw new ConnectionException("Server Busy Try Later");

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	private int getUserId() {
		String userid = "SELECT MAX(USERID) FROM USERDETAILS";
		int value = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(userid);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}

	private int getAddressId() {
		String addressid = "SELECT MAX(ADDRESSID) FROM ADDRESS";
		int value = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(addressid);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}

	public String getRole(UserDetails ud) {
		String role = null;
		String userIdAvailability = "SELECT LOGINTYPE FROM USERDETAILS WHERE LOGINID = ? AND PASSWORD = ?";

		try {
			role = jdbcTemplate.queryForObject(userIdAvailability,
					new Object[] { ud.getLoginId().toUpperCase(), ud.getPassword() }, String.class);
			if (StringUtils.isNotBlank(role)) {
				return role;
			}
		} catch (NullPointerException ne) {
			return null;

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			return null;
		}
		return role;
	}

	public int insertUserDetails(UserDetails ud) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateUserDetails(UserDetails ud) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteUserdetails(UserDetails ud) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<UserDetails> getUsers() {

		String sql = "SELECT U.USERID,U.FIRSTNAME,U.LASTNAME,U.GENDER,U.STATUS,U.EMAILID,A.PHNO,A.HOUSENO,A.STREET,A.CITY,A.STATE,A.COUNTRY,A.PINCODE FROM USERDETAILS U,ADDRESS A WHERE U.USERID = A.USERIDREF";
		Connection conn = null;
		ArrayList<UserDetails> userDetails = new ArrayList<UserDetails>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UserDetails user = new UserDetails();
				user.setUserId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setGender(rs.getString(4));
				user.setStatus(rs.getString(5));
				user.setEmailId(rs.getString(6));
				user.setPhone(rs.getString(7));
				user.setHouseNo(rs.getString(8));
				user.setStreet(rs.getString(9));
				user.setCity(rs.getString(10));
				user.setState(rs.getString(11));
				user.setCountry(rs.getString(12));
				user.setPin(rs.getString(13));
				userDetails.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userDetails;

	}

	public boolean changeStatus(String status, String userId) {
		String sql = "UPDATE USERDETAILS SET STATUS=? WHERE USERID=?";
		Connection conn = null;
		String changeStatus = null;
		boolean flag = false;
		try {
			if (status.equalsIgnoreCase("active")) {
				changeStatus = "inactive";
			} else if (status.equalsIgnoreCase("inactive")) {
				changeStatus = "active";
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, changeStatus);
			stmt.setInt(2, Integer.parseInt(userId));
			int updatedd = stmt.executeUpdate();
			if (updatedd > 0) {

				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public ArrayList<UserDetails> getWinners() {
		String sql = " select biddingprice,useridref,bididref from userbid where status='win' order by bidingdate desc";
		String userdetails = "select u.firstname,u.lastname,u.emailid,a.phno from userdetails u,address a where u.userid =? and a.useridref =?";
		Connection conn = null;
		ArrayList<UserDetails> users = new ArrayList<UserDetails>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt1 = conn.prepareStatement(userdetails);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UserDetails user = new UserDetails();
				user.setBidAmount(rs.getDouble(1));
				user.setUserId(rs.getInt(2));
				user.setBidId(rs.getInt(3));
				user.setItemName(getItemName(user.getBidId()));
				stmt1.setInt(1, user.getUserId());
				stmt1.setInt(2, user.getUserId());
				ResultSet rs1 = stmt1.executeQuery();
				if (rs1.next()) {
					user.setFirstName(rs1.getString(1));
					user.setLastName(rs1.getString(2));
					user.setEmailId(rs1.getString(3));
					user.setPhone(rs1.getString(1));
				}
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	private String getItemName(int bidid) {
		String sql = "select itemsname from  items where itemid =(select itemidref from bid where bidid = ?)";
		Connection conn = null;
		String itemName = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bidid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				itemName = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemName;

	}

	public String recoveryPassword(String userName, String question, String answer) {
		String sql = "update USERDETAILS set password = ? where loginid =? and forgotpwquestion =? and forgotpwanswer=?";
		Connection conn = null;
		String pass = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			int randomNumber = new Random().nextInt(9999);
			stmt.setString(1,Integer.toString(randomNumber));
			stmt.setString(2,userName);
			stmt.setString(3,question);
			stmt.setString(4,answer);
			int update = stmt.executeUpdate();
			if (update > 0) {
				 pass = Integer.toString(randomNumber);
				return pass;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return pass;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pass;
	}

	public boolean changePassword(String userid, String oldpassword, String newpassword) {
		String sql = "update USERDETAILS set password = ? where loginid =? and password =?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,newpassword);
			stmt.setString(2,userid.toLowerCase());
			stmt.setString(3,oldpassword);
			int update = stmt.executeUpdate();
			if (update > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<UserDetails> getUsers(String userid) {
		String sql = "SELECT U.USERID,U.FIRSTNAME,U.LASTNAME,U.GENDER,U.STATUS,U.EMAILID,A.PHNO,A.HOUSENO,A.STREET,A.CITY,A.STATE,A.COUNTRY,A.PINCODE,AC.BANKNAME,AC.CARDNUMBER,AC.EXPYEAR,AC.EXPMONTH,AC.SECURITYCODE FROM USERDETAILS U,ADDRESS A,ACCOUNTDETAILS AC WHERE U.USERID = (SELECT USERID FROM USERDETAILS WHERE LOGINID = ?) AND  A.USERIDREF = (SELECT USERID FROM USERDETAILS WHERE LOGINID = ?) AND AC.USERIDREF=(SELECT USERID FROM USERDETAILS WHERE LOGINID = ?)";
		Connection conn = null;
		ArrayList<UserDetails> userDetails = new ArrayList<UserDetails>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userid);
			stmt.setString(2, userid);
			stmt.setString(3, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UserDetails user = new UserDetails();
				user.setUserId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setGender(rs.getString(4));
				user.setStatus(rs.getString(5));
				user.setEmailId(rs.getString(6));
				user.setPhone(rs.getString(7));
				user.setHouseNo(rs.getString(8));
				user.setStreet(rs.getString(9));
				user.setCity(rs.getString(10));
				user.setState(rs.getString(11));
				user.setCountry(rs.getString(12));
				user.setPin(rs.getString(13));
				user.setBankName(rs.getString(14));
				user.setCardNo(rs.getLong(15));
				user.setExpYear(rs.getInt(16));
				user.setExpMonth(rs.getInt(17));
				user.setSecurityCode(rs.getInt(18));
				userDetails.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userDetails;

	}

	public boolean updateUser(UserDetails ud) {
		String usersql = "update USERDETAILS set firstname=?,lastname=?,emailid=?,gender=? where userid= ?";
		String addresssql = "update ADDRESS set houseno=?,street=?,city=?,state=?,country=?,phno=?,pincode=? where useridref= ?";
		String accountsql = "update ACCOUNTDETAILS set cardnumber=?,expmonth=?,expyear=?,securitycode=?,bankname=? where useridref= ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(usersql);
			stmt.setString(1,ud.getFirstName());
			stmt.setString(2,ud.getLastName());
			stmt.setString(3,ud.getEmailId());
			stmt.setString(4,ud.getGender());
			stmt.setInt(5,ud.getUserId());
			int update = stmt.executeUpdate();
			if (update > 0) {
				PreparedStatement stmt1 = conn.prepareStatement(addresssql);
				stmt1.setString(1,ud.getHouseNo());
				stmt1.setString(2,ud.getStreet());
				stmt1.setString(3,ud.getCity());
				stmt1.setString(4,ud.getState());
				stmt1.setString(5,ud.getCountry());
				stmt1.setString(6,ud.getPhone());
				stmt1.setString(7, ud.getPin());
				stmt1.setInt(8,ud.getUserId());
				int update1 = stmt1.executeUpdate();
				if(update1>0){
				PreparedStatement stmt2 = conn.prepareStatement(accountsql);
				stmt2.setLong(1,ud.getCardNo());
				stmt2.setInt(2,ud.getExpMonth());
				stmt2.setInt(3,ud.getExpYear());
				stmt2.setInt(4,ud.getSecurityCode());
				stmt2.setString(5,ud.getBankName());
				stmt2.setInt(6,ud.getUserId());
				int update2 = stmt2.executeUpdate();
				if(update2>0){
				return true;
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;		
		
	}
}
