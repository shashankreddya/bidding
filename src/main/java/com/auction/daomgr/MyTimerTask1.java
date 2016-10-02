package com.auction.daomgr;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MyTimerTask1 extends TimerTask {
    private final static int HOURS = 20;
    private final static int MINUTES = 23;


    @Override
    public void run() {
        long currennTime = System.currentTimeMillis();
        long stopTime = currennTime + 1000*60*10;
          while(stopTime != System.currentTimeMillis()){
              // Do your Job Here
            System.out.println("Start Job"+stopTime);
            System.out.println("End Job"+System.currentTimeMillis());
          }
    }
    @SuppressWarnings("deprecation")
	private static Date get12AMTmrw(){

        Date date = new java.util.Date(); 
           date.setHours(HOURS); 
           date.setMinutes(MINUTES); 
           return date;
      }
    //call this method from your servlet init method
    public static void startTask(){
        MyTimerTask task = new MyTimerTask();
        Timer timer = new Timer();  
        timer.schedule(task,get12AMTmrw(),1000*60*60*24);// for your case u need to give 1000*60*60*24
    }
    public static void main(String args[]){
        startTask();

    }

}