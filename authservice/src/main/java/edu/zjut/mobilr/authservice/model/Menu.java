package edu.zjut.mobilr.authservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false)
    private String url;

    private String path;

    private String name;


    private Meta meta;

    private Integer parentId;

    private Boolean enabled;
    @OneToMany
    private List<Menu> children;
    @ManyToMany
    @JoinTable(name="T_R_U",joinColumns = {
            @JoinColumn(name = "u_id")
    },inverseJoinColumns = {@JoinColumn(name = "r_id")})
    private List<Role> roles;


}