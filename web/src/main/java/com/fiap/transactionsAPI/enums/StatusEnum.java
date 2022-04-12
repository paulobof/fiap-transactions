package com.fiap.transactionsAPI.enums;

public enum StatusEnum {

    APROVED(1,"Transação aprovada."),
    NOT_APROVED(2, "Transação não aprovada."),
    INSUFFICIENT_BALANCE(3, "Saldo insuficiente.");

    public Integer code;
    public String description;


    StatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
