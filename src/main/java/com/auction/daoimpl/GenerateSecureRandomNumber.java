package com.auction.daoimpl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
 
public class GenerateSecureRandomNumber {
 
  public static void main(String[] args) {

    try {
    	int i = new Random().nextInt(9999);
    	System.out.println(i);
	
    } catch (Exception e) {
    }
 
  }
}