package br.com.springboot.cadastropacientes.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.br.CPF;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;


    @Column(name = "pessoa_name", nullable = false)
    @NotBlank(message = "O nome é obrigatório!")
    private String pessoaName;

    @Column(name = "pessoa_cpf", unique = true, length = 255, nullable = false)
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


//    public String getPessoaCpf() {
//        try {
//            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//            encryptor.setPassword("9592f001-7c7a-4182-8aa5-04301cc41f9b");
////            encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
//            return encryptor.decrypt(this.pessoaCpf);
//        } catch (RuntimeException exception) {
//            return this.pessoaCpf;
//        }
//    }

}
