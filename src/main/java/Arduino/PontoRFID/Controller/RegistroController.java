package Arduino.PontoRFID.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Arduino.PontoRFID.Model.RegistroPonto;
import Arduino.PontoRFID.Repository.RegistroPontoRepository;

@RestController
@RequestMapping("/api/historico")
public class RegistroController {

    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @GetMapping
    public List<RegistroPonto> listarRegistro(){
        List<RegistroPonto> registros = registroPontoRepository.findAll();
        return registros;
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarPonto(RegistroPonto registroPonto){
        if(registroPontoRepository.existsByFuncionario(registroPonto.getFuncionario())){
            registroPontoRepository.save(registroPonto);
            return ResponseEntity.ok("Ponto registrado com sucesso.");
        } else{
            return ResponseEntity.status(400).body("Funcionário não Cadastrado.");
        }
    }

}
