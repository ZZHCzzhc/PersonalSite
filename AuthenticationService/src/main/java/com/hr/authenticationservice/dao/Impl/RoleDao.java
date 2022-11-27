package com.hr.authenticationservice.dao.Impl;

import com.hr.authenticationservice.dao.AbstractHibernateDAO;
import com.hr.authenticationservice.domain.Role;
import com.hr.authenticationservice.domain.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDao extends AbstractHibernateDAO<Role> {
    public RoleDao() {setClazz(Role.class);}

    public List<String> getAllUserRoles(List<UserRole> userRoles) {
        Session session = getCurrentSession();
        List<String> userRoleList = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            if (userRole.getActiveFlag()) {
                userRoleList.add(session.get(Role.class, userRole.getRole().getID()).getRoleName());
            }
        }

        return userRoleList.size() == 0 ? null : userRoleList;
    }

}
