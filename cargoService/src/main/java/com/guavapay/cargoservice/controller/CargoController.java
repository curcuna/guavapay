package com.guavapay.cargoservice.controller;

import com.guavapay.cargoservice.model.entity.Cargo;
import com.guavapay.cargoservice.service.CargoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/cargo")
@AllArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping()
    public ResponseEntity assignCourier(@RequestBody Cargo cargo) {
        cargoService.assignCourier(cargo);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/person/{personId}")
    public ResponseEntity getOrders(@PathVariable long personId) {
        List<Cargo> cargoList = cargoService.getCargo(personId);
        return ResponseEntity.ok().body(cargoList);
    }

    @PutMapping("/status")
    public ResponseEntity changeCargoStatus(@RequestBody Cargo cargo) {
        cargoService.changeCargoStatus(cargo);
        return ResponseEntity.ok().body(cargo);
    }

    @DeleteMapping()
    public ResponseEntity changeCargoLocation(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok().build();
    }
}