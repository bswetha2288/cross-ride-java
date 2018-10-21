/**
 * 
 */
package com.crossover.techtrial.repositories;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {

	// user defined method	
	@Query("SELECT " +
	           "    new com.crossover.techtrial.dto.TopDriverDTO(driver.name, driver.email,"
	           + "  sum(ride.endTime - ride.startTime)/100 as totalRideDurationInSeconds,"	           
	           + "  max (ride.endTime - ride.startTime)/100 as maxRideDurationInSecods, "
	           + "  avg(ride.distance) as averageDistance)"	           	            
			   + "  from Ride ride, Person driver where driver.id=ride.driver " 	           
			   + "  and ride.startTime >=?1 and ride.endTime <?2"
			   + "  group by driver"
			   + "  order by maxRideDurationInSecods desc")	 

	public List<TopDriverDTO> getTopDriversWithInTimeFrame(LocalDateTime startTime,LocalDateTime endTime,Pageable  pageRequest);
	


}
