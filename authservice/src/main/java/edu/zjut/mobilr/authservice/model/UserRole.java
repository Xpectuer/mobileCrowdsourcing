package edu.zjut.mobilr.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="t_r_u")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserRolePK.class)
public class UserRole implements Serializable {
    @Id
    private Long r_id;

    @Id
    private Long u_id;

    private String comment;

}
