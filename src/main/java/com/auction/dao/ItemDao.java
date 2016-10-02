package com.auction.dao;

import java.util.ArrayList;

import com.auction.pojo.Category;
import com.auction.pojo.Items;

public interface ItemDao {
	
	public ArrayList<Category> getCategories();
	public boolean addItems(Items items);

}
