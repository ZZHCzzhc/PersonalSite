package com.hr.authenticationservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class RegistrationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String Token;
    private String Email;
    private LocalDateTime ExpirationDate;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "CreateBy")
    User user;

    @Override
    public String toString() {
        return "RegistrationToken{" +
                "ID=" + ID +
                ", Token='" + Token + '\'' +
                ", Email='" + Email + '\'' +
                ", ExpirationDate=" + ExpirationDate +
                ", CreateBy=" + user.getID() +
                '}';
    }
}
