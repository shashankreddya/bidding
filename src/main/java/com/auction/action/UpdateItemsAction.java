package com.auction.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.auction.daomgr.ItemsDaoManager;
import com.auction.pojo.Category;
import com.auction.pojo.Items;
import com.auction.util.UtilConstants;

public class UpdateItemsAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String target = "/updateItem.jsp";
		try {
				boolean flag = false;
				flag = updateItem(request, response);
				if (flag) {
					target = "/adminhomepage.jsp";
					request.setAttribute("status", "Item updated successfully");
				} else {
					target = "/updateItem.jsp";
					request.setAttribute("status", "Item not updated. Please try again. ");
				}
		} catch (Exception e) {
			target = "/updateItem.jsp";
			request.setAttribute("status", "Please Try again with proper data");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}
	}

	private boolean updateItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024);
		String destinationDir = request.getSession().getServletContext().getRealPath("tmpImage");
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		boolean flag = false;
		Items bidItems = new Items();
		List items = uploadHandler.parseRequest(request);
		Iterator itr = items.iterator();
		FileInputStream fileInputStream = null;
		int length = 0;
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				System.out.println("Field Name = " + item.getFieldName() + ", Value = " + item.getString());
				if (item.getFieldName().equals("itemname") && StringUtils.isNotBlank(item.getString())) {
					bidItems.setItemName(item.getString());
				}
				if (item.getFieldName().equals("itemid") && StringUtils.isNotBlank(item.getString())) {
					String itemid = item.getString();
					bidItems.setItemId(Integer.parseInt(itemid));
				}
				if (item.getFieldName().equals("summary") && StringUtils.isNotBlank(item.getString())) {
					bidItems.setSummary(item.getString());
				}
				if (item.getFieldName().equals("description") && StringUtils.isNotBlank(item.getString())) {
					bidItems.setDescription(item.getString());
				}
				if (item.getFieldName().equals("itemprice") && StringUtils.isNotBlank(item.getString())) {
					String price = item.getString();
					bidItems.setPrice(Double.parseDouble(price));
				}

			} else {
				File file = new File(destinationDir, new File(item.getName()).getName());
				item.write(file);
				length = (int) file.length();
				fileInputStream = new FileInputStream(file);
				if (item.getFieldName().equals("smallImage")) {
					bidItems.setSmallImage(fileInputStream);
					bidItems.setSmallImgLength(length);
				} else if (item.getFieldName().equals("bigImage")) {
					bidItems.setBigImage(fileInputStream);
					bidItems.setBigImgLength(length);
				}
				file.deleteOnExit();
			}
		}
		flag = new ItemsDaoManager().updateItems(bidItems);

		return flag;
	}
}
