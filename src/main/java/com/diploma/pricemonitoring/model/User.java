package com.diploma.pricemonitoring.model;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.swing.table.TableModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String email;
    private String name;
    private String password;
    private Role role;
    private Timestamp dateCreated;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_liked_laptops",
            joinColumns = @JoinColumn(name = "user_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "planshet_model_id", nullable = true))
    private List<PlanshetModel> planshetModels;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_liked_smartphones",
            joinColumns = @JoinColumn(name = "user_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "smartphone_model_id", nullable = true))
    private List<SmartphoneModel> smartphoneModels;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_liked_notebooks",
            joinColumns = @JoinColumn(name = "user_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "notebook_model_id", nullable = true))
    private List<NotebookModel> notebookModels;

    public User(Long id, String email, String name, String password, Role role, Timestamp dateCreated) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.dateCreated = dateCreated;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public User setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.name().toUpperCase()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return email;
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
}
