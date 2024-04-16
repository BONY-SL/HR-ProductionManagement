package com.example.luckySystem.service.bottles;

import com.example.luckySystem.dto.bottles.EmptyBottleDTO;
import com.example.luckySystem.entity.DailyEmptyBottleUnit;
import com.example.luckySystem.repo.bottles.DailyEmptyBottleUnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyEmptyBottleUnitService {

    @Autowired
    private DailyEmptyBottleUnitRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public DailyEmptyBottleUnit saveDailyEmptyBottleUnit(EmptyBottleDTO dto) {
        DailyEmptyBottleUnit entity = modelMapper.map(dto, DailyEmptyBottleUnit.class);
        return repository.save(entity);
    }


    public List<EmptyBottleDTO> getAllEmptyBottles() {
        List<DailyEmptyBottleUnit> units = repository.findAll();
        return units.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private EmptyBottleDTO convertEntityToDTO(DailyEmptyBottleUnit unit) {

        return new EmptyBottleDTO(unit.getId(), unit.getEmpty_bottles(), unit.getDamage_bottles(), unit.getSubmit_time(), unit.getSubmit_date(), unit.getFor_washing());
    }


    public void updateEmptyBottle(EmptyBottleDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        DailyEmptyBottleUnit unit = repository.findById(dto.getId()).orElseThrow();
        unit.setId(dto.getId());
        unit.setEmpty_bottles(dto.getEmpty_bottles());
        unit.setDamage_bottles(dto.getDamage_bottles());
        // Set other fields as necessary
        repository.save(unit);
    }

}
