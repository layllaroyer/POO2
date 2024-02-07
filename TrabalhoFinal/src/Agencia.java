import java.io.Serializable;
import java.util.ArrayList;

public class Agencia implements Serializable {
    private int nroagencia;
    private String nome;
    private Endereco e;
    private Gerente gerente;

    private ArrayList<Funcionario> funcionarios;
    public Agencia(String n, Endereco end, Gerente gerente, int numero, ArrayList<Funcionario> func){
        this.gerente = gerente;
        nome=n;
        e=end;
        nroagencia = numero;
        funcionarios = func;
    }
    public void setNroagencia(int nroagencia) {
        this.nroagencia = nroagencia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNroagencia() {
        return nroagencia;
    }
    public String getNome(){
        return nome;
    }

    public Endereco getE() {
        return e;
    }

    public void setE(Endereco e) {
        this.e = e;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
