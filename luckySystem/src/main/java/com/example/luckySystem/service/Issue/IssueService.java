package com.example.luckySystem.service.Issue;


import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.issue.IssueDTO;
import com.example.luckySystem.entity.Agent;
import com.example.luckySystem.entity.ProductionIssue;
import com.example.luckySystem.repo.agent.AgentRepo;
import com.example.luckySystem.repo.issue.IssueRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IssueRepo issueRepo;

    public ProductionIssue addNewIssue(String  issue) {
        ProductionIssue entity = new ProductionIssue();
        entity.setIssue_name(issue);
        return issueRepo.save(entity);
    }

    public List<IssueDTO> getIssueDetails() {
        List<ProductionIssue> issues= issueRepo.findAll();
        return issues.stream().map(this::convertIssueEntityToDTO).collect(Collectors.toList());
    }

    private IssueDTO convertIssueEntityToDTO(ProductionIssue unit) {

        return new IssueDTO(unit.getIssue_id(),unit.getIssue_name());
    }
}
