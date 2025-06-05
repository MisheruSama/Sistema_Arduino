package Arduino.Ponto_RFID.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Arduino.Ponto_RFID.Model.RegistroPonto;
import Arduino.Ponto_RFID.Repository.RegistroPontoRepository;

@RestController
@RequestMapping("/historico")
public class RegistroController {

    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @GetMapping("/historico/api")
    public List<RegistroPonto> listarRegistro(){
        List<RegistroPonto> registros = registroPontoRepository.findAll();
        return registros;
    }

    @GetMapping("/historico/encontrar/{uid}")
    public List<RegistroPonto> encontrarRegistroPorNome(String nome) {
        List<RegistroPonto> registrosPorNome = registroPontoRepository.findByFuncionarioNome(nome);
        return registrosPorNome;
    }
}
