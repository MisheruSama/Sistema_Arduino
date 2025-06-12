package Arduino.PontoRFID.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private FuncionarioRepository FuncionarioRepository;
    @Autowired
    private RFIDMode rfidMode;

    @PostMapping("/modoCadastro")
    public ResponseEntity<String> ativarCadastro(){
        rfidMode.setCadastro(true);
        return ResponseEntity.ok("Modo de cadastro ativado.");
    }
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

    @PostMapping("/cadastrar")
    public String cadastrarFuncionario(@RequestBody Funcionario funcionario){
        FuncionarioRepository.save(funcionario);
          rfidMode.setUltimoRfidUid("");
        return "redirect:/funcionarios";
    }
    @DeleteMapping("/deletar/{rfiduid}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable String rfiduid) {
        FuncionarioRepository.deleteById(rfiduid);
        return ResponseEntity.ok().build();
    }
    
}
