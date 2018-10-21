/**
 * 
 */
package com.crossover.techtrial.dto;

/**
 * @author crossover
 *
 */

public class TopDriverDTO {
  
  /**
   * Constructor for TopDriverDTO
   * @param name
   * @param email
   * @param totalRideDurationInSeconds
   * @param maxRideDurationInSecods
   * @param averageDistance
   */
  public TopDriverDTO(String name, 
      String email, 
      Integer totalRideDurationInSeconds,
      Integer maxRideDurationInSecods,
      Double averageDistance) {
    this.setName(name);
    this.setEmail(email);
    this.setAverageDistance(averageDistance);
    this.setMaxRideDurationInSecods(maxRideDurationInSecods);
    this.setTotalRideDurationInSeconds(totalRideDurationInSeconds);
    
  }
  
  public TopDriverDTO() {
    
  }
  
  private String name;
  
  private String email;
  
  private Integer totalRideDurationInSeconds;
  
  private Integer maxRideDurationInSecods;
  
  private Double averageDistance;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getTotalRideDurationInSeconds() {
    return totalRideDurationInSeconds;
  }

  public void setTotalRideDurationInSeconds(Integer totalRideDurationInSeconds) {
    this.totalRideDurationInSeconds = totalRideDurationInSeconds;
  }

  public Integer getMaxRideDurationInSecods() {
    return maxRideDurationInSecods;
  }

  public void setMaxRideDurationInSecods(Integer maxRideDurationInSecods) {
    this.maxRideDurationInSecods = maxRideDurationInSecods;
  }

  public Double getAverageDistance() {
    return averageDistance;
  }

  public void setAverageDistance(Double averageDistance) {
    this.averageDistance = averageDistance;
  }
  
  
    
}
