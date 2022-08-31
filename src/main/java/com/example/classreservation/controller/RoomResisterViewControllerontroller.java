package com.example.classreservation.controller;



import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import com.example.classreservation.vo.RoomResiseterTime;
import com.example.classreservation.vo.RoomResiseterContent;
import org.springframework.ui.Model;

@Controller
public class RoomResisterViewControllerontroller {

    boolean flag;

    @RequestMapping("/RoomResisetrView")
    public String index(Model model) {
        List<RoomResiseterTime> days = new ArrayList<RoomResiseterTime>();
        List<RoomResiseterContent> contents = new ArrayList<RoomResiseterContent>();
        
        for(int i = 0; i>10; i++){
        if(flag){
        RoomResiseterTime sunday = new RoomResiseterTime("10:00-11:00","11:15-12:15","13:15-14:15","14:30-15:30","15:45-16:45");
        days.add(sunday);
        RoomResiseterContent contentA =new RoomResiseterContent("101","a","a","a","a","a");
        contents.add(contentA);
        RoomResiseterContent contentB =new RoomResiseterContent("102","a","a","a","a","a");
        contents.add(contentB);
        RoomResiseterContent contentC =new RoomResiseterContent("103","a","a","a","a","a");
        contents.add(contentC);
        RoomResiseterContent contentD =new RoomResiseterContent("104","a","a","a","a","a");
        contents.add(contentD);
        RoomResiseterContent contentE =new RoomResiseterContent("201","a","a","a","a","a");
        contents.add(contentE);
        RoomResiseterContent contentF =new RoomResiseterContent("202","a","a","a","a","a");
        contents.add(contentF);
        RoomResiseterContent contentG =new RoomResiseterContent("301","a","a","a","a","a");
        contents.add(contentG);
        RoomResiseterContent contentH =new RoomResiseterContent("302","a","a","a","a","a");
        contents.add(contentH);
        RoomResiseterContent contentI =new RoomResiseterContent("303","a","a","a","a","a");
        contents.add(contentI);
        System.out.println(i);
        }
        else{
            RoomResiseterTime manday = new RoomResiseterTime("16:00-17:00","17:15-18:15","18:30-19:30","","");
        days.add(manday);
        RoomResiseterContent contentA =new RoomResiseterContent("101","a","a","a","","");
        contents.add(contentA);
        RoomResiseterContent contentB =new RoomResiseterContent("102","a","a","a","","");
        contents.add(contentB);
        RoomResiseterContent contentC =new RoomResiseterContent("103","a","a","a","","");
        contents.add(contentC);
        RoomResiseterContent contentD =new RoomResiseterContent("104","a","a","a","","");
        contents.add(contentD);
        RoomResiseterContent contentE =new RoomResiseterContent("201","a","a","a","","");
        contents.add(contentE);
        RoomResiseterContent contentF =new RoomResiseterContent("202","a","a","a","","");
        contents.add(contentF);
        RoomResiseterContent contentG =new RoomResiseterContent("301","a","a","a","","");
        contents.add(contentG);
        RoomResiseterContent contentH =new RoomResiseterContent("302","a","a","a","","");
        contents.add(contentH);
        RoomResiseterContent contentI =new RoomResiseterContent("303","a","a","a","","");
        contents.add(contentI);
        System.out.println(i);
        }
    }
    
   
    
        model.addAttribute("days", days);
        model.addAttribute("contents", contents);
        return "RoomResisetrView";
    }
}