package com.example.luckySystem.controller.bottles;
import com.example.luckySystem.dto.bottles.EmptyBottleDTO;
import com.example.luckySystem.entity.DailyEmptyBottleUnit;
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
    public ResponseEntity<DailyEmptyBottleUnit> createDailyEmptyBottleUnit(@RequestBody EmptyBottleDTO dto) {
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

}
