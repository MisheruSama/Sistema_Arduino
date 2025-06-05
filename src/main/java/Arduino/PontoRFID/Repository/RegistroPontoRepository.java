package Arduino.Ponto_RFID.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Arduino.Ponto_RFID.Model.RegistroPonto;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long>{

    List<RegistroPonto> findByFuncionarioNome(String nome);

}
