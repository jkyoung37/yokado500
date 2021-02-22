package com.jp.yokado.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.jp.yokado.model.Receipt;

public class ReceiptForm {
    private List<Receipt> receipts = new ArrayList<>();
    
    @NotNull
    private Integer maxPoint;
    
    public Integer getMaxPoint() {
		return maxPoint;
	}

	public ReceiptForm() {
    }

    public void appendRow() {
        this.receipts.add(new Receipt());
    }

    public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
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