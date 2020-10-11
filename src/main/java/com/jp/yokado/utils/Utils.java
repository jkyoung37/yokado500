package com.jp.yokado.utils;

import java.util.List;

import com.jp.yokado.model.Receipt;

public class Utils {

    public static boolean OverMaxPoint(List<Receipt> receipts) {
        double allSumValue = receipts.stream().mapToInt(Receipt::getPoint).sum();
        if (allSumValue < Numbers.POINT) {
            return false;
        }
        return true;
    }

    public static int[] convertIntegers(List<Receipt> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).getPoint();
        }
        return ret;
    }
    
    public static int[] convertIntegers2(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
