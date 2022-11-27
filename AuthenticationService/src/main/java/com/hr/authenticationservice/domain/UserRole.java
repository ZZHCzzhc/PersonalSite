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
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Boolean ActiveFlag;
    private LocalDateTime CreateDate;
    private LocalDateTime LastModificationDate;

    @ManyToOne
    @JoinColumn(name = "UserID")
    User user;

    @ManyToOne
    @JoinColumn(name = "RoleID")
    Role role;

    @Override
    public String toString() {
        return "UserRole{" +
                "ID=" + ID +
                ", ActiveFlag=" + ActiveFlag +
                ", CreateDate=" + CreateDate +
                ", LastModificationDate=" + LastModificationDate +
                '}';
    }
}
