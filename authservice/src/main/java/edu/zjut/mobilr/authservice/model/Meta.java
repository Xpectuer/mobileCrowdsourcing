package edu.zjut.mobilr.authservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Meta implements Serializable {
    private Boolean keepAlive;

    private Boolean requireAuth;

    }