package com.crewrisk.model;

import com.crewrisk.model.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users implements UserDetails, Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;
    private String password;

    private boolean certificationed = false;
    private boolean questioned = false;
    private String resume = "";

    @OneToOne(cascade = CascadeType.ALL)
    private Primarys primarys = new Primarys();
    @OneToOne(cascade = CascadeType.ALL)
    private Secondary secondary = new Secondary();
    @OneToOne(cascade = CascadeType.ALL)
    private Tertiary tertiary = new Tertiary();
    @OneToOne(cascade = CascadeType.ALL)
    private Score score = new Score(this);

    @OneToOne(cascade = CascadeType.ALL)
    private Question question;
    @OneToOne(cascade = CascadeType.ALL)
    private Certification certification;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vacancy> vacancies = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Offers> offers = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Apps> apps = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<AppAnswer> appAnswers = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<HumanComments> commentsOwner = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HumanComments> commentsUser = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Reviews> reviews = new ArrayList<>();

    public Users(String username, String password, Role role) {
        this.role = role;
        this.username = username;
        this.password = passwordEncoder().encode(password);
    }

    public int getExperience() {
        return secondary.getExperience();
    }

    public boolean checkJob(Long jobId) {
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getJob().getId().equals(jobId)) {
                return false;
            }
        }
        return true;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
