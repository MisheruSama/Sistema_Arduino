package Arduino.PontoRFID.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Autowired
    private FuncionarioRepository FuncionarioRepository;

    @GetMapping
    public List<Funcionario> ListarFuncionarios(){
        List<Funcionario> funcionarios = FuncionarioRepository.findAll();
        return funcionarios;
    }

    @PostMapping("/cadastrar")
    public String cadastrarFuncionario(@RequestBody Funcionario funcionario){
        FuncionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }
    @PostMapping("/editar")
    public String editarFuncionario(@RequestBody Funcionario funcionario){
        FuncionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }
    @DeleteMapping("/deletar")
    public String excluirFuncionario(@RequestBody Funcionario funcionario) {
        FuncionarioRepository.delete(funcionario);
        return "redirect:/funcionarios";
    }
    
}
