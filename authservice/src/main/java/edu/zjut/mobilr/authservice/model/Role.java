package edu.zjut.mobilr.authservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name ="t_role")
@Entity
@ToString
public class Role extends BaseEntity implements Serializable {



    private String name;

    @Column(name="namezh")
    private String nameZh;






}
