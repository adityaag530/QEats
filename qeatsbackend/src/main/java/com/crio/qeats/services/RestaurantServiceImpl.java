
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
     log.info("inside restaurant service implementation class and inside method getrestaurantresonse");
    //  - For peak hours: 8AM - 10AM, 1PM-2PM, 7PM-9PM
    //  * - service radius is 3KMs.
    //  * - All other times, serving radius is 5KMs.
    GetRestaurantsResponse getRestaurantsResponse;
    if(isPeakHour(currentTime)){
      getRestaurantsResponse =  new GetRestaurantsResponse(restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), 
      getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms));
    }else{
      getRestaurantsResponse =  new GetRestaurantsResponse(restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), 
      getRestaurantsRequest.getLongitude(), currentTime, normalHoursServingRadiusInKms));
    }
    return getRestaurantsResponse;
  }

  public boolean isPeakHour(LocalTime currentTime){
    int time = currentTime.getHour();
    if( (time<=10 && time>=8) || (time<=14 && time>=13) || (time<=21 && time>=19) ){
      return true;
    }
    return false;
  }
}

