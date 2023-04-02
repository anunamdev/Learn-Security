package com.example.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL_ID", nullable = false)
    private String email;
    @Column(name = "ABOUT_USER", nullable = false)
    private String about;

    @ManyToMany
    @JoinTable(name = "USER_ROLE",
            joinColumns=@JoinColumn(name="user",referencedColumnName = "roleId"),
            inverseJoinColumns =@JoinColumn(name = "role",referencedColumnName = "id") )
    Set<Role> role=new HashSet<>();


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> collect = role.stream().map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toList());
//        return collect;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
