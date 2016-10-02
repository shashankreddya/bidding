package com.auction.daoimpl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.auction.dao.ItemDao;
import com.auction.pojo.Bid;
import com.auction.pojo.Category;
import com.auction.pojo.Feedback;
import com.auction.pojo.Items;
import com.auction.pojo.Transaction;
import com.auction.pojo.UserDetails;
import com.auction.util.DateUtil;

public class ItemsDaoImpl implements ItemDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ArrayList<Category> getCategories() {
		String sql = "SELECT CATEGORYID,CATEGORYNAME FROM CATEGORY";
		ArrayList<Category> category = new ArrayList<Category>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Category cat = new Category();
			cat.setCategoryId((Integer) row.get("CATEGORYID"));
			cat.setCategoryName((String) row.get("CATEGORYNAME"));
			category.add(cat);
		}
		return category;
	}

	public boolean addItems(Items items) {
		LobHandler lobHandler1 = new DefaultLobHandler();
		LobHandler lobHandler2 = new DefaultLobHandler();
		String insertIntoCategory = "INSERT INTO CATEGORY(CATEGORYID,CATEGORYNAME) VALUES (?,?)";
		String insertIntoItems = "INSERT INTO ITEMS(ITEMID,ITEMSNAME,DESCRIPTION,SUMMARY,IMG1,IMG2,PRICE,USERIDREF,CATEGORYIDREF) VALUES(?,?,?,?,?,?,?,?,?)";
		items.setUserId(getUserId(items.getUserName()));
		items.setItemId(getItemId());
		if (items.getCategoryName().equalsIgnoreCase("Category Inserted")) {
			int insertedItems = jdbcTemplate.update(insertIntoItems,
					new Object[] { items.getItemId(), items.getItemName(), items.getDescription(), items.getSummary(),
							new SqlLobValue(items.getSmallImage(), items.getSmallImgLength(), lobHandler1),
							new SqlLobValue(items.getBigImage(), items.getBigImgLength(), lobHandler2),
							items.getPrice(), items.getUserId(), items.getCategoryId() },
					new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.BLOB,
							Types.DOUBLE, Types.INTEGER, Types.INTEGER });
			if (insertedItems > 0) {
				return true;
			}
		} else {
			items.setCategoryId(getCategoryId());
			int insertedCat = jdbcTemplate.update(insertIntoCategory,
					new Object[] { items.getCategoryId(), items.getCategoryName().toLowerCase() });
			if (insertedCat > 0) {
				int insertedItem = jdbcTemplate.update(insertIntoItems,
						new Object[] { items.getItemId(), items.getItemName(), items.getDescription(),
								items.getSummary(),
								new SqlLobValue(items.getSmallImage(), items.getSmallImgLength(), lobHandler1),
								new SqlLobValue(items.getBigImage(), items.getBigImgLength(), lobHandler2),
								items.getPrice(), items.getUserId(), items.getCategoryId() },
						new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.BLOB,
								Types.DOUBLE, Types.INTEGER, Types.INTEGER });
				if (insertedItem > 0) {
					return true;
				}
			}
		}

		return false;
	}

	private int getCategoryId() {
		String addressid = "SELECT MAX(CATEGORYID) FROM CATEGORY";
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

	private int getItemId() {
		String addressid = "SELECT MAX(ITEMID) FROM ITEMS";
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
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;

	}

	private int getbidId() {
		String sql = "SELECT MAX(BIDID) FROM BID";
		int value = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
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
		return value;

	}

	private int getUserBidId() {
		String sql = "SELECT MAX(USERBIDID) FROM USERBID";
		int value = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
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
		return value;

	}

	private String getBidStatus(int uid, int iid) {
		String sql = "SELECT BIDSTATUS FROM BID WHERE USERIDREF= ? AND ITEMIDREF=?";
		String value = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, uid);
			stmt.setInt(2, iid);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return value = "no status";
			} else {
				value = rs.getString(1);
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
		return value;

	}

	private int getUserId(String userName) {
		String sql = "SELECT USERID FROM USERDETAILS WHERE LOGINID = ?";
		Connection conn = null;
		int retVal = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName.toUpperCase());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return retVal = rs.getInt("USERID");
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
		return retVal;
	}

	@SuppressWarnings("resource")
	public ArrayList<Items> getItems(String path) {
		String sql = "SELECT * FROM ITEMS";
		Connection conn = null;
		int retVal = 0;
		ArrayList<Items> itemsList = new ArrayList<Items>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Items items = new Items();
				int itemid = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMSNAME");
				items.setItemId(itemid);
				items.setItemName(itemName);
				items.setDescription(rs.getString("DESCRIPTION"));
				items.setSummary(rs.getString("SUMMARY"));
				Blob blob1, blob2;
				blob1 = rs.getBlob("IMG1");
				blob2 = rs.getBlob("IMG2");
				byte b1[] = blob1.getBytes(1, (int) blob1.length());
				byte b2[] = blob2.getBytes(1, (int) blob2.length());
				String bigImagePath = path;
				String path1 = path;
				path1 = path1 + "/" + itemid + ".jpeg";
				bigImagePath = bigImagePath + "/" + itemName + ".jpeg";
				OutputStream fout = new FileOutputStream("/bidding/tmpImage/"+ itemid + ".jpeg");
				OutputStream fout1 = new FileOutputStream("/bidding/tmpImage/"+ itemName + ".jpeg");
				fout.write(b1);
				fout1.write(b2);
				items.setSmallImagePath(path1);
				items.setBigImagePath(bigImagePath);
				items.setPrice(rs.getDouble("PRICE"));
				items.setUserId(rs.getInt("USERIDREF"));
				items.setCategoryId(rs.getInt("CATEGORYIDREF"));
				itemsList.add(items);
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
		return itemsList;
	}

	public boolean updateItems(Items items) {

		String sql = "UPDATE ITEMS SET ITEMSNAME=?,DESCRIPTION=?,SUMMARY=?,PRICE=? WHERE ITEMID=?";
		Connection conn = null;
		boolean flag = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, items.getItemName());
			stmt.setString(2, items.getDescription());
			stmt.setString(3, items.getSummary());
			stmt.setDouble(4, items.getPrice());
			stmt.setInt(5, items.getItemId());
			int updated = stmt.executeUpdate();
			if (updated > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	public boolean deleteItems(int itemId) {
		String sql = "DELETE FROM ITEMS WHERE ITEMID=?";
		Connection conn = null;
		boolean flag = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemId);
			int updated = stmt.executeUpdate();
			if (updated > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	public boolean addCategory(String categoryname) {
		String insertIntoCategory = "INSERT INTO CATEGORY(CATEGORYID,CATEGORYNAME) VALUES (?,?)";
		int categoryid = getCategoryId();
		int insertedCat = jdbcTemplate.update(insertIntoCategory,
				new Object[] { categoryid, categoryname.toLowerCase() });
		if (insertedCat > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<Items> getItems(String path, String categoryName) {
		String sql = "SELECT * FROM ITEMS WHERE CATEGORYIDREF =(SELECT CATEGORYID FROM CATEGORY WHERE CATEGORYNAME =?)";
		Connection conn = null;
		int retVal = 0;
		ArrayList<Items> itemsList = new ArrayList<Items>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoryName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Items items = new Items();
				int itemid = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMSNAME");
				items.setItemId(itemid);
				items.setItemName(itemName);
				items.setDescription(rs.getString("DESCRIPTION"));
				items.setSummary(rs.getString("SUMMARY"));
				Blob blob1, blob2;
				blob1 = rs.getBlob("IMG1");
				blob2 = rs.getBlob("IMG2");
				byte b1[] = blob1.getBytes(1, (int) blob1.length());
				byte b2[] = blob2.getBytes(1, (int) blob2.length());
				String bigImagePath = path;
				String path1 = path;
				path1 = path1 + "/" + itemid + ".jpeg";
				bigImagePath = bigImagePath + "/" + itemName + ".jpeg";
				OutputStream fout = new FileOutputStream(path1);
				OutputStream fout1 = new FileOutputStream(bigImagePath);
				fout.write(b1);
				fout1.write(b2);
				items.setSmallImagePath("/bidding/tmpImage/"+ itemid + ".jpeg");
				items.setBigImagePath("/bidding/tmpImage/"+ itemName + ".jpeg");
				items.setPrice(rs.getDouble("PRICE"));
				items.setUserId(rs.getInt("USERIDREF"));
				items.setCategoryId(rs.getInt("CATEGORYIDREF"));
				itemsList.add(items);
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
		return itemsList;
	}

	public boolean addItems(Items items, String categoryName) {
		LobHandler lobHandler1 = new DefaultLobHandler();
		LobHandler lobHandler2 = new DefaultLobHandler();
		String insertIntoItems = "INSERT INTO ITEMS(ITEMID,ITEMSNAME,DESCRIPTION,SUMMARY,IMG1,IMG2,PRICE,USERIDREF,CATEGORYIDREF) VALUES(?,?,?,?,?,?,?,?,(SELECT CATEGORYID FROM CATEGORY WHERE CATEGORYNAME=?))";
		items.setUserId(getUserId(items.getUserName()));
		items.setItemId(getItemId());
		int insertedItems = jdbcTemplate.update(insertIntoItems,
				new Object[] { items.getItemId(), items.getItemName(), items.getDescription(), items.getSummary(),
						new SqlLobValue(items.getSmallImage(), items.getSmallImgLength(), lobHandler1),
						new SqlLobValue(items.getBigImage(), items.getBigImgLength(), lobHandler2), items.getPrice(),
						items.getUserId(), items.getCategoryName() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.BLOB,
						Types.DOUBLE, Types.INTEGER, Types.VARCHAR });
		if (insertedItems > 0) {
			return true;
		}

		return false;
	}

	public boolean addAuctionDates(Bid bid) {
		String sql = "INSERT INTO BID(BIDID,BIDSTARTDATE,BIDENDDATE,BIDSTATUS,ITEMIDREF,USERIDREF) VALUES(?,?,?,?,?,?)";
		String updatesql = "UPDATE BID SET BIDSTARTDATE=?,BIDENDDATE=? WHERE ITEMIDREF= ? AND USERIDREF=?";
		bid.setUserId(getUserId(bid.getLoginId()));
		bid.setStatus(getBidStatus(bid.getUserId(), bid.getItemId()));
		bid.setBidId(getbidId());
		int insertedItems = 0;
		if (DateUtil.beforeDate(bid.getBidStartDate()) && DateUtil.beforeDate(bid.getBidEndDate())
				&& bid.getStatus().equalsIgnoreCase("no status")) {
			insertedItems = jdbcTemplate
					.update(sql,
							new Object[] { bid.getBidId(), DateUtil.sqlDateFormat(bid.getBidStartDate()),
									DateUtil.sqlDateFormat(bid.getBidEndDate()), "init", bid.getItemId(),
									bid.getUserId() });
		} else if (DateUtil.beforeDate(bid.getBidStartDate()) && DateUtil.beforeDate(bid.getBidEndDate())
				&& bid.getStatus().equalsIgnoreCase("init")) {
			insertedItems = jdbcTemplate.update(updatesql, new Object[] { DateUtil.sqlDateFormat(bid.getBidStartDate()),
					DateUtil.sqlDateFormat(bid.getBidEndDate()), bid.getItemId(), bid.getUserId() });
		}
		if (insertedItems > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<Bid> getBid(String user, int itemId) {
		String sql = "SELECT BIDID,DATE(BIDSTARTDATE),DATE(BIDENDDATE),BIDSTATUS,ITEMIDREF,USERIDREF FROM BID WHERE ITEMIDREF=?";
		Connection conn = null;
		ArrayList<Bid> dateslist = new ArrayList<Bid>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Bid bid = new Bid();
				bid.setBidId(rs.getInt(1));
				bid.setBidStartDate(DateUtil.normalDateFormat(rs.getString(2)));
				bid.setBidEndDate(DateUtil.normalDateFormat(rs.getString(3)));
				bid.setStatus(rs.getString(4));
				bid.setUserId(rs.getInt(5));
				bid.setItemId(rs.getInt(6));
				dateslist.add(bid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dateslist;
	}

	public boolean changeStatus(Bid bid) {
		int insertedItems = 0;
		if ("init".equalsIgnoreCase(bid.getStatus()) || "start".equalsIgnoreCase(bid.getStatus())
				|| "end".equalsIgnoreCase(bid.getStatus())) {
			if (DateUtil.beforeDate(bid.getBidStartDate()) && DateUtil.beforeDate(bid.getBidEndDate())) {
				String updatesql = "UPDATE BID SET BIDSTARTDATE=?,BIDENDDATE=?,BIDSTATUS=? WHERE BIDID= ?";
				insertedItems = jdbcTemplate.update(updatesql,
						new Object[] { DateUtil.sqlDateFormat(bid.getBidStartDate()),
								DateUtil.sqlDateFormat(bid.getBidEndDate()), bid.getStatus(), bid.getBidId() });
				if (insertedItems > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public ArrayList<Bid> getBid(String user) {
		String sql = "SELECT B.BIDID,DATE(B.BIDSTARTDATE),DATE(B.BIDENDDATE),B.BIDSTATUS,B.ITEMIDREF,B.USERIDREF,I.ITEMSNAME FROM BID B,ITEMS I WHERE B.USERIDREF=? AND I.ITEMID = B.ITEMIDREF";
		Connection conn = null;
		ArrayList<Bid> dateslist = new ArrayList<Bid>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getUserId(user));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Bid bid = new Bid();
				bid.setBidId(rs.getInt(1));
				bid.setBidStartDate(DateUtil.normalDateFormat(rs.getString(2)));
				bid.setBidEndDate(DateUtil.normalDateFormat(rs.getString(3)));
				bid.setStatus(rs.getString(4));
				bid.setUserId(rs.getInt(5));
				bid.setItemId(rs.getInt(6));
				bid.setItemName(rs.getString(7));
				dateslist.add(bid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dateslist;
	}

	public ArrayList<Items> getBidItems(String category, String path, String check) {
		int categoryId = Integer.parseInt(category);
		String sql = null;
		if (check.equalsIgnoreCase("current")) {
			sql = "SELECT B.BIDID,DATE(B.BIDSTARTDATE),DATE(B.BIDENDDATE),B.BIDSTATUS,I.ITEMID,I.ITEMSNAME,I.DESCRIPTION,I.SUMMARY,I.IMG1,I.IMG2,I.PRICE FROM BID B,ITEMS I WHERE B.ITEMIDREF=I.ITEMID AND I.ITEMID IN (SELECT A.ITEMID FROM ITEMS A WHERE A.CATEGORYIDREF=?) AND (B.BIDSTARTDATE<=(SELECT CURRENT_DATE()) AND B.BIDENDDATE >= (SELECT CURRENT_DATE())) AND B.BIDSTATUS='start'";
		} else if (check.equalsIgnoreCase("endtoday")) {
			sql = "SELECT B.BIDID,DATE(B.BIDSTARTDATE),DATE(B.BIDENDDATE),B.BIDSTATUS,I.ITEMID,I.ITEMSNAME,I.DESCRIPTION,I.SUMMARY,I.IMG1,I.IMG2,I.PRICE FROM BID B,ITEMS I WHERE B.ITEMIDREF=I.ITEMID AND I.ITEMID IN (SELECT A.ITEMID FROM ITEMS A WHERE A.CATEGORYIDREF=?) AND DATE(B.BIDENDDATE) =(SELECT CURRENT_DATE())  AND B.BIDSTATUS='start'";
		} else if (check.equalsIgnoreCase("starttoday")) {
			sql = "SELECT B.BIDID,DATE(B.BIDSTARTDATE),DATE(B.BIDENDDATE),B.BIDSTATUS,I.ITEMID,I.ITEMSNAME,I.DESCRIPTION,I.SUMMARY,I.IMG1,I.IMG2,I.PRICE FROM BID B,ITEMS I WHERE B.ITEMIDREF=I.ITEMID AND I.ITEMID IN (SELECT A.ITEMID FROM ITEMS A WHERE A.CATEGORYIDREF=?) AND DATE(B.BIDSTARTDATE) =(SELECT CURRENT_DATE())  AND B.BIDSTATUS='start'";
		}
		Connection conn = null;
		ArrayList<Items> itemsList = new ArrayList<Items>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Items items = new Items();
				items.setBidId(rs.getInt(1));
				items.setBidStartDate(DateUtil.normalDateFormat(rs.getString(2)));
				items.setBidEndDate(DateUtil.normalDateFormat(rs.getString(3)));
				items.setBidStatus(rs.getString(4));
				int itemid = rs.getInt(5);
				String itemName = rs.getString(6);
				items.setItemId(itemid);
				items.setItemName(itemName);
				items.setDescription(rs.getString(7));
				items.setSummary(rs.getString(8));
				Blob blob1, blob2;
				blob1 = rs.getBlob(9);
				blob2 = rs.getBlob(10);
				byte b1[] = blob1.getBytes(1, (int) blob1.length());
				byte b2[] = blob2.getBytes(1, (int) blob2.length());
				String bigImagePath = path;
				String path1 = path;
				path1 = path1 + "/" + itemid + ".jpeg";
				bigImagePath = bigImagePath + "/" + itemName + ".jpeg";
				OutputStream fout = new FileOutputStream(path1);
				OutputStream fout1 = new FileOutputStream(bigImagePath);
				fout.write(b1);
				fout1.write(b2);
				items.setSmallImagePath("/bidding/tmpImage/"+ itemid + ".jpeg");
				items.setBigImagePath("/bidding/tmpImage/"+ itemName + ".jpeg");
				items.setPrice(rs.getDouble(11));
				itemsList.add(items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemsList;
	}

	public boolean addBiddingPrice(String username, String bidPrice, String bidId) {
		String sql = "INSERT INTO USERBID(USERBIDID,BIDINGDATE,STATUS,BIDDINGPRICE,USERIDREF,BIDIDREF) VALUES(?,?,?,?,?,?)";
		getUserId(username);
		int insertedItems = 0;
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateObject = new java.sql.Date(calendar.getTime().getTime());
		insertedItems = jdbcTemplate.update(sql, new Object[] { getUserBidId(), dateObject, "bidding",
				Integer.parseInt(bidPrice), getUserId(username), Integer.parseInt(bidId) });

		if (insertedItems > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<UserDetails> getBidderWinners() {
		String bididsql = "select bidid from bid where date(bidenddate) in (select current_date())";
		String userbididSql = "select userbidid,useridref from userbid where userbidid = (select userbidid from userbid where biddingprice = (select max(biddingprice) from userbid where bididref=?))";
		String updateStatus = "update userbid set status ='win' where userbidid =?";
		String userdetails = "SELECT U.USERID,U.FIRSTNAME,U.LASTNAME,U.GENDER,U.STATUS,U.EMAILID,A.PHNO,A.HOUSENO,A.STREET,A.CITY,A.STATE,A.COUNTRY,A.PINCODE FROM USERDETAILS U,ADDRESS A WHERE U.USERID = ? AND A.USERIDREF=?";
		String bidstatus = "UPDATE BID SET BIDSTATUS=? WHERE BIDID= ?";
		Connection conn = null;
		int bidid = 0, userid = 0, userbidid = 0;
		ArrayList<UserDetails> userDetails = new ArrayList<UserDetails>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(bididsql);
			PreparedStatement pstm = conn.prepareStatement(userbididSql);
			PreparedStatement pstm1 = conn.prepareStatement(updateStatus);
			PreparedStatement pstm2 = conn.prepareStatement(bidstatus);
			PreparedStatement pstm3 = conn.prepareStatement(userdetails);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				bidid = rs.getInt(1);
				if (bidid > 0) {

					pstm.setInt(1, bidid);
					ResultSet rs1 = pstm.executeQuery();
					if (rs1.next()) {
						userbidid = rs1.getInt(1);
						userid = rs1.getInt(2);
						pstm1.setInt(1, userbidid);
						int r = pstm1.executeUpdate();
						if (r > 0) {
							pstm2.setString(1, "end");
							pstm2.setInt(2, bidid);
							int r1 = pstm2.executeUpdate();
							if (r1 > 0) {
								pstm3.setInt(1, userid);
								pstm3.setInt(2, userid);
								ResultSet rs2 = pstm3.executeQuery();
								while (rs2.next()) {
									UserDetails user = new UserDetails();
									user.setUserbidId(userbidid);
									user.setBidId(bidid);
									user.setUserId(rs2.getInt(1));
									user.setFirstName(rs2.getString(2));
									user.setLastName(rs2.getString(3));
									user.setGender(rs2.getString(4));
									user.setStatus(rs2.getString(5));
									user.setEmailId(rs2.getString(6));
									user.setPhone(rs2.getString(7));
									user.setHouseNo(rs2.getString(8));
									user.setStreet(rs2.getString(9));
									user.setCity(rs2.getString(10));
									user.setState(rs2.getString(11));
									user.setCountry(rs2.getString(12));
									user.setPin(rs2.getString(13));
									userDetails.add(user);
								}

							}

						}

					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userDetails;
	}

	public double getMaxBidAmount(int itemid) {
		String sql = "select max(biddingprice) from userbid where bididref = (select bidid from bid where itemidref = ?)";
		Connection conn = null;
		double maxAmount = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				maxAmount = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxAmount;

	}

	public UserDetails makeTransaction(UserDetails ud) {
		String sql = "insert into transaction(TRANSACTIONID,TRANSACTIONAMOUNT,TRANSACTIONDATE,PURPOSE,PAYERID,SELLERID,BIDIDREF) values(?,?,?,?,?,(select useridref from bid where bidid=?),?)";
		Connection conn = null;
		double maxAmount = 0;
		try {
			Calendar calendar = Calendar.getInstance();
			java.sql.Date dateObject = new java.sql.Date(calendar.getTime().getTime());
			ud.setDor(dateObject);
			maxAmount = getBidAmount(ud.getUserbidId());
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getTransactionId());
			stmt.setDouble(2, maxAmount);
			stmt.setDate(3, dateObject);
			stmt.setString(4, "Transation done for bid");
			stmt.setInt(5, ud.getUserId());
			stmt.setInt(6, ud.getBidId());
			stmt.setInt(7, ud.getBidId());
			int inserted = stmt.executeUpdate();
			if (inserted > 0) {
				ud.setCardNo(getUserCardNo(ud.getUserId()));
				ud.setBidAmount(maxAmount);
				return ud;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return ud;
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ud;

	}

	private int getTransactionId() {
		String sql = "SELECT MAX(TRANSACTIONID) FROM transaction";
		int value = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
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
		return value;

	}

	private double getBidAmount(int bidid) {
		String sql = "select biddingprice from userbid where userbidid=?";
		Connection conn = null;
		double maxAmount = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bidid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				maxAmount = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxAmount;

	}

	private long getUserCardNo(int userid) {
		String sql = "select cardnumber from accountdetails where useridref=?";
		Connection conn = null;
		long number = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				number = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;

	}

	public Transaction getTransactionInfo(int bidid) {
		String sql = "select transactionid,date(transactiondate),transactionamount from transaction where bididref = ?";

		Connection conn = null;
		Transaction transaction = new Transaction();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bidid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				transaction.setTransactionId(rs.getInt(1));
				transaction.setTransactionDate(DateUtil.normalDateFormat(rs.getString(2)));
				transaction.setTransactionAmount(rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return transaction;
	}

	public boolean sendMessage(int userid, String message, String subject) {
		String sql = "INSERT INTO FEEDBACK(FEEDBACKID,MESSAGE,SUBJECT,FROMID,TOID) VALUES (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getFeedBackId());
			stmt.setString(2, message);
			stmt.setString(3, subject);
			stmt.setInt(4, 1);
			stmt.setInt(5, userid);
			int inserted = stmt.executeUpdate();
			if (inserted > 0) {
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

	private int getFeedBackId() {
		String sql = "SELECT MAX(FEEDBACKID) FROM FEEDBACK";
		int value = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				return value;
			} else {
				value = rs.getInt(1);
				value++;
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
		return value;
	}
	
	
	public ArrayList<Feedback> viewFeedBack(String userid, String role) {
		String sql = "SELECT FEEDBACKID,MESSAGE,SUBJECT,FROMID,TOID FROM FEEDBACK WHERE TOID = (SELECT USERID FROM USERDETAILS WHERE LOGINID = ?) ORDER BY FEEDBACKID DESC LIMIT 50";
		ArrayList<Feedback> feedback = new ArrayList<Feedback>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Feedback fback = new Feedback();
				fback.setFeedbackId(rs.getInt(1));
				fback.setMessage(rs.getString(2));
				fback.setSubject(rs.getString(3));
				fback.setToName(getName(rs.getInt(4)));
				fback.setFromName(getName(rs.getInt(5)));
				feedback.add(fback);
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
		return feedback ;
	}

	public ArrayList<Transaction> viewTransaction(String userid, String role) {
		String sql = null;
		if (("ADMIN").equalsIgnoreCase(role)) {
			sql = "SELECT TRANSACTIONID,TRANSACTIONAMOUNT,DATE(TRANSACTIONDATE),PURPOSE,PAYERID,SELLERID,BIDIDREF FROM TRANSACTION ";
		} else if (("Seller").equalsIgnoreCase(role)) {
			sql = "SELECT TRANSACTIONID,TRANSACTIONAMOUNT,DATE(TRANSACTIONDATE),PURPOSE,PAYERID,SELLERID,BIDIDREF FROM TRANSACTION WHERE SELLERID = (SELECT USERID FROM USERDETAILS WHERE LOGINID = ?)";
		} else if (("Buyer").equalsIgnoreCase(role)) {
			sql = "SELECT TRANSACTIONID,TRANSACTIONAMOUNT,DATE(TRANSACTIONDATE),PURPOSE,PAYERID,SELLERID,BIDIDREF FROM TRANSACTION WHERE PAYERID = (SELECT USERID FROM USERDETAILS WHERE LOGINID = ?)";
		}
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (!"ADMIN".equalsIgnoreCase(role)) {
				stmt.setString(1, userid);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Transaction tran = new Transaction();
				tran.setTransactionId(rs.getInt(1));
				tran.setTransactionAmount(rs.getDouble(2));
				tran.setTransactionDate(DateUtil.normalDateFormat(rs.getString(3)));
				tran.setPurpose(rs.getString(4));
				tran.setBuyerName(getName(rs.getInt(5)));
				tran.setSellerName(getName(rs.getInt(6)));
				tran.setItemName(getItemName(rs.getInt(7)));
				transaction.add(tran);
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
		return transaction;
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

	private String getName(int userid) {
		String sql = "select firstname,lastname from  userdetails where userid = ?";
		Connection conn = null;
		String name = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1) + " " + rs.getString(2);
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
		return name;

	}

	public boolean sendMessage(String userid, String message, String subject) {
		
		String sql = "INSERT INTO FEEDBACK(FEEDBACKID,MESSAGE,SUBJECT,FROMID,TOID) VALUES (?,?,?,(SELECT USERID FROM USERDETAILS WHERE LOGINID = ?),?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getFeedBackId());
			stmt.setString(2, message);
			stmt.setString(3, subject);
			stmt.setString(4, userid);
			stmt.setInt(5, 1);
			int inserted = stmt.executeUpdate();
			if (inserted > 0) {
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

	public boolean updateCategory(String categoryname, String categoryId) {
		String sql = "update category set categoryname = ? where categoryid = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoryname);
			stmt.setInt(2, Integer.parseInt(categoryId));
			int inserted = stmt.executeUpdate();
			if (inserted > 0) {
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

	public boolean deleteCategory(String categoryId) {
		String sql = "delete from  category  where categoryid = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(categoryId));
			int inserted = stmt.executeUpdate();
			if (inserted > 0) {
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
}
