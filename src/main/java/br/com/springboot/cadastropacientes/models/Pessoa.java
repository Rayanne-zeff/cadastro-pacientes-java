package br.com.springboot.cadastropacientes.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
@Embeddable
@Table(name = "pessoa")
public class Pessoa implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;


    @Column(name = "pessoa_name", nullable = false)
    @NotBlank(message = "O nome é obrigatório!")
    private String pessoaName;

    @Column(name = "pessoa_cpf",unique = true, length = 20, nullable = false)
    @CPF(message = "Informe um CPF válido")
    @NotBlank(message = "O CPF é obrigatório!")
    private String pessoaCpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "pessoa_tipo", nullable = false, length = 10)
    private PessoaTipo pessoaTipo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pessoa_data_criacao", nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @CreatedDate
    private Date pessoaDataCriacao;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pessoa_data_alteracao")
    @ColumnDefault("current_timestamp")
    @LastModifiedDate
    private Date pessoaDataAlteracao;

    public void setPessoaCpf(String pessoaCpf) {
        this.pessoaCpf = pessoaCpf.replaceAll("[^\\d ]", "");
    }
}
