import java.io.Serializable;

public class Endereco implements Serializable {

    private String cidade, bairro, estado;

    public Endereco(String est,String cit,String ba){
        estado=est;
        cidade=cit;
        bairro=ba;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
