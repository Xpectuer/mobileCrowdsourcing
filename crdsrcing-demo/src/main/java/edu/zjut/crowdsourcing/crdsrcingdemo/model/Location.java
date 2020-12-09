package edu.zjut.crowdsourcing.crdsrcingdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


/**
 *  {
 *   locations: [
 * {
 * },
 * ] }
 * location_longtitude: xxx, location_latitude: xxx, direction: xxx,
 * velocity: xxx
 * timestamp: xxxx-xx-xx
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private BigDecimal location_longtitude;
    private BigDecimal location_latitude;
    private Double direction;
    private List<Double> velocity;
    private String timestamp;

}
