package br.com.springboot.cadastropacientes.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "usuario_login", unique = true, length = 20, nullable = false)
    @NotBlank(message = "O login é obrigatório!")
    private String usuarioLogin;

    @Column(name = "usuario_senha", nullable = false)
    @NotBlank(message = "A senha é obrigatória!")
    private String usuarioSenha;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usuario_data_criacao", nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @CreatedDate
    private Date usuarioDataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usuario_data_alteracao")
    @ColumnDefault("current_timestamp")
    @LastModifiedDate
    private Date usuarioDataAlteracao;


    @NotEmpty(message = "Perfil do usuário não pode ser vazio!")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_perfil",
            joinColumns = {@JoinColumn(name = "usuario_perfil", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "perfil_name", nullable = false)})
    private Collection<Perfil> perfil;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//        list.add(new SimpleGrantedAuthority("ROLE_" + this.perfil));
//        return list;
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        this.perfil.forEach(
//                cadaPerfil -> authorities.add(
//                        new SimpleGrantedAuthority(cadaPerfil.getPerfilName())
//                )
//        );
//
//        return authorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.perfil.forEach(
            perfilItem -> authorities.add(
                new SimpleGrantedAuthority(perfilItem.getPerfilName())
            )
        );
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.usuarioSenha;
    }

    @Override
    public String getUsername() {
        return this.usuarioLogin;
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
