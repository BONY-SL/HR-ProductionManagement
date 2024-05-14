package com.example.luckySystem.controller.issue;
import com.example.luckySystem.dto.issue.DailyIssuEmployeeDTO;
import com.example.luckySystem.dto.issue.GetMonthlyIssueDTO;
import com.example.luckySystem.dto.issue.IssueDTO;
import com.example.luckySystem.entity.DailyProductionIssuesByEmployee;
import com.example.luckySystem.entity.ProductionIssue;
import com.example.luckySystem.service.Issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class IssueContrroler {


    @Autowired
    private IssueService issueService;

    @PostMapping("/addNewIssue/{issue}")
    public ResponseEntity<?> addNewIssue(@PathVariable String  issue) {
        ProductionIssue savedEntity=issueService.addNewIssue(issue);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getIssueDetails")
    public ResponseEntity<List<IssueDTO>> getIssueDetails() {
        List<IssueDTO> issue = issueService.getIssueDetails();
        return ResponseEntity.ok().body(issue);
    }

    @PutMapping("/updateIssue")
    public ResponseEntity<?> updateIssue(@RequestBody IssueDTO dto) {
        issueService.updateIssue(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addDailyIssuesemployee")
    public ResponseEntity<DailyProductionIssuesByEmployee> addDailyIssues(@RequestBody DailyIssuEmployeeDTO dto) {
        //System.out.println("controller"+dto);
        DailyProductionIssuesByEmployee savedEntity = issueService.addDailyIssues(dto);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/gettAllIssueByEmployee")
    public ResponseEntity<List<DailyIssuEmployeeDTO>> gettAllIssueByEmployee() {
        List<DailyIssuEmployeeDTO> employeeIssue = issueService.gettAllIssueByEmployee();
        System.out.println(employeeIssue);
        return ResponseEntity.ok().body(employeeIssue);
    }

    @GetMapping("/getMonthlyIssues")
    public List<GetMonthlyIssueDTO> getMonthlyIssues(@RequestParam int month, @RequestParam int year) {

        System.out.println(month);
        System.out.println(year);
        return issueService.getMonthlyIssues(month, year);
    }


}
