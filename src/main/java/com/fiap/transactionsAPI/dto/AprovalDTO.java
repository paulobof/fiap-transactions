package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.enums.StatusEnum;

import java.time.LocalDateTime;

public class AprovalDTO {

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
}
