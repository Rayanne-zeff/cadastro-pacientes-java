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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;


   @Column(name = "pessoa_name")
   @NotBlank(message = "O nome é obrigatório!")
    private String pessoaName;

    @Column(name = "pessoa_cpf",unique = true, length = 20, nullable = false)
    @CPF(message = "Informe um CPF válido")
    @NotBlank(message = "O CPF é obrigatório!")
    private String pessoaCpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "pessoa_tipo", nullable = false, length = 10)
    @NotBlank(message = "O tipo da pessoa é obrigatório!")
    private PessoaTipo pessoaTipoEnum;

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
}
