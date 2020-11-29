package com.jp.yokado.validation;

import java.util.List;

import com.jp.yokado.model.Receipt;

public class Validations {

    public static boolean isNull(List<Receipt> receipts) {
        if (receipts.size() > 0) {
            for (Receipt item : receipts) {
                if (item.getPoint() == null) {
                    item.setErrorCode(1);
                    item.setErrorMessage("値を入力してください");
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
   }

}
