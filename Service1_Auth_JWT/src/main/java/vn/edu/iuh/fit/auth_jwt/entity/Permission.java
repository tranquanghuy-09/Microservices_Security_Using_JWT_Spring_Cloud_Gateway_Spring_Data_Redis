package vn.edu.iuh.fit.auth_jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "t_permission")
@Getter
@Setter
public class Permission extends BaseEntity {

    private String permissionName;

    private String permissionKey;

}