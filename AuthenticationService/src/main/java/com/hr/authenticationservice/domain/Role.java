package com.hr.authenticationservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String RoleName;
    private String RoleDescription;
    private LocalDateTime CreateDate;
    private LocalDateTime LastModificationDate;

    @Override
    public String toString() {
        return "Role{" +
                "ID=" + ID +
                ", RoleName='" + RoleName + '\'' +
                ", RoleDescription='" + RoleDescription + '\'' +
                ", CreateDate=" + CreateDate +
                ", LastModificationDate=" + LastModificationDate +
                '}';
    }
}
