package com.example.classreservation.vo;

public class RoomResiseterTime {

 
  public final String time1;
  public final String time2;
  public final String time3;
  public final String time4;
  public final String time5;

 

 public  RoomResiseterTime(String time1,String time2,String time3,String time4,String time5){
    
  
    this.time1 = time1;
    this.time2 = time2;
    this.time3 = time3;
    this.time4 = time4;
    this.time5 = time5;
    
  }


  public String gettime1() {
    return time1;
  }
  public String gettime2() {
    return time2;
  }
  public String gettime3() {
    return time3;
  }
  public String gettime4() {
    return time4;
  }
  public String gettime5() {
    return time5;
  }
  
}