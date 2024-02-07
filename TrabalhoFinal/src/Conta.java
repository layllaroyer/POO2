import java.time.LocalDate;
import java.io.Serializable;
public abstract class Conta implements Serializable{
    private int senha,nroconta;
    private String status;
    private Cliente[] clientes = new Cliente[2];
    private Agencia agencia;
    private LocalDate dataAbertura;
    private LocalDate dataUltimaMovi;
    private double saldoatual;

    public Conta(Agencia age, int s, int n, String st, LocalDate da, LocalDate du, double sal, Cliente cliente1){
        agencia = age;
        senha=s;
        nroconta=n;
        status=st;
        dataAbertura=da;
        dataUltimaMovi=du;
        saldoatual=sal;
        clientes[0] = cliente1;
        clientes[1]=null;
    }

    public Conta(Agencia age, int s,int n,String st,LocalDate da,LocalDate du,double sal, Cliente cliente1, Cliente cliente2){
        agencia = age;
        senha=s;
        nroconta=n;
        status=st;
        dataAbertura=da;
        dataUltimaMovi=du;
        saldoatual=sal;
        clientes[0] = cliente1;
        clientes[1] = cliente2;
    }

    public abstract boolean debitaValor(double val, int pwd) throws ErroTransacao;
    public double getSaldoatual() {
        return saldoatual;
    }

    public int getNroconta() {
        return nroconta;
    }

    public int getSenha() {
        return senha;
    }

    public LocalDate getDataabertura() {
        return dataAbertura;
    }

    public LocalDate getDataUltimamovi() {
        return dataUltimaMovi;
    }

    public String getStatus() {
        return status;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public LocalDate getDataUltimaMovi() {
        return dataUltimaMovi;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataUltimaMovi(LocalDate dataUltimaMovi) {
        this.dataUltimaMovi = dataUltimaMovi;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }


    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public void setDataabertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataúltimamovi(LocalDate dataUltimaMovi) {
        this.dataUltimaMovi = dataUltimaMovi;
    }

    public void setNroconta(int nroconta) {
        this.nroconta = nroconta;
    }

    public void setSaldoatual(double saldoatual) {
        this.saldoatual = saldoatual;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSenha(int pwd) throws ErroTransacao{
        if(senha != pwd)
            throw new ErroTransacao("A senha está incorreta!");
        return true;
    }
}
