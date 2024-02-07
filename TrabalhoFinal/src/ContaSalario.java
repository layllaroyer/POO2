import java.time.LocalDate;

public class ContaSalario extends Conta{

    private double limitesaq,limitetrans;

    public ContaSalario(Agencia age, int s,int n,String st,LocalDate da,LocalDate du,float sal, float ls, float lt, Cliente cliente1){
        super(age, s,n,st,da,du,sal, cliente1);
        limitesaq=ls;
        limitetrans=lt;
    }

    public boolean debitaValor(double val, int pwd) throws ErroTransacao{
        if((getStatus()).equals("Desativada"))
            throw new ErroTransacao("Esta conta está desativada!");
        isSenha(pwd);
        if(val>limitesaq)
            throw new ErroTransacao("O valor de saque ultrapassa o limite de saque da sua conta!");
        if(val>getSaldoatual())
            throw new ErroTransacao("O valor de saque é maior que o saldo e o cheque especial!");
        setSaldoatual(getSaldoatual()-val);
        return true;
    }

    public boolean debitaValorTransferencia(double val, int pwd) throws ErroTransacao{
        if((getStatus()).equals("Desativada"))
            throw new ErroTransacao("Esta conta está desativada!");
        isSenha(pwd);
        if(val>limitetrans)
            throw new ErroTransacao("O valor a ser transferido ultrapassa o limite de saque da sua conta!");
        if(val>getSaldoatual())
            throw new ErroTransacao("O valor valor da transação é maior que o saldo e o cheque especial!");
        setSaldoatual(getSaldoatual()-val);
        return true;
    }
    public double getLimitesaq() {
        return limitesaq;
    }

    public double getLimitetrans() {
        return limitetrans;
    }

    public void setLimitesaq(double limitesaq) {
        this.limitesaq = limitesaq;
    }

    public void setLimitetrans(double limitetrans) {
        this.limitetrans = limitetrans;
    }
}
