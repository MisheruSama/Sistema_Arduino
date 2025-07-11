package Arduino.PontoRFID.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Representa uma Entidade no Banco de Dados
@Entity
@Table(name = "funcionario")
//Gera os getters e setters automaticamente
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    // Indica o identificador da entidade e a coluna correspondente no banco de dados
    @Id
    private String rfiduid;
    private String nome;
    private String cargo;
    private String status;

    @OneToMany(mappedBy = "funcionario")
    @JsonIgnoreProperties({"funcionario"})
    private List<RegistroPonto> registroPontos;
}
