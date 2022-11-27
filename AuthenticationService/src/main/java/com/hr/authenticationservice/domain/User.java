package com.hr.authenticationservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String Username;
    private String Password;
    private String Email;
    private LocalDateTime CreateDate;
    private LocalDateTime LastModificationDate;
    private Boolean ActiveFlag;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<RegistrationToken> registrationTokens;

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", CreateDate=" + CreateDate +
                ", LastModificationDate=" + LastModificationDate +
                ", ActiveFlag=" + ActiveFlag +
                '}';
    }
}