package com.jp.yokado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import com.jp.yokado.form.ReceiptForm;
import com.jp.yokado.model.Receipt;
import com.jp.yokado.utils.*;
import com.jp.yokado.validation.Validations;

@Controller
@RequestMapping("/init")
public class InitController {

    @ModelAttribute
    public ReceiptForm setupForm() {
        return new ReceiptForm();
    }

    @GetMapping
    public String init(ReceiptForm form) {
        form.appendRow();
        return "index";
    }

    @PostMapping(params = "appendRow")
    public String appendRow(ReceiptForm form, BindingResult result) {
        form.appendRow();
        return "index";
    }

    @PostMapping(params = "removeIndex")
    public String submit(ReceiptForm form, @RequestParam int removeIndex, BindingResult result) {
        form.removeRow(removeIndex);
        return "index";
    }

    @PostMapping(params = "doCalculate")
    public String submit(Model model, ReceiptForm form, BindingResult result) {

        if(result.hasErrors()){
            return "index";
        }
       
        if(hasErrors(form)){
            return "index";
        }

        List<int[]> allPoints = new ArrayList<>();
        boolean isOverMaxpoint = Utils.OverMaxPoint(form.getReceipts());
        Integer minSum = 0;
        List<int[]> minSumResult = new ArrayList<>();

        if (isOverMaxpoint) {
            allPoints = getAllPoint(form);

            // Maxpoints Pick up
            for (int i = 0; i < allPoints.size(); i++) {
                int[] items = allPoints.get(i);
                int sum = 0;

                for (int j = 0; j < items.length; j++) {
                    sum += items[j];
                }

                if (sum >= Numbers.MAX_POINT) {
                    if (sum <= minSum.intValue() || minSum.intValue() == 0) {
                        minSum = sum;
                        minSumResult.add(items);
                    }
                }
            }
        } else {
            return "index";
        }

        if(minSumResult.size() == 0){
            return "index";
        }else{

            int[] miniNb;
            List<StringBuilder> resultList = new ArrayList();
            for(int[] item : minSumResult){
                miniNb = item;
                StringBuilder resultMSG = new StringBuilder();
                for (int i = 0; i < miniNb.length ; i++) {
                    int inputNm = miniNb[i];
                    if(i==0){
                        resultMSG.append(i+1);
                        resultMSG.append(" : ");
                    }
                    resultMSG.append(inputNm);
                }
                resultList.add(resultMSG);
            }

            model.addAttribute("result", resultList);
            return "resultPage";
        }
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

    private boolean hasErrors(ReceiptForm form){

        boolean isNull = Validations.isNull(form.getReceipts());
        if(isNull){
            return true;
        }
        return false;
    
    }

}
