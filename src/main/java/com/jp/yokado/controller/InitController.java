package com.jp.yokado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jp.yokado.form.ReceiptForm;
import com.jp.yokado.utils.*;


@Controller
@RequestMapping("/init")
public class InitController {

 
    @ModelAttribute
    public ReceiptForm setupForm() {
      return new ReceiptForm();
    }

    @GetMapping
    public String init() {
        return "initPage";
    }

    @PostMapping(params = "appendRow")
    public String appendRow(ReceiptForm form, BindingResult result) {
        form.appendRow();
        return "initPage";
    }

    @PostMapping(params = "removeIndex")
    public String submit(ReceiptForm form, @RequestParam int removeIndex, BindingResult result) {
        form.removeRow(removeIndex);
        return "initPage";
    }

    @PostMapping(params = "doCalculate")
    public String submit(ReceiptForm form, BindingResult result) {
        

        int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];

        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            Combination.comb(arr, visited, 0, n, i);
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            Combination.combination(arr, visited, 0, n, i);
        }



        boolean over2500point = Utils.Over2500Point(form.getReceipts());
        if(over2500point){



        }else{
            
        }
        return "initPage";
        
    }

}
