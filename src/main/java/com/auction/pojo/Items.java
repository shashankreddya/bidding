package com.auction.pojo;

import java.io.FileInputStream;
import java.io.Serializable;

public class Items implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int itemId;
	private int categoryId;
	private String itemName;
	private String CategoryName;
	private String userName;
	private int bigImgLength;
	private int smallImgLength;
	public int getBigImgLength() {
		return bigImgLength;
	}
	public void setBigImgLength(int bigImgLength) {
		this.bigImgLength = bigImgLength;
	}
	public int getSmallImgLength() {
		return smallImgLength;
	}
	public void setSmallImgLength(int smallImgLength) {
		this.smallImgLength = smallImgLength;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public FileInputStream getSmallImage() {
		return smallImage;
	}
	public void setSmallImage(FileInputStream smallImage) {
		this.smallImage = smallImage;
	}
	public FileInputStream getBigImage() {
		return bigImage;
	}
	public void setBigImage(FileInputStream bigImage) {
		this.bigImage = bigImage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String description;
	private String summary;
	private String smallImagePath;
	private String bigImagePath;
	public String getSmallImagePath() {
		return smallImagePath;
	}
	public void setSmallImagePath(String smallImagePath) {
		this.smallImagePath = smallImagePath;
	}
	public String getBigImagePath() {
		return bigImagePath;
	}
	public void setBigImagePath(String bigImagePath) {
		this.bigImagePath = bigImagePath;
	}
	private FileInputStream smallImage;
	private FileInputStream bigImage;
	private double price; 
	private int bidId;
	private String bidStartDate;
	private String bidEndDate;
	private String bidStatus;
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public String getBidStartDate() {
		return bidStartDate;
	}
	public void setBidStartDate(String bidStartDate) {
		this.bidStartDate = bidStartDate;
	}
	public String getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	public String getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

}
