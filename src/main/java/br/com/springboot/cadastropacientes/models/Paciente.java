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
@Table(name = "paciente")
public class Paciente {

    @Id
    private Long pessoaId;
    @MapsId("pessoa_id")
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id")
    private Pessoa pessoa;


    @Column(name = "paciente_data_nascimento", columnDefinition = "date")
    private Date pacienteDataNascimento;


    @Column(name = "paciente_uf", nullable = false)
    @NotBlank(message = "O estado é obrigatório!")
    private String  pacienteUF;


    @Column(name = "paciente_peso")
    private Double pacientePeso;

    @Column(name = "paciente_altura")
    private Double pacienteAltura;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paciente_data_criacao", nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @CreatedDate
    private Date pacienteDataCriacao;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paciente_data_alteracao")
    @ColumnDefault("current_timestamp")
    @LastModifiedDate
    private Date pacienteDataAlteracao;


}
