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
import Arduino.PontoRFID.Model.RFIDMode;
import Arduino.PontoRFID.Repository.FuncionarioRepository;




@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    
    @Autowired //Anotação para injetar a dependência do repositório de funcionários
    private FuncionarioRepository FuncionarioRepository;
    @Autowired
    private RFIDMode rfidMode;

   @PostMapping("/modoCadastro")
   //metodo para usar o cadastro
    public ResponseEntity<String> ativarCadastro(){
        rfidMode.setCadastro(true);
        return ResponseEntity.ok("Modo de cadastro ativado.");
    }
    //metodo para pegar todos os funcionarios do banco de dados
    @GetMapping
    public List<Funcionario> ListarFuncionarios(){
        List<Funcionario> funcionarios = FuncionarioRepository.findAll();
        return funcionarios;
    }
      @PostMapping("/registrarUid")
    public ResponseEntity<String> registrarUid(@RequestBody String uid) {
        if(rfidMode.isCadastro()) {
            rfidMode.setUltimoRfidUid(uid);
                rfidMode.setCadastro(false);   
            return ResponseEntity.ok("UID registrado.");
            
        } else {
            return ResponseEntity.status(400).body("Modo cadastro não ativado.");
        }
    }

    // Frontend chama para pegar o último UID lido
    @GetMapping("/ultimoUid")
    public ResponseEntity<String> getUltimoUid() {
            String uid = rfidMode.getUltimoRfidUid();
    if (uid == null || uid.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(uid);
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
