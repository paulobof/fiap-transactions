package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.ReportDTO;

public interface ReportService {

    public ReportDTO generateReport(Long ra);
}
