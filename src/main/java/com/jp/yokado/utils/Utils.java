package com.jp.yokado.utils;

import java.util.List;

import com.jp.yokado.model.Receipt;


public class Utils {

    public static boolean averageOver500(List<Receipt> receipts) {
        Double averageValue = receipts.stream().mapToInt(Receipt::getPoint).average().orElse(0.0);
        if(averageValue < 500){
            return false;
        }
        return true;
    }
}
