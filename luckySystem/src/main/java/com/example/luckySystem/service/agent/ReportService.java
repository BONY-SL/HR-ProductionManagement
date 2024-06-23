package com.example.luckySystem.service.agent;
import com.example.luckySystem.dto.agent.AgentReportDTO;
import com.example.luckySystem.dto.agent.ProductionReportDTO;
import com.example.luckySystem.dto.issue.IssueReportDTO;
import com.example.luckySystem.repo.bottles.DailyFinishedRepostory;
import com.example.luckySystem.repo.bottles.ProductsForLoadingRepo;
import com.example.luckySystem.repo.issue.DailyIssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ReportService {

    @Autowired
    private ProductsForLoadingRepo productsForLoadingRepo;

    @Autowired
    private DailyFinishedRepostory dailyFinishedRepository;

    @Autowired
    private DailyIssueRepo dailyIssueRepo;


    /*
    This Class Create For Manage the Agents Purchase Details
     */

    public List<AgentReportDTO> getDailyReportagent(Date date) {

        Date formattedDate = formatDate(date);

        return productsForLoadingRepo.findDailyReportByDate(formattedDate);
    }


    public List<AgentReportDTO> getReportByDateRange(Date fromDate, Date toDate) {
        Date formattedFromDate = formatDate(fromDate);
        Date formattedToDate = formatDate(toDate);

        return productsForLoadingRepo.findReportByDateRange(formattedFromDate, formattedToDate);
    }


    public List<AgentReportDTO> getReportByMonth(int month, int year) {

        return productsForLoadingRepo.findReportByMonth(month, year);
    }

    //This method Create For Set Java DataFormat to Database Data Format
    private Date formatDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);
        return java.sql.Date.valueOf(formattedDate);
    }

    public List<ProductionReportDTO> getDailyProductionsByDate(Date date) {
        Date formattedDate = formatDate(date);
        return dailyFinishedRepository.findDailyProductionsByDate(formattedDate);
    }

    public List<IssueReportDTO> getDailyIssuesByDate(Date date) {
        Date formattedDate = formatDate(date);
        return dailyIssueRepo.findDailyIssuesByDate(formattedDate);
    }

    public List<ProductionReportDTO> getProductionsByDateRange(Date fromDate, Date toDate) {
        Date formattedFromDate = formatDate(fromDate);
        Date formattedToDate = formatDate(toDate);
        return dailyFinishedRepository.findProductionsByDateRange(formattedFromDate, formattedToDate);
    }

    public List<IssueReportDTO> getIssuesByDateRange(Date fromDate, Date toDate) {
        Date formattedFromDate = formatDate(fromDate);
        Date formattedToDate = formatDate(toDate);
        return dailyIssueRepo.findIssuesByDateRange(formattedFromDate, formattedToDate);
    }

    public List<ProductionReportDTO> getProductionsByMonth(int month, int year) {
        return dailyFinishedRepository.findProductionsByMonth(month, year);
    }

    public List<IssueReportDTO> getIssuesByMonth(int month, int year) {
        return dailyIssueRepo.findIssuesByMonth(month, year);
    }

}
