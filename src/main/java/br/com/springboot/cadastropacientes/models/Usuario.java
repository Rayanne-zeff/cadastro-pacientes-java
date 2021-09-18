package br.com.springboot.cadastropacientes.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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



}
