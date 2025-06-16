package Arduino.PontoRFID.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// classe para controlar as páginas do sistema
@Controller
public class PaginaController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/historico")
    public String historico(){
        return "historico";
    }
}
