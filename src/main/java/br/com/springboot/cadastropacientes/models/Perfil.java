package br.com.springboot.cadastropacientes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author : Gloria Rayane
 * @since : 19/09/2021
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfil")
public class Perfil implements GrantedAuthority {

    @Id
    @Column(name = "perfil_name", nullable = false)
    private String perfilName;

    @Override
    public String getAuthority() {
        return this.perfilName;
    }
}
