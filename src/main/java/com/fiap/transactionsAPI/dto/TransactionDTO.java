package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.enums.StatusEnum;

import java.time.LocalDateTime;

public class TransactionDTO {

    private LocalDateTime approvalTime;
    private StatusEnum statusEnum;

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
}
