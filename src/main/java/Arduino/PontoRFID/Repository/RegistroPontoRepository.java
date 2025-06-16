package Arduino.PontoRFID.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Arduino.PontoRFID.Model.Funcionario;
import Arduino.PontoRFID.Model.RegistroPonto;
// classe para acessar o banco de dados e buscar os registros de ponto
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long>{
    public RegistroPonto findTopByFuncionarioOrderByDataderegistroDescHorarioDesc(Funcionario funcionario);
}
