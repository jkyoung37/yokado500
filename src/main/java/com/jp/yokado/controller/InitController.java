package com.jp.yokado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.jp.yokado.form.ReceiptForm;
import com.jp.yokado.model.Receipt;
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

        List<int[]> allPoints = getAllPoint(form);
        List<int[]> over2500 = new ArrayList<>();


        boolean isOverMaxpoint = Utils.OverMaxPoint(form.getReceipts());

        if (isOverMaxpoint) {
            for (int i = 0; i < allPoints.size(); i++) {
                int[] items = allPoints.get(i);
                int sum = 0;
    
                for (int j = 0; j < items.length; j++) {
                    sum += items[j];
                }
    
                if (sum >= Numbers.POINT) {
                    over2500.add(allPoints.get(i));
                } 
            }
        } else {

        }
        return "initPage";

    }

    private List<int[]> getAllPoint(ReceiptForm form) {
        List<Receipt> itemLists = form.getReceipts();
        int[] points = Utils.convertIntegers(itemLists);
        int n = points.length;
        boolean[] visited = new boolean[n];

        Combination.clear();
        for (int i = 1; i <= n; i++) {
            Combination.combination(points, visited, 0, n, i);
        }
        return Combination.getItem();
    }

}
