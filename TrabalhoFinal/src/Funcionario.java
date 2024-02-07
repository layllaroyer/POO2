import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.time.LocalDate;
public class Funcionario extends Pessoa {
    private String Rg,sexo,cargo;
    private int nrocarteira, anodeingresso;
    private double salario;
    private LocalDate dataIngressoEmpresa;

    public Funcionario(String no, String cpf, String est, LocalDate dn, Endereco e, String r, String s, String c, int n, int a, double sal, int dia, int mes){
        super(no,cpf,est,dn,e);
        Rg=r;
        sexo=s;
        cargo=c;
        nrocarteira=n;
        anodeingresso=a;
        salario=sal;
        dataIngressoEmpresa = LocalDate.of(a, mes, dia);
    }
    public double getSalário() {
        return salario;
    }

    public void setSalário(double salario) {
        this.salario = salario;
    }

    public int getAnodeingresso() {
        return anodeingresso;
    }

    public int getNrocarteira() {
        return nrocarteira;
    }

    public String getCargo() {
        return cargo;
    }

    public String getRg() {
        return Rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setAnodeingresso(int anodeingresso) {
        this.anodeingresso = anodeingresso;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setNrocarteira(int nrocarteira) {
        this.nrocarteira = nrocarteira;
    }

    public void setRg(String rg) {
        Rg = rg;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataIngressoEmpresa(){
        return dataIngressoEmpresa;
    }

    public void setDataIngressoEmpresa(LocalDate dataIngressoEmpresa) {
        this.dataIngressoEmpresa = dataIngressoEmpresa;
    }
}
