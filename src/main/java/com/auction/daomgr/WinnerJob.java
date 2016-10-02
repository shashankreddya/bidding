package com.auction.daomgr;
import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.auction.daoimpl.ItemsDaoImpl;
import com.auction.pojo.UserDetails;

public class WinnerJob implements Job{
	private ArrayList<UserDetails> userDetails;

	public ArrayList<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(ArrayList<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	
	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		sentMails();
	}
	
	private void sentMails(){
		userDetails = new ItemsDaoManager().getBidderWinners();
		setUserDetails(userDetails);
		String message , subject= null;
		boolean feedback;
		for(UserDetails ud: userDetails){
			subject = "Congratulation You Won the bid.";
			UserDetails  user =new ItemsDaoManager().makeTransaction(ud);
			
			boolean flag = new TransactionMgr().transferMoney(ud.getBidAmount(),ud.getCardNo()); 
			if(flag){
				message = "Bid amount :"+ud.getBidAmount()+" deducted from user Account "+ ud.getCardNo();
			}else{
				message = "Bid amount :"+ud.getBidAmount()+" try to deduct from user Account "+ ud.getCardNo()+". But Transaction failed. Please contact administrator for futher.";
			}
			feedback = new ItemsDaoManager().sendMessage(user.getUserId(),message,subject);
		}
	}
	
/*public static void main(String a[]){
	 new ItemsDaoManager().getBidderWinners();
	
	}
*/
}
