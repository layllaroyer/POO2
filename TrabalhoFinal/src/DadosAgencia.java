import java.util.HashMap;

public class DadosAgencia {
    private static HashMap<Integer,Agencia> agencias;

    public static void inicializaAgencias() {
        agencias = (HashMap<Integer, Agencia>) Persist.recuperar("agencias.dat");
        if (agencias == null)
            agencias = new HashMap<Integer, Agencia>();
    }

    public static void cadastrar(Agencia a, int numeroAgencia){
        agencias.put(numeroAgencia, a);
        boolean t = Persist.gravar(agencias, "agencias.dat");
    }

    public static HashMap<Integer, Agencia> getAgencias() {
        return agencias;
    }
}
