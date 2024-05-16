package com.example.luckySystem.service.bottles;
import com.example.luckySystem.dto.bottles.*;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.agent.AgentRepo;
import com.example.luckySystem.repo.bottles.*;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.service.employee.EmployeeService;
import com.example.luckySystem.util.SerializeCurrentBottleStock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyEmptyBottleUnitService {

    @Autowired
    private DailyFinishedRepostory dailyFinishedRepostory;

    @Autowired
    private DailyEmptyBottleUnitRepository repository;

    @Autowired
    private DailyDamagesByEmployeeRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CompanyBottleStockRepository repositoryNewBottle;

    @Autowired
    private ProductsForLoadingRepo productsForLoadingRepo;

    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private SerializeCurrentBottleStock serializeCurrentBottleStock;


    public DailyEmptyBottleUnit saveDailyEmptyBottleUnit(EmptyBottleDTO dto) {

        DailyEmptyBottleUnit entity = modelMapper.map(dto, DailyEmptyBottleUnit.class);

        //add to Current Washing

        CurrentBottleStatusDTO currentBottleStatusDTO=serializeCurrentBottleStock.deserializebottleStock();

        currentBottleStatusDTO.setWoshing(currentBottleStatusDTO.getWoshing()+dto.getFor_washing());
        if(currentBottleStatusDTO.getLording()>dto.getFor_washing()) {

            currentBottleStatusDTO.setLording(currentBottleStatusDTO.getLording()-dto.getFor_washing());
        }

        serializeCurrentBottleStock.serializebottleStock(currentBottleStatusDTO);

        System.out.println("Add Washing");
        System.out.println("Washing"+ serializeCurrentBottleStock.deserializebottleStock().getWoshing());
        System.out.println("Production"+ serializeCurrentBottleStock.deserializebottleStock().getProduction());
        System.out.println("Lording"+ serializeCurrentBottleStock.deserializebottleStock().getLording());

        repository.save(entity);
        return entity;
    }


    //get all add empty bottles
    public List<EmptyBottleDTO> getAllEmptyBottles() {
        List<DailyEmptyBottleUnit> units = repository.findAll();
        //System.out.println(units.get(units.toArray().length-1).getFor_washing());
        return units.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private EmptyBottleDTO convertEntityToDTO(DailyEmptyBottleUnit unit) {

        return new EmptyBottleDTO(unit.getId(), unit.getEmpty_bottles(), unit.getDamage_bottles(), unit.getSubmit_time(), unit.getSubmit_date(), unit.getFor_washing());
    }


    public void updateEmptyBottle(EmptyBottleDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
       // System.out.println(dto);
        DailyEmptyBottleUnit unit = repository.findById(dto.getId()).orElseThrow();
        unit.setId(dto.getId());
        unit.setEmpty_bottles(dto.getEmpty_bottles());
        unit.setDamage_bottles(dto.getDamage_bottles());
        unit.setFor_washing(dto.getEmpty_bottles()-dto.getDamage_bottles());
        repository.save(unit);
    }

    public DailyDamageBottleByEmployee saveDailyDamagesByEmployee(DamageBottleDTO dto){
        if(!employeeService.employeeExists(dto.getEmployee_id())) {
            throw new AppException("Invalid Employee ID: " + dto.getEmployee_id(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepo.findById(dto.getEmployee_id())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));

        DailyDamageBottleByEmployee entity = modelMapper.map(dto, DailyDamageBottleByEmployee.class);

        //reduce the Damages
        CurrentBottleStatusDTO currentBottleStatusDTO=serializeCurrentBottleStock.deserializebottleStock();

        currentBottleStatusDTO.setWoshing(currentBottleStatusDTO.getWoshing()-dto.getDamage_amount());

        serializeCurrentBottleStock.serializebottleStock(currentBottleStatusDTO);

        System.out.println("Add Damage");
        System.out.println("Washing"+ serializeCurrentBottleStock.deserializebottleStock().getWoshing());
        System.out.println("Production"+ serializeCurrentBottleStock.deserializebottleStock().getProduction());
        System.out.println("Lording"+ serializeCurrentBottleStock.deserializebottleStock().getLording());

        entity.setEmployee(employee);
        return repo.save(entity);
    }


    //get all demages
    public List<DamageBottleDTO> getAllDamageBottles() {

        List<DailyDamageBottleByEmployee> units = repo.findAll();
        return units.stream().map(this::damageconvertEntityToDTO).collect(Collectors.toList());
    }

    private DamageBottleDTO damageconvertEntityToDTO(DailyDamageBottleByEmployee unit) {

        return new DamageBottleDTO(unit.getDaily_damage_id(),unit.getUnit_type(),unit.getEmployee().getEmployee_id(),unit.getDamage_amount(),unit.getDate());
    }

    public void updateDamageBottle(DamageBottleDTO dto) {

        if(!employeeService.employeeExists(dto.getEmployee_id())) {

            throw new AppException("Invalid Employee ID: " + dto.getEmployee_id(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepo.findById(dto.getEmployee_id())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));


        DailyDamageBottleByEmployee unit = repo.findById(dto.getDaily_damage_id()).orElseThrow();

        unit.setDaily_damage_id(dto.getDaily_damage_id());
        unit.setDamage_amount(dto.getDamage_amount());
        unit.setEmployee(employee);

        repo.save(unit);
    }


    public DailyFinished saveDailyFinishedMilk(DailyFinishedDTO dto) {

        DailyFinished entity = modelMapper.map(dto, DailyFinished.class);

        //set Current Production
        CurrentBottleStatusDTO currentBottleStatusDTO=serializeCurrentBottleStock.deserializebottleStock();

        currentBottleStatusDTO.setWoshing(currentBottleStatusDTO.getWoshing()-dto.getAmount());
        currentBottleStatusDTO.setProduction(currentBottleStatusDTO.getProduction()+dto.getAmount());

        serializeCurrentBottleStock.serializebottleStock(currentBottleStatusDTO);

        System.out.println("Add Production");
        System.out.println("Washing"+ serializeCurrentBottleStock.deserializebottleStock().getWoshing());
        System.out.println("Production"+ serializeCurrentBottleStock.deserializebottleStock().getProduction());
        System.out.println("Lording"+ serializeCurrentBottleStock.deserializebottleStock().getLording());
        return dailyFinishedRepostory.save(entity);

    }


    //get daily finished
    public List<DailyFinishedDTO> getDailyFinishedMilkBottle() {

        List<DailyFinished> units = dailyFinishedRepostory.findAll();
        return units.stream().map(this::finishedconvertEntityToDTO).collect(Collectors.toList());
    }

    private DailyFinishedDTO finishedconvertEntityToDTO(DailyFinished unit) {

        return new DailyFinishedDTO(unit.getFinished_id(),unit.getAmount(),unit.getBatch_code(),unit.getSubmit_time(),unit.getSubmit_date(),unit.getFinished_status());
    }

    public void updatefinishedMilk(DailyFinishedDTO dto) {

        DailyFinished unit = dailyFinishedRepostory.findById(dto.getFinished_id()).orElseThrow();

        unit.setFinished_id(dto.getFinished_id());
        unit.setAmount(dto.getAmount());
        unit.setBatch_code(dto.getBatch_code());
        unit.setFinished_status(dto.getFinished_status());
        dailyFinishedRepostory.save(unit);
    }




    //for maintain company bottle stock continuously
    public CompanyBottleStock updateOrSaveCompanyBottleStock(CompanyBottleStockDTO dto) {
        CompanyBottleStock stock = repositoryNewBottle.findTopByOrderByDateDesc()
                .orElse(new CompanyBottleStock());
        stock.setDate(dto.getDate());
        stock.setTotal_bottle(dto.getTotalBottle()+stock.getTotal_bottle());
        return repositoryNewBottle.save(stock);
    }

    public GoodProductsForLoading addLording(ProductsForLoadingDTO dto) {
        Agent agent = agentRepo.findById(dto.getAg_id())
                .orElseThrow(() -> new AppException("Invalid Agent ID not found", HttpStatus.BAD_REQUEST));

        GoodProductsForLoading entity = modelMapper.map(dto, GoodProductsForLoading.class);
        entity.setAg_id(agent);

        //set lording
        CurrentBottleStatusDTO currentBottleStatusDTO=serializeCurrentBottleStock.deserializebottleStock();


        currentBottleStatusDTO.setProduction(currentBottleStatusDTO.getProduction()-dto.getAmount());
        currentBottleStatusDTO.setLording(currentBottleStatusDTO.getLording()+dto.getAmount());

        serializeCurrentBottleStock.serializebottleStock(currentBottleStatusDTO);

        System.out.println("Add Lording");
        System.out.println("Washing"+ serializeCurrentBottleStock.deserializebottleStock().getWoshing());
        System.out.println("Production"+ serializeCurrentBottleStock.deserializebottleStock().getProduction());
        System.out.println("Lording"+ serializeCurrentBottleStock.deserializebottleStock().getLording());

        return productsForLoadingRepo.save(entity);

    }

    //get all lording
    public List<ProductsForLoadingDTO> getAllLoading() {

        List<GoodProductsForLoading> units = productsForLoadingRepo.findAll();
        return units.stream().map(this::lordingconvertEntityToDTO).collect(Collectors.toList());
    }

    private ProductsForLoadingDTO lordingconvertEntityToDTO(GoodProductsForLoading unit) {

        return new ProductsForLoadingDTO(unit.getLoading_id(),unit.getAmount(),unit.getBatch_code(),unit.getSubmit_time(),unit.getSubmit_date(),unit.getAg_id().getAgent_id());
    }

    public void updateLording(ProductsForLoadingDTO dto) {

        GoodProductsForLoading unit = productsForLoadingRepo.findById(dto.getLoading_id()).orElseThrow();

        Agent agent=agentRepo.findById(dto.getAg_id())
                .orElseThrow(() -> new AppException("Agent not found", HttpStatus.NOT_FOUND));

        unit.setAmount(dto.getAmount());
        unit.setBatch_code(dto.getBatch_code());
        unit.setAg_id(agent);
        unit.setSubmit_time(dto.getSubmit_time());
        unit.setSubmit_date(dto.getSubmit_date());
        productsForLoadingRepo.save(unit);
    }


}
