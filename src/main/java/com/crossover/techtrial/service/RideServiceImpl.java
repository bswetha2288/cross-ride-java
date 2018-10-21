/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService{

  @Autowired
  RideRepository rideRepository;
  
  public Ride save(Ride ride) {
    return rideRepository.save(ride);
  }
  
  public Ride findById(Long rideId) {
    Optional<Ride> optionalRide = rideRepository.findById(rideId);
    if (optionalRide.isPresent()) {
      return optionalRide.get();
    }else return null;
  }

  @Override
  public List<TopDriverDTO> getTopDrivers(Long maxCount, LocalDateTime startTime, LocalDateTime endTime) {
	
     
	//List<TopDriverDTO>  topDriversList = topRepository.getTopDriversWithInTimeFrame(sTime, eTime);
	List<TopDriverDTO>  topDriversList = rideRepository.getTopDriversWithInTimeFrame(startTime, endTime,new PageRequest(0,maxCount.intValue()));
	
	System.out.println(topDriversList);
	
	return topDriversList;
	
}


}
