package br.com.springboot.cadastropacientes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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
@Table(name = "enfermeiro")

public class Enfermeiro {

    @Id
    private Long pessoaId;
    @MapsId("pessoa_id")
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id")
    private Pessoa pessoa;

    @Column(name = "enfermeiro_cre")
    private String enfermeiroCre;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enfermeiro_criacao", nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @CreatedDate
    private Date enfermeiroDataCriacao;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enfermeiro_data_alteracao")
    @ColumnDefault("current_timestamp")
    @LastModifiedDate
    private Date enfermeiroDataAlteracao;
}
