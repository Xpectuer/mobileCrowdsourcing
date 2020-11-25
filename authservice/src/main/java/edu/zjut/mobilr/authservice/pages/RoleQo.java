package edu.zjut.mobilr.authservice.pages;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleQo extends PageQo {
    private Long id;
    private String name;
    private String nameZh;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_time;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date update_time;

}
