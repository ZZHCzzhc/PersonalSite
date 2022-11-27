package com.hr.authenticationservice.dao.Impl;

import com.hr.authenticationservice.dao.AbstractHibernateDAO;
import com.hr.authenticationservice.domain.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRoleDao extends AbstractHibernateDAO<UserRole> {
    public UserRoleDao() {setClazz(UserRole.class);}

    public List<UserRole> getAllUserRoleS(Integer userID) {
        Session session = getCurrentSession();

        TypedQuery<UserRole> query = session.createQuery("from UserRole ur where ur.user.id =:userID");
        List<UserRole> userRoles = query.setParameter("userID", userID).getResultList();

        return userRoles;
    }

}
