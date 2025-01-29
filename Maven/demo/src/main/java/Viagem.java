import java.util.Date;

public class Viagem {
    public Date data;
    public String horaPartida;
    public String horaChegada;
    
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    
    public String getHoraPartida() {
        return horaPartida;
    }
    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }
    
    public String getHoraChegada() {
        return horaChegada;
    }
    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }
}

