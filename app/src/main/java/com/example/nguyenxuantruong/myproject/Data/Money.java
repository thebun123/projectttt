package com.example.nguyenxuantruong.myproject.Data;

/**
 * Created by nguyenxuantruong on 5/13/17.
 */

public class Money {

    private String type;
    private float value;

    public Money(String type,int value){
        this.type = type;
        this.value = value;
    }

    public float exchange(Money money1, Money money2){

            float moneyVN = money1.getValue();
            float moneyREsult = moneyVN/(money2.getValue()) + moneyVN%(money2.getValue());
        return moneyREsult;
    }

    public Money(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
