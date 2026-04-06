package com.portal.carnetwork.service;

import com.portal.carnetwork.dto.CarPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarPostStoreService {

    List<CarPostDTO> getCarForSales();
    void changeCarForSale(CarPostDTO carPost, String id);
    void removeCarForSale(String id);

}
