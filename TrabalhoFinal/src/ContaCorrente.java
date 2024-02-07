import java.time.LocalDate;
import java.util.ArrayList;
public class ContaCorrente extends Conta {

    private double limitechequesp,valortaxaadm;

    public ContaCorrente(Agencia age, int s, int n, String st, LocalDate da, LocalDate du, double sal, double lim, double t, Cliente cliente1){
        super(age, s,n,st,da,du,sal, cliente1);
        limitechequesp=lim;
        valortaxaadm=t;
    }

    public ContaCorrente(Agencia age, int s,int n,String st,LocalDate da,LocalDate du,double sal, double lim, double t, Cliente cliente1, Cliente cliente2){
        super(age, s,n,st,da,du,sal, cliente1, cliente2);
        limitechequesp=lim;
        valortaxaadm=t;
    }

    public boolean debitaValor(double val, int pwd) throws ErroTransacao{
        if((getStatus()).equals("Desativada"))
            throw new ErroTransacao("Esta conta está desativada!");
        isSenha(pwd);
        if(val>(getSaldoatual()+limitechequesp))
            throw new ErroTransacao("O valor da transação é maior que o saldo na conta e o cheque especial disponível para sua conta!");
        if(val>getSaldoatual())
            System.out.println("O cheque especial da sua conta foi ativado!");
        setSaldoatual(getSaldoatual()-val);

        return true;
    }
    public double getLimitechequesp() {
        return limitechequesp;
    }

    public double getValortaxaadm() {
        return valortaxaadm;
    }

    public void setLimitechequesp(double limitechequesp) {
        this.limitechequesp = limitechequesp;
    }

    public void setValortaxaadm(double valortaxaadm) {
        this.valortaxaadm = valortaxaadm;
    }
}
