package com.jp.yokado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.jp.yokado.form.ReceiptForm;
import com.jp.yokado.utils.Utils;

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
    public String submit(ReceiptForm form, @RequestParam int removeIndex) {
        form.removeRow(removeIndex);
        return "initPage";
    }

    @PostMapping(params = "doCalculate")
    public String submit(ReceiptForm form, BindingResult result) {
        Utils.averageOver500(form.getReceipts());
        return "initPage";
    }
}
