package com.example.luckySystem.controller.agent;
import com.example.luckySystem.dto.agent.AgentReportDTO;
import com.example.luckySystem.dto.agent.ProductionReportDTO;
import com.example.luckySystem.dto.issue.IssueReportDTO;
import com.example.luckySystem.service.agent.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class ReportController {


    @Autowired
    private ReportService reportService;


    @GetMapping("/getDailyReportagent")
    public List<AgentReportDTO> getDailyReportagent(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return reportService.getDailyReportagent(date);
    }

    @GetMapping("/getWeeklyReportagent")
    public List<AgentReportDTO> getWeeklyReportagent(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {


        return reportService.getReportByDateRange(fromDate, toDate);
    }


    @GetMapping("/getMonthlyReportagent")
    public List<AgentReportDTO> getMonthlyReportagent(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        return reportService.getReportByMonth(month, year);
    }


    @GetMapping("/getDailyProductions")
    public List<ProductionReportDTO> getDailyProductions(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return reportService.getDailyProductionsByDate(date);
    }

    @GetMapping("/getDailyIssues")
    public List<IssueReportDTO> getDailyIssues(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return reportService.getDailyIssuesByDate(date);
    }


    @GetMapping("/getProductionsByDateRange")
    public List<ProductionReportDTO> getProductionsByDateRange(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return reportService.getProductionsByDateRange(fromDate, toDate);
    }

    @GetMapping("/getIssuesByDateRange")
    public List<IssueReportDTO> getIssuesByDateRange(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return reportService.getIssuesByDateRange(fromDate, toDate);
    }


    @GetMapping("/getProductionsByMonth")
    public List<ProductionReportDTO> getProductionsByMonth(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        return reportService.getProductionsByMonth(month, year);
    }

    @GetMapping("/getIssuesByMonth")
    public List<IssueReportDTO> getIssuesByMonth(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        return reportService.getIssuesByMonth(month, year);
    }
}