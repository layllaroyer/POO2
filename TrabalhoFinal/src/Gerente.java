import java.time.LocalDate;
import java.io.Serializable;

public class Gerente extends Funcionario implements Serializable{
    private LocalDate dataingresso;
    private int nroagencia;
    private String possuicurso; //1 se sim e 0 se nao

    private static double comissao;

    public Gerente(String no, String cpf, String est, LocalDate dn, Endereco e, String r, String s, String c, int n, int a, double sal, LocalDate di, int nun, String p, int dia, int mes, double comissaoG){
        super(no,cpf,est,dn,e,r,s,c,n,a,sal, dia, mes);
        dataingresso=di;
        nroagencia=nun;
        possuicurso=p;
        comissao= comissaoG;

    }
    public static void setComissao(double c){
        comissao=c;
    }

    public static double getComissao(){
        return comissao;
    }

    public int getNroagencia() {
        return nroagencia;
    }

    public void setNroagencia(int nroagencia) {
        this.nroagencia = nroagencia;
    }

    public LocalDate getDataingresso() {
        return dataingresso;
    }

    public void setDataingresso(LocalDate dataingresso) {
        this.dataingresso = dataingresso;
    }

    public String getPossuicurso() {
        return possuicurso;
    }

    public void setPossuicurso(String possuicurso) {
        this.possuicurso = possuicurso;
    }

    @Override
    public double calculaSalario() {
        double salarioBase = super.calculaSalario();
        return salarioBase + comissao;
    }
}
