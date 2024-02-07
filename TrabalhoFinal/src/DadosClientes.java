import java.util.ArrayList;
import java.util.HashMap;

public class DadosClientes {
    private static HashMap<String, Cliente> clientes;
    public static void inicializaClientes(String arquivo){
        clientes = (HashMap<String, Cliente>) Persist.recuperar(arquivo);
        if(clientes == null)
            clientes = new HashMap<String, Cliente>();
    }

    public static void cadastrar(Cliente c, String arquivo){
        clientes.put(c.getCPF(), c);
        boolean r = Persist.gravar(clientes, arquivo);
    }
    public static Cliente remover(String CPF, String arquivo){
        Cliente cli = clientes.remove(CPF);
        boolean t = Persist.gravar(clientes, arquivo);
        return cli;
    }

    public static HashMap<String, Cliente> getClientes() {
        return clientes;
    }
}
