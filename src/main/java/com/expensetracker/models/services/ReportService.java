package com.expensetracker.models.services;

import com.expensetracker.models.Repository.ReportRepository;
import com.expensetracker.models.entities.Report;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService {
    private final ReportRepository reportRepository = new ReportRepository();

    public void generateReport(Report report) {
        reportRepository.generate(report);
    }
}
