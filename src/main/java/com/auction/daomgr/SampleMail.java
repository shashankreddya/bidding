package com.auction.daomgr;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SampleMail {  
 public static void main(String[] args) {  
  
  String host="smtp.gmail.com";  
  final String user="sankarmca42@gmail.com";//change accordingly  
  final String password="8662bujjiumma";//change accordingly  
    
  String to="sankarraok.mca@gmail.com";//change accordingly  
  
   //Get the session object  
  Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");  
     
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("Testing");  
     message.setText("This is simple program of sending email using JavaMail API");  
       
    //send the message  
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();}  
 }  
}  
