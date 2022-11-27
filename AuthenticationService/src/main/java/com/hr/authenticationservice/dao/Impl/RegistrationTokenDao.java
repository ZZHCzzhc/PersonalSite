package com.hr.authenticationservice.dao.Impl;


import com.hr.authenticationservice.dao.AbstractHibernateDAO;
import com.hr.authenticationservice.domain.RegistrationToken;
import com.hr.authenticationservice.domain.User;
import com.hr.authenticationservice.exception.InvalidTokenException;
import com.hr.authenticationservice.service.EncryptDesService;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class RegistrationTokenDao extends AbstractHibernateDAO<RegistrationToken> {
    public RegistrationTokenDao() {
        setClazz(RegistrationToken.class);
    }

    public RegistrationToken CreateRegistrationToken(int hrId, String Email) {
        Session session = getCurrentSession();
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusHours(3);
        EncryptDesService des1 = null;
        String token = "Hr#" + hrId + "invitation";
        try {
            des1 = new EncryptDesService(String.valueOf(LocalDateTime.now()));
            token = des1.encrypt(Email + hrId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User hr = session.get(User.class, hrId);
        RegistrationToken registrationToken = RegistrationToken.builder()
                .Email(Email)
                .ExpirationDate(localDateTime)
                .Token(token)
                .user(hr)
                .build();

        session.save(registrationToken);

        return registrationToken;
    }

    public RegistrationToken getRegistrationTokenByToken(String token) throws Exception {
        Session session = getCurrentSession();
        LocalDateTime localDateTime = LocalDateTime.now();

        TypedQuery<RegistrationToken> query = session.createQuery("from RegistrationToken r where r.Token =:token and r.ExpirationDate >:now");
        Optional<RegistrationToken> registrationToken = null;

        try {
              List<RegistrationToken> registrationTokenList = query.setParameter("token", token).setParameter("now", localDateTime).getResultList();
            registrationToken = Optional.ofNullable(registrationTokenList.get(0));

        } catch (Exception e) {
            throw  new InvalidTokenException();
        }

        return registrationToken.get();


//        TypedQuery<RegistrationToken> query = session.createQuery("from RegistrationToken r where r.Token =:token and r.ExpirationDate >:now");
//        Optional<RegistrationToken> registrationTokens = Optional.ofNullable(query.setParameter("token", token).setParameter("now", date).getSingleResult());
//
//        System.out.println("-------now: " + date);
//        System.out.println("-------token: " + token);
//            System.out.println(registrationTokens.get().getToken().equals(token) + "    +++++++    " + registrationTokens.get().getID() + "     +++++++     " + registrationTokens.get().getExpirationDate() );
//        return null;
    }
}
