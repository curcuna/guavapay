package com.guavapay.cargoservice.service;

import com.guavapay.cargoservice.model.entity.Cargo;
import com.guavapay.cargoservice.model.entity.CargoStatus;
import com.guavapay.cargoservice.repository.CargoRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;
    private final RabbitTemplate rabbitTemplate;

    public void assignCourier(Cargo cargo){
        Cargo _cargo = cargoRepository.findByOrderId(cargo.getOrderId());
        if(_cargo == null){
            _cargo = cargo;
            _cargo.setOrderId(cargo.getOrderId());
        }
        _cargo.setCourierId(cargo.getCourierId());
        _cargo.setCargoStatus(CargoStatus.ASSIGNED);
        cargoRepository.save(_cargo);

        rabbitTemplate.convertAndSend("assignCourier", cargo.getOrderId());
    }

    public List<Cargo> getCargo(long personId){
        List<Cargo> cargoList = cargoRepository.findAllByCourierId(personId);
        return cargoList;
    }

    public void changeCargoStatus(Cargo cargo){
        Cargo _cargo = cargoRepository.findById(cargo.getId()).get();
        if(_cargo != null){
            _cargo.setCargoStatus(cargo.getCargoStatus());
            cargoRepository.save(_cargo);
            if(CargoStatus.DELIVERED.equals(cargo.getCargoStatus())){
                rabbitTemplate.convertAndSend("changeOrderStatus", cargo.getOrderId());
            }
        }
    }
}
