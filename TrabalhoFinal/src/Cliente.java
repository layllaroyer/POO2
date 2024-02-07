import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Pessoa implements Serializable{
    private String escolaridade;
    private Agencia agencia;

    private ArrayList<Conta> contas = new ArrayList<Conta>();
    public Cliente(){

    }

    public Cliente (String nome, String cpf, String est, LocalDate dataNasc, Endereco end, Agencia age ){
        super(nome,cpf, est, dataNasc, end);
        agencia = age;

    }

    public Agencia getAgencia() {
        return agencia;
    }

    public String getEscolaridade() {
        return escolaridade;
    }


    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }


    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public void adicionarConta(Conta c){
        contas.add(c);
    }
}
