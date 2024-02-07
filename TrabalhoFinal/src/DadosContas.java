import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DadosContas{
    private static HashMap<Integer,Conta> contas;

    public static void inicializaContas(){
        contas = (HashMap<Integer, Conta>)Persist.recuperar("contas.dat");
        if(contas==null)
            contas = new HashMap<Integer, Conta>();
    }

    public static void cadastrar(Conta c, int numeroConta){
        contas.put(numeroConta, c);
        boolean t = Persist.gravar(contas, "contas.dat");
    }

    public static void remover(int numeroConta){
        contas.remove(numeroConta);
        boolean t = Persist.gravar(contas, "contas.dat");
    }

    public static HashMap<Integer, Conta> getContas() {
        return contas;
    }
}
