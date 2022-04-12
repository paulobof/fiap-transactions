package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.enums.StatusEnum;
import com.fiap.transactionsAPI.utils.Constants;

import java.time.LocalDateTime;

public class ApprovalDTO {

    private LocalDateTime approvalTime;
    private StatusEnum statusEnum;
    private Long idTransaction;
    private String message;

    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void approvalFailed() {
        this.setApprovalTime(LocalDateTime.now());
        this.setStatusEnum(StatusEnum.NOT_APROVED);
        this.setMessage(Constants.NON_EXISTING_CARD);
    }

    public void approvalSucess() {
        this.setApprovalTime(LocalDateTime.now());
        this.setStatusEnum(StatusEnum.APROVED);
        this.setMessage(Constants.PURCHASE_MADE_SUCCESSFULLY);
    }

    public void approvalDenied(){
        this.setApprovalTime(LocalDateTime.now());
        this.setStatusEnum(StatusEnum.NOT_APROVED);
        this.setMessage(Constants.INSUFFICIENT_BALANCE);
    }

}
