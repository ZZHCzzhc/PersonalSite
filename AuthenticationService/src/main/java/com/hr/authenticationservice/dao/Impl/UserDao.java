package com.hr.authenticationservice.dao.Impl;


import com.hr.authenticationservice.dao.AbstractHibernateDAO;
import com.hr.authenticationservice.domain.Role;
import com.hr.authenticationservice.domain.User;
import com.hr.authenticationservice.domain.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class UserDao extends AbstractHibernateDAO<User> {
    public UserDao() {
        setClazz(User.class);
    }

    public Optional<User> loadUserByUsername(String username) {
        Session session = getCurrentSession();

        TypedQuery<User> query = session.createQuery((username.contains("@") || username.contains(".com")) ? "from User u where u.Email =:username" : "from User u where u.Username =:username");
        Optional<User> user = Optional.ofNullable(query.setParameter("username", username).getSingleResult());

        return user;
    }

//    @Transactional
    public void create(String username, String password, String email) throws Exception {
        Session session = getCurrentSession();
        TypedQuery<User> query = session.createQuery("from User u where u.Username =:username or u.Email =:email");

        Optional<User> user = Optional.ofNullable(null);
        try {
            user = Optional.ofNullable(query.setParameter("username", username).setParameter("email", email).getSingleResult());
        } catch ( Exception e) {}

        if (user.isPresent())
            throw new Exception("user/email name already exist");

        LocalDateTime localDateTime = LocalDateTime.now();
        User u = User.builder()
                .Username(username)
                .Password(password)
                .Email(email)
                .CreateDate(localDateTime)
                .LastModificationDate(localDateTime)
                .ActiveFlag(true)
                .build();

        session.save(u);

        UserRole userRole = UserRole.builder()
                .ActiveFlag(true)
                .user(u)
                .CreateDate(localDateTime)
                .LastModificationDate(localDateTime)
                .role(session.get(Role.class, 2))
                .build();

        session.save(userRole);

    }
}
