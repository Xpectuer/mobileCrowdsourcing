package edu.zjut.mobilr.authservice.pages;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserQo extends PageQo{
    private Long id;
    private String name;
    private String email;
    private String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date update_time;


}
