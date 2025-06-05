package Arduino.PontoRFID.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Arduino.PontoRFID.Model.RegistroPonto;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long>{

    List<RegistroPonto> findByFuncionarioNome(String nome);

}
