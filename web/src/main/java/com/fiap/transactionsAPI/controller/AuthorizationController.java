package com.fiap.transactionsAPI.controller;

import com.fiap.transactionsAPI.dto.ApprovalDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;
import com.fiap.transactionsAPI.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authorization")
public class AuthorizationController {

    private final TransactionService transactionService;

    public AuthorizationController(TransactionService transactionService){

        this.transactionService = transactionService;
    }

    @PostMapping
    public ApprovalDTO authorize(@RequestBody TransactionDTO transactionDTO) {
       return transactionService.authorize(transactionDTO);
    }
}
