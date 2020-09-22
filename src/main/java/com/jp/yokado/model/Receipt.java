package com.jp.yokado.model;

public class Receipt {

    private int no;
    private int point;

    public Receipt(int no, int point) {
        this.no = no;
        this.point = point;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}