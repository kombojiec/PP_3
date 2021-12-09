package com.example.pp_3.dao;

import com.example.pp_3.entity.AuthenticationProvider;
import com.example.pp_3.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


    @Override
    public Set<User> getUsers() {
        List<User> list = em.createQuery("select u from User u JOIN FETCH u.roles", User.class).getResultList();
        return list.stream().collect(Collectors.toSet());
    }

    @Override
    public User getUserById(int id) {
        return (User) em.createQuery("SELECT u FROM User u JOIN FETCH u.roles where u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User deleteUser(int id) {
        User user = em.find(User.class, id);
        user.setRoles(null);
        em.remove(user);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query =  em.createQuery("select u from User u JOIN FETCH u.roles where  u.firstName = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query =  em.createQuery("select u from User u JOIN FETCH u.roles where  u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        if(user.getId() == 0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else if(user.getId() != 0 && !getUserById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return em.merge(user);
    }

    public void processOAuthPostLogin(String email) {
        System.out.println("==========================  processOAuthPostLogin  =================================");
        User user = getUserByEmail(email);
        if(user == null) {
            user = new User();
            user.setEmail(email);
            user.setAuthProvider(AuthenticationProvider.GOOGLE);

            saveUser(user);
        }
    }

}
