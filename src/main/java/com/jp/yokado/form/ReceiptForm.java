package com.jp.yokado.form;

import java.util.ArrayList;
import java.util.List;

import com.jp.yokado.model.Receipt;

public class ReceiptForm {
    private List<Receipt> receipts = new ArrayList<>();

    public ReceiptForm() {
        this.receipts.add(new Receipt());
        this.receipts.add(new Receipt());
        this.receipts.add(new Receipt());
        this.receipts.add(new Receipt());
        this.receipts.add(new Receipt());
    }

    public void appendRow() {
        this.receipts.add(new Receipt());
    }

    public void removeRow(int index) {
        this.receipts.remove(index);
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}