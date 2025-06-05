package Arduino.Ponto_RFID.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
