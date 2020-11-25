package edu.zjut.mobilr.authservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRolePK implements Serializable {

    private Long r_id;
    private Long u_id;
}
