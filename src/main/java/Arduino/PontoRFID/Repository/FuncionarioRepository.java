package Arduino.PontoRFID.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Arduino.PontoRFID.Model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String>{
}
