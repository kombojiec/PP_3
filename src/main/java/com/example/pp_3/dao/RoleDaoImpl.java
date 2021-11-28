package com.example.pp_3.dao;

import com.example.pp_3.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Role> getRoles() {
        List<Role> list =  em.createQuery("select role from Role role", Role.class).getResultList();
        return list.stream().collect(Collectors.toSet());
    }
}
