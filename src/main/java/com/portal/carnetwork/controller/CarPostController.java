package com.portal.carnetwork.controller;

import com.portal.carnetwork.dto.CarPostDTO;
import com.portal.carnetwork.message.KafkaProducerMessage;
import com.portal.carnetwork.service.CarPostStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarPostController {

    @Autowired
    private CarPostStoreService carPostStoreService;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @GetMapping("/posts")
    public ResponseEntity<List<CarPostDTO>> getCarSales(){
        return ResponseEntity.status(HttpStatus.FOUND).body(carPostStoreService.getCarForSales());
    }

    @PutMapping("/{id}")
    public ResponseEntity changeCarSales(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id) {
        carPostStoreService.changeCarForSale(carPostDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarForSales(@PathVariable("id") String id) {
        carPostStoreService.removeCarForSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity postCarForSales(@RequestBody CarPostDTO carPostDTO) {
        kafkaProducerMessage.sendMessage(carPostDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
