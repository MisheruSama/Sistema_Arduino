package Arduino.PontoRFID.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Arduino.PontoRFID.Model.Funcionario;
import Arduino.PontoRFID.Model.RegistroPonto;
import Arduino.PontoRFID.Repository.FuncionarioRepository;
import Arduino.PontoRFID.Repository.RegistroPontoRepository;

@RestController
@RequestMapping("/api/historico")
public class RegistroController {

    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<RegistroPonto> listarRegistro() {
        return registroPontoRepository.findAll();
    }

    @PostMapping("/registrar")
public ResponseEntity<String> registrarPonto(@RequestBody RegistroPonto registroPonto) {
    String codigoRfid = registroPonto.getFuncionario().getRfiduid().toUpperCase();

    Optional<Funcionario> funcionario = funcionarioRepository.findById(codigoRfid);

    if (funcionario.isEmpty()) {
        return ResponseEntity.status(400).body("Funcionário não cadastrado.");
    }

    registroPonto.setFuncionario(funcionario.get());

    RegistroPonto ultimoRegistro = registroPontoRepository
        .findTopByFuncionarioOrderByDataderegistroDescHorarioDesc(funcionario.get());

    String statusCalculado = (ultimoRegistro == null || "saida".equalsIgnoreCase(ultimoRegistro.getStatus()))
        ? "entrada"
        : "saida";

    registroPonto.setStatus(statusCalculado);

    registroPontoRepository.save(registroPonto);

    return ResponseEntity.ok("Ponto registrado como: " + statusCalculado);
}

}
