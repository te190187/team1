package com.example.classreservation.models.classReservation;

import java.util.ArrayList;
import java.util.List;


public class ReservationFrame {
  public Integer frameNumber;
  public List<ReservationClassroom> classrooms = new ArrayList<>();

  public ReservationFrame(Integer frameNumber) {
    this.frameNumber = frameNumber;
  }

  public void assign() {
    
  }
}
