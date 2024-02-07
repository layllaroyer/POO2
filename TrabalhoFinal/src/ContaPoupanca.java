import java.time.LocalDate;
public class ContaPoupanca extends Conta{

    private double rendimento;

    public ContaPoupanca(Agencia age, int s, int n, String st, LocalDate da, LocalDate du, double sal, double r, Cliente cliente1, Cliente cliente2){
        super(age, s,n,st,da,du,sal,  cliente1, cliente2);
        rendimento=r;
    }

    public ContaPoupanca(Agencia age, int s,int n,String st,LocalDate da,LocalDate du,double sal, double r,  Cliente cliente1){
        super(age, s,n,st,da,du,sal, cliente1);
        rendimento=r;
    }

    public boolean debitaValor(double val, int pwd) throws ErroTransacao{
        if((getStatus()).equals("Desativada"))
            throw new ErroTransacao("Esta conta está desativada!");
        isSenha(pwd);
        if(val>getSaldoatual())
            throw new ErroTransacao("O valor valor da transação é maior que o saldo!");
        setSaldoatual(getSaldoatual()-val);
        return true;
    }
    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
}
