package br.com.springboot.cadastropacientes.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "medico")
public class Medico {

    @Id
    private Long pessoaId;
    @MapsId("pessoa_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id")
    private Pessoa pessoa;


    @Column(name = "medico_crm")
    private String medicoCrm;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "medico_data_criacao", nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @CreatedDate
    private Date medicoDataCriacao;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "medico_data_alteracao")
    @ColumnDefault("current_timestamp")
    @LastModifiedDate
    private Date medicoDataAlteracao;
}
