package com.example.classreservation.vo;

public class RoomResiseterContent {

 
  public final String room;
  public final String content1;
  public final String content2;
  public final String content3;
  public final String content4;
  public final String content5;


 

 public  RoomResiseterContent(String room,String content1,String content2,String content3,String content4,String content5){
    
    this.room = room;
    this.content1 = content1;
    this.content2 = content2;
    this.content3 = content3;
    this.content4 = content4;
    this.content5 = content5;
    
  }

  public String getroom(){
    return room;
  }
  public String getcontent1() {
    return content1;
  }
  public String getcontent2() {
    return content2;
  }
  public String getcontent3() {
    return content3;
  }
  public String getcontent4() {
    return content4;
  }
  public String getcontent5() {
    return content5;
  }
  
}