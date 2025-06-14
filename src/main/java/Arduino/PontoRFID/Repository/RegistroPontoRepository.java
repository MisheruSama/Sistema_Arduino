package Arduino.PontoRFID.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Arduino.PontoRFID.Model.Funcionario;
import Arduino.PontoRFID.Model.RegistroPonto;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long>{
    boolean existsByFuncionario(Funcionario funcionario);
}
