/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.controller;

import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.services.RestaurantService;
import java.time.LocalTime;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
// Implement Controller using Spring annotations.
// Remember, annotations have various "targets". They can be class level, method level or others.
@RestController
@Log4j2
public class RestaurantController {

  public static final String RESTAURANT_API_ENDPOINT = "/qeats/v1";
  public static final String RESTAURANTS_API = "/restaurants";
  public static final String MENU_API = "/menu";
  public static final String CART_API = "/cart";
  public static final String CART_ITEM_API = "/cart/item";
  public static final String CART_CLEAR_API = "/cart/clear";
  public static final String POST_ORDER_API = "/order";
  public static final String GET_ORDERS_API = "/orders";

  @Autowired
  private RestaurantService restaurantService;
  
  // curl http://localhost:8081/qeats/v1/restaurants?latitude=45.23423&longitude=73.45252
  @GetMapping(RESTAURANT_API_ENDPOINT + RESTAURANTS_API)
  public ResponseEntity<GetRestaurantsResponse> getRestaurants(
      @Valid GetRestaurantsRequest getRestaurantsRequest) {
    System.out.println("inside /qeats/v1");
    log.info("getRestaurants called with {}", getRestaurantsRequest);
    GetRestaurantsResponse getRestaurantsResponse;

    // CHECKSTYLE:OFF
    getRestaurantsResponse =
        restaurantService.findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
    log.info("getRestaurants returned {}", getRestaurantsResponse);
    // CHECKSTYLE:ON

    return ResponseEntity.ok().body(getRestaurantsResponse);
  }













}

