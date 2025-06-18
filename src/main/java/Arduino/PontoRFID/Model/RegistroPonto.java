package Arduino.PontoRFID.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registroponto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegistroPonto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataderegistro;
    private LocalTime horario;
    private String status;
    
    @ManyToOne(fetch = FetchType.EAGER)   
     @JoinColumn(name = "fk_funcionario_rfiduid")
    @JsonIgnoreProperties("registroPontos")
    private Funcionario funcionario;
    
    
}
