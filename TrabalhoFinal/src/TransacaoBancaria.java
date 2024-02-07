import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

public class TransacaoBancaria implements Imprimir {
    private String tipoTransacao;
    private LocalDate datatransacao;
    private double valortransacao;
    private int canaltransacao;//1-internet banking, 2-caixa eletrônico ou 3-caixa físico

    private Conta conta;

    public TransacaoBancaria(Conta c, LocalDate data, int canal, String tipo){
        conta=c;
        datatransacao= data;
        canaltransacao = canal;
        tipoTransacao = tipo;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public LocalDate getDatatransacao(){
        return datatransacao;
    }

    public void setDatatransacao(LocalDate datatransacao) {
        this.datatransacao = datatransacao;
    }

    public double getValortransacao() {
        return valortransacao;
    }

    public int getCanalransacao() {
        return canaltransacao;
    }

    public void setCanalransacao(int canalrtansacao) {
        this.canaltransacao = canaltransacao;
    }

    public void setValortransacao(double valortransacao) {
        this.valortransacao = valortransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    @Override
    public void imprimirDados() {
        System.out.println("Transação");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        System.out.println("Data: "+datatransacao.format(formatter));
        if(canaltransacao == 1)
            System.out.println("Canal da transação: Internet banking");
        else if(canaltransacao == 2)
            System.out.println("Canal de transação: Caixa eletrônico");
        else
            System.out.println("Canal de transação: Caixa físico");
    }

    public boolean sacar(int pwd, double valorSaque) throws ErroTransacao{
        return conta.debitaValor(valorSaque, pwd);
    }

    public boolean depositar(int pwd, double valorDeposito) throws ErroTransacao{
        conta.isSenha(pwd);
        if(conta.getStatus().equals("Desativada"))
            throw new ErroTransacao("Esta conta está desativada!");
        conta.setSaldoatual(conta.getSaldoatual()+valorDeposito);
        System.out.println(conta.getSaldoatual());
        return true;
    }

    public boolean consultarSaldo(int pwd) throws ErroTransacao{
        conta.isSenha(pwd);
        if(conta.getStatus().equals("Desativada"))
            throw new ErroTransacao("Esta conta está desativada!");
        System.out.println("Saldo atual:"+ conta.getSaldoatual());
        return true;
    }

    public boolean efetuarPagamento(int pwd, double valorPagamento) throws ErroTransacao{
        return conta.debitaValor(valorPagamento, pwd);
    }
}
