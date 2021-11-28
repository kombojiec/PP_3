package com.example.pp_3.service;

import com.example.pp_3.dao.RoleDao;
import com.example.pp_3.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }
}
