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
    private FuncionarioRepository FuncionarioRepository;

    @GetMapping
    public List<RegistroPonto> listarRegistro(){
        List<RegistroPonto> registros = registroPontoRepository.findAll();
        return registros;
    }
   @PostMapping("/registrar")
public ResponseEntity<String> registrarPonto(@RequestBody RegistroPonto registroPonto) {
    String codigoRfid = registroPonto.getFuncionario().getRfiduid();
   Optional<Funcionario> funcionario = FuncionarioRepository.findById(codigoRfid);
    

    if (funcionario.isPresent()) {
        registroPonto.setFuncionario(funcionario.get());
         // Busca o último registro desse funcionário
        RegistroPonto ultimoRegistro = registroPontoRepository.findTopByFuncionarioOrderByDataderegistroDescHorarioDesc(funcionario.get());

        // Define o status com base no último registro
        String statusCalculado = (ultimoRegistro == null || "saida".equals(ultimoRegistro.getStatus()))
            ? "entrada"
            : "saida";

        registroPonto.setStatus(statusCalculado);

        registroPontoRepository.save(registroPonto);

        return ResponseEntity.ok("Ponto registrado como: " + statusCalculado);
    } else {
        return ResponseEntity.status(400).body("Funcionário não cadastrado.");
    }

}

}
