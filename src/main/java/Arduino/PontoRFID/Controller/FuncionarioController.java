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
import Arduino.PontoRFID.Repository.FuncionarioRepository;




@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    
    @Autowired //Anotação para injetar a dependência do repositório de funcionários
    private FuncionarioRepository FuncionarioRepository;
    
    //metodo para pegar todos os funcionarios do banco de dados
    @GetMapping
    public List<Funcionario> ListarFuncionarios(){
        List<Funcionario> funcionarios = FuncionarioRepository.findAll();
        return funcionarios;
    }

//Endpoint para cadastrar os funcionários
@PostMapping("/cadastrar")
public ResponseEntity<?> cadastrarFuncionario(@RequestBody Funcionario funcionario){
    try {
        funcionario.setStatus("Ativo");
        
        FuncionarioRepository.save(funcionario);
        return ResponseEntity.status(201).body(funcionario);
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro ao cadastrar: " + e.getMessage());
    }
}

  @PostMapping("/demitir")
public ResponseEntity<?> demitirFuncionario(@RequestBody Funcionario funcionario) {
    Optional<Funcionario> funcionarioOptional = FuncionarioRepository.findById(funcionario.getRfiduid());

    if (funcionarioOptional.isPresent()) {
        Funcionario existente = funcionarioOptional.get();
        existente.setStatus("Inativo");
        FuncionarioRepository.save(existente);
        return ResponseEntity.ok("Funcionário demitido com sucesso.");
    } else {
        return ResponseEntity.status(404).body("Funcionário não encontrado.");
    }
}

    
}
