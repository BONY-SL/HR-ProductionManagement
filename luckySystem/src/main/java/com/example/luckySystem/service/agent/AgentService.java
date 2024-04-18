package com.example.luckySystem.service.agent;


import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.entity.Agent;
import com.example.luckySystem.repo.agent.AgentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AgentRepo agentRepo;

    public Agent saveNewAgentDetails(AgentDTO dto) {
        Agent entity = modelMapper.map(dto, Agent.class);
        return agentRepo.save(entity);
    }

    public List<AgentDTO> getallAgentDetails() {
        List<Agent> units = agentRepo.findAll();
        return units.stream().map(this::convertAgentEntityToDTO).collect(Collectors.toList());
    }

    private AgentDTO convertAgentEntityToDTO(Agent unit) {

        return new AgentDTO(unit.getAgent_id(),unit.getAgent_name(),unit.getAgency_name(),unit.getAddress(),unit.getEmail(),unit.getContact_number());
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
}
