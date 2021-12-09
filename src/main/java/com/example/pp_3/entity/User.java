package com.example.pp_3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "First name can't be blank")
    @Size(min = 2, max = 15, message = "First name must be between 2 and 10 symbols")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    @Size(min = 2, max = 15, message = "Last name must be between 2 and 10 symbols")
    @Column(name = "last_name")
    private String lastName;

    @Range(min = 0, max = 120, message = "Age must be between 0 and 120 ")
    @Column(name = "age")
    private byte age;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 4, max = 255, message = "Password must be between 4 and 255 symbols")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email must be correct")
    @Column(name = "email")
    private String email;

    @Enumerated
    @Column(name = "oauth_provider")
    private AuthenticationProvider authProvider;

    @ManyToMany
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



    public User(){
        roles = new HashSet<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
