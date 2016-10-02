package com.bank.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal accountNumber;
	private String accountName;
	private String accountType;
	private double balance;
	public BigDecimal getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(BigDecimal accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAcCreationDate() {
		return acCreationDate;
	}
	public void setAcCreationDate(String acCreationDate) {
		this.acCreationDate = acCreationDate;
	}
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	public int getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	private String pin;
	private String acCreationDate;
	private int expYear;
	private int expMonth;
	private int securityCode;
	

}
