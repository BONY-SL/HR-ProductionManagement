package com.example.luckySystem.service.agent;
import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.agent.AgentPurchaseDTO;
import com.example.luckySystem.entity.Agent;
import com.example.luckySystem.entity.GoodProductsForLoading;
import com.example.luckySystem.repo.agent.AgentRepo;
import com.example.luckySystem.repo.bottles.ProductsForLoadingRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private ProductsForLoadingRepo productsForLoadingRepo;

    public Agent saveNewAgentDetails(AgentDTO dto) {
        Agent entity = modelMapper.map(dto, Agent.class);
        return agentRepo.save(entity);
    }

    public List<AgentDTO> getallAgentDetails() {
        List<Agent> agents = agentRepo.findAllByDeletedAtIsNull();
        return agents.stream().map(this::convertAgentEntityToDTO).collect(Collectors.toList());
    }

    private AgentDTO convertAgentEntityToDTO(Agent unit) {

        return new AgentDTO(unit.getAgent_id(),unit.getAgent_name(),unit.getAgency_name(),unit.getAddress(),unit.getEmail(),unit.getContact_number(),unit.getDeletedAt(),unit.getDeleteReason());
    }

    public void updateAgentDetails(AgentDTO dto) {

        Agent unit = agentRepo.findById(dto.getAgent_id()).orElseThrow();

        unit.setAgent_id(dto.getAgent_id());
        unit.setAgent_name(dto.getAgent_name());
        unit.setAgency_name(dto.getAgency_name());
        unit.setAddress(dto.getAddress());
        unit.setEmail(dto.getEmail());
        unit.setContact_number(dto.getContact_number());

        agentRepo.save(unit);
    }

    // Agent Data not Deleted Permanently  update Set Delete Data and setDeleteReason
    //show user for deleteData and Delete Reason Null All fields
    public void deleteAgentDetails(Long agentId, String deleteReason) {
        Agent agent = agentRepo.findById(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));
        agent.setDeletedAt(new Date());
        agent.setDeleteReason(deleteReason);
        agentRepo.save(agent);
    }


    public void undoDeleteAgentDetails(Long agentId) {
        //System.out.println(agentId);
        Agent agent = agentRepo.findById(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));
        agent.setAgent_id(agentId);
        agent.setDeletedAt(null);
        agent.setDeleteReason(null);
        agentRepo.save(agent);
    }

    //return the list monthly purchase milk bottles amount selected Agent
    public List<AgentPurchaseDTO> getPurchasesByAgentIdAndMonthYear(Long agentId, int month, int year) {
        Date startDate = getStartDate(year, month);
        Date endDate = getEndDate(year, month);

        System.out.println(startDate);

        List<GoodProductsForLoading> products = productsForLoadingRepo.findByAgIdAndSubmitDateBetweenOrderBySubmitDateAsc(agentId, startDate, endDate);

        System.out.println(products);
        return products.stream()
                .map(product -> new AgentPurchaseDTO(product.getAmount(), product.getSubmit_date()))
                .collect(Collectors.toList());
    }

    //get value for start date
    private Date getStartDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //get value for end date
    private Date getEndDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
