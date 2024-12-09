package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;
import net.comicorp.collector.constant.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static net.comicorp.collector.constant.Constants.ROLE_PREFIX;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "cover")
    private String cover;

    @Column(name = "about_you")
    private String aboutYou;

    @Column(name = "signup_date")
    private Date signUpDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Profile> profiles;

    @ManyToMany
    @JoinTable(
            name = "users_issues",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "issue_id")
    )
    private Set<Issue> issues;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional
                .ofNullable(this.getProfiles())
                .orElse(Collections.emptySet())
                .stream()
                .map(profile -> new SimpleGrantedAuthority(ROLE_PREFIX + profile.getProfileName()))
                .toList();
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
