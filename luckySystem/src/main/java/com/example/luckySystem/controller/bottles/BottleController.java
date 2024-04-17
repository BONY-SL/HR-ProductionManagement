package com.example.luckySystem.controller.bottles;
import com.example.luckySystem.dto.bottles.DailyFinishedDTO;
import com.example.luckySystem.dto.bottles.DamageBottleDTO;
import com.example.luckySystem.dto.bottles.EmptyBottleDTO;
import com.example.luckySystem.entity.DailyDamageBottleByEmployee;
import com.example.luckySystem.entity.DailyEmptyBottleUnit;
import com.example.luckySystem.entity.DailyFinished;
import com.example.luckySystem.service.bottles.DailyEmptyBottleUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class BottleController {

    @Autowired
    private DailyEmptyBottleUnitService service;

    @PostMapping("/adddailyemptybottles")
    public ResponseEntity<DailyEmptyBottleUnit> addDailyEmptyBottleUnit(@RequestBody EmptyBottleDTO dto) {
        DailyEmptyBottleUnit savedEntity = service.saveDailyEmptyBottleUnit(dto);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getEmptyBottle")
    public ResponseEntity<List<EmptyBottleDTO>> getAllEmptyBottles() {
        List<EmptyBottleDTO> bottles = service.getAllEmptyBottles();
        return ResponseEntity.ok().body(bottles);
    }

    @PutMapping("/updateEmptyBottle")
    public ResponseEntity<?> updateEmptyBottle(@RequestBody EmptyBottleDTO dto) {
        service.updateEmptyBottle(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addDailyDamages")
    public ResponseEntity<DailyDamageBottleByEmployee> addDailyDamagesByEmployee(@RequestBody DamageBottleDTO dto) {
        DailyDamageBottleByEmployee savedEntity = service.saveDailyDamagesByEmployee(dto);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getemployeeDamageBottle")
    public ResponseEntity<List<DamageBottleDTO>> getAllDamageBottles() {
        List<DamageBottleDTO> bottles = service.getAllDamageBottles();
        return ResponseEntity.ok().body(bottles);
    }

    @PutMapping("/updatedamageBottle")
    public ResponseEntity<?> updatedamageBottle(@RequestBody DamageBottleDTO dto) {
        service.updateDamageBottle(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/adddailyfinishedmilk")
    public ResponseEntity<DailyFinished> addDailyfinishedBottleUnit(@RequestBody DailyFinishedDTO dto) {
        DailyFinished savedEntity = service.saveDailyFinishedMilk(dto);
        return ResponseEntity.ok(savedEntity);
    }
}
