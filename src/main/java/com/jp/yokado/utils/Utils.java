package com.jp.yokado.utils;

import java.util.List;

import com.jp.yokado.model.Receipt;


public class Utils {

    public static boolean Over2500Point(List<Receipt> receipts) {
        double allSumValue = receipts.stream().mapToInt(Receipt::getPoint).sum();
        if(allSumValue < 2500){
            return false;
        }
        return true;
    }
}
