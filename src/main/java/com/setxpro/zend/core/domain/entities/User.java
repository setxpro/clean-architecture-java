package com.setxpro.zend.core.domain.entities;

import com.setxpro.zend.core.domain.dtos.userDto.UserDTO;
import com.setxpro.zend.core.domain.enums.GenderEnum;
import com.setxpro.zend.core.domain.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name="TAB_USERS")
@Table(name="TAB_USERS")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements UserDetails {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Nome é Obrigatório")
    private String name;

    @Email
    @NotBlank(message = "E-mail é obrigatório.")
    private String email;

    @Size(min = 8, message = "Senha deve conter no mínimo 8 caracteres")
    private String password;

    private String login;

    @NotBlank(message = "Telefone é obrigatório")
    private String phoneNumber;

    private GenderEnum gender;

    private RoleEnum role;
    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.login = userDTO.login();
        this.phoneNumber = userDTO.phoneNumber();
        this.password = userDTO.password();
        this.gender = userDTO.gender();
        this.role = userDTO.role();
    }

    public User(String name, String email, String login, String encryptedPassword, String phoneNumber, GenderEnum gender, RoleEnum role) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = encryptedPassword;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == RoleEnum.ADMIN)  return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
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
