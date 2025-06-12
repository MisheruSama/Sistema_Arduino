package Arduino.PontoRFID.Model;

import org.springframework.stereotype.Component;

@Component
public class RFIDMode {
    private boolean cadastro = false;
    private String ultimoRfidUid;
    public boolean isCadastro() {
        return cadastro;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    public String getUltimoRfidUid() {
        return ultimoRfidUid;
    }

    public void setUltimoRfidUid(String ultimoRfidUid) {
        this.ultimoRfidUid = ultimoRfidUid;
    }

}
