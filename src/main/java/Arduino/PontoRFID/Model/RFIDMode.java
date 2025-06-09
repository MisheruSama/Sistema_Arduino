package Arduino.PontoRFID.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RFIDMode {
    private boolean cadastro;
    private boolean leitura;
}
