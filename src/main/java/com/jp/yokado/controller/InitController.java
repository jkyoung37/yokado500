package com.jp.yokado.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jp.yokado.model.*;

@Controller
public class InitController {
    @RequestMapping("/Init")
    public String ViewInitPage(Model model){
        
        ArrayList<Receipt> receipts = new ArrayList();
        getTestData(receipts);
        
        model.addAttribute("recepits",receipts);
        return "Init";
    }


    private void getTestData(ArrayList<Receipt> testData){

        Receipt test1 = new Receipt(1,875);
        Receipt test2 = new Receipt(2,7);
        Receipt test3 = new Receipt(3,1);
        Receipt test4 = new Receipt(4,5);
        Receipt test5 = new Receipt(5,1275);
        Receipt test6 = new Receipt(6,75);

        testData.add(test1);
        testData.add(test2);
        testData.add(test3);
        testData.add(test4);
        testData.add(test5);
        testData.add(test6);
    }
}
