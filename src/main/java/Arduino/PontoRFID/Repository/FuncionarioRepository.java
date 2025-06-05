package Arduino.Ponto_RFID.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Arduino.Ponto_RFID.Model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String>{
}
