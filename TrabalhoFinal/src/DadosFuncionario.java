import java.util.ArrayList;

public class DadosFuncionario {
    private static ArrayList<Funcionario> funcionarios;
    public static void inicializaFuncionarios(){
        funcionarios = (ArrayList<Funcionario>)Persist.recuperar("funcionarios.dat");
        if(funcionarios == null)
            funcionarios = new ArrayList<Funcionario>();
    }

    public static void cadastrar(Funcionario f){
        funcionarios.add(f);
        boolean r = Persist.gravar(funcionarios, "funcionarios.dat");
    }

    public static ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
