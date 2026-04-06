package com.portal.carnetwork.client;

import com.portal.carnetwork.dto.CarPostDTO;
import com.portal.carnetwork.dto.OwnerPostDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CarPostStoreClient {

    private final String USER_STORE_SERIVCE_URI = "http://localhost:8080/user";
    private final String POSTS_STORE_SERIVCE_URI = "http://localhost:8080/sales";

    private final RestClient restClient;

    public CarPostStoreClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<CarPostDTO> carForSaleClient(){
        CarPostDTO[] response = restClient.get()
                .uri(POSTS_STORE_SERIVCE_URI + "/cars")
                .retrieve()
                .body(CarPostDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }

    public void ownerPostsClient(OwnerPostDTO newUser){
        restClient.post()
                .uri(USER_STORE_SERIVCE_URI)
                .body(newUser)
                .retrieve()
                .toBodilessEntity();
    }

    public void changeCarForSaleClient(CarPostDTO carPostDTO, String id){
        restClient.put()
                .uri(POSTS_STORE_SERIVCE_URI + "/cars/" + id)
                .body(carPostDTO)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteCarForSaleClient(String id){
        restClient.delete()
                .uri(POSTS_STORE_SERIVCE_URI + "/cars/" + id)
                .retrieve()
                .toBodilessEntity();
    }

}
