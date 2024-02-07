import java.util.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
public class Main {
    public static void main(String[] args) throws ParseException {
        DadosAgencia.inicializaAgencias();
        HashMap<Integer, Agencia> agencias = DadosAgencia.getAgencias();
        Agencia ag = agencias.get(741894);

        int op = 0;
        do {
            System.out.println("-------------------------------------------");
            System.out.println("--------------------Banco------------------");
            System.out.println("-------------------------------------------");
            System.out.println("***Selecione a operação que deseja fazer***");
            System.out.println("-------------------------------------------");
            System.out.println("|   Opcao 1 - Cadastrar cliente   |");
            System.out.println("|   Opcao 2 - Listar contas       |");
            System.out.println("|   Opcao 3 - Depositar           |");
            System.out.println("|   Opcao 4 - Sacar               |");
            System.out.println("|   Opcao 5 - Consultar saldo     |");
            System.out.println("|   Opcao 6 - Efetuar pagamento   |");
            System.out.println("|   Opcao 7 - Sair                |");

            Scanner ent = new Scanner(System.in);
            op = ent.nextInt();
            clearBuffer(ent);
            switch (op) {
                case 1:
                    Cliente c = new Cliente();
                    String nome, cpf;
                    System.out.println("Digite seu nome:");
                    nome = ent.nextLine();
                    c.setNome(nome);
                    System.out.println("Digite seu CPF:");
                    cpf = ent.nextLine();
                    boolean r=false;
                    while(r==false) {
                        try {
                            r=Pessoa.isCPF(cpf);
                        } catch (CPFInvalido a) {
                            System.out.println(a.getMessage());
                        }
                        if(r==false) {
                            System.out.println("Digite seu cpf novamente:");
                            cpf = ent.nextLine();
                        }
                    }
                    c.setCPF(cpf);
                    String arquivo = String.valueOf(ag.getNroagencia());
                    arquivo = arquivo.concat("Clientes.dat");
                    DadosClientes.inicializaClientes(arquivo);
                    Cliente retorno = DadosClientes.remover(cpf,arquivo);
                    if(retorno != null){
                        c.setContas(retorno.getContas());
                    }
                    int d, m, a;
                    System.out.println("Digite o dia do seu nascimento:");
                    d = ent.nextInt();
                    System.out.println("Digite o mês do seu nascimento:");
                    m = ent.nextInt();
                    System.out.println("Digite o ano do seu nascimento:");
                    a = ent.nextInt();
                    clearBuffer(ent);
                    LocalDate datacliente = LocalDate.of(a, m, d);
                    c.setDatanasc(datacliente);
                    String cid, bairro, est;
                    System.out.println("Digite seu estado:");
                    est = ent.nextLine();
                    System.out.println("Digite sua cidade:");
                    cid = ent.nextLine();
                    System.out.println("Digite seu bairro:");
                    bairro = ent.nextLine();
                    Endereco en = new Endereco(est, cid, bairro);
                    c.setEnd(en);
                    System.out.println("Digite sua escolaridade:");
                    String esc = ent.nextLine();
                    c.setEscolaridade(esc);
                    System.out.println("Digite seu estado civil:");
                    String ec = ent.nextLine();
                    c.setEstadocivil(ec);
                    c.setAgencia(ag);
                    System.out.println("Os dados da sua agencia são:");
                    System.out.printf("nome: %s\n", ag.getNome());
                    System.out.printf("numero: %d\n", ag.getNroagencia());
                    Endereco aux = ag.getE();
                    System.out.print("endereço:");
                    System.out.println(aux.getEstado() + "-" + aux.getCidade() + "-" + aux.getBairro() + "\n");
                    System.out.printf("Digite o tipo de conta que deseja criar:\n1-Conta Salario;\n2-Conta Corrente;\n3-Conta Poupança.\n");
                    int op1 = ent.nextInt();
                    clearBuffer(ent);
                    switch (op1) {

                        case 1:
                            System.out.println("Digite a senha que deseja utilizar:");
                            int senha1 = ent.nextInt();
                            System.out.println("Digite novamente para confirmar:");
                            int senha2 = ent.nextInt();
                            while (senha1 != senha2) {
                                System.out.println("As senhas não são iguais. Digite novamente:");
                                senha2 = ent.nextInt();
                            }
                            int randomNumber = generateRandomNumber(100000, 999999);
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas = DadosContas.getContas();
                            ContaSalario sa = (ContaSalario) contas.get(randomNumber);
                            while (sa != null) {
                                randomNumber = generateRandomNumber(100000, 999999);
                                sa = (ContaSalario) contas.get(randomNumber);
                            }
                            System.out.printf("Seu numero da conta é: %d\n", randomNumber);
                            LocalDate datacriacao = LocalDate.now();
                            System.out.printf("Defina o seu limite de saque:");
                            float limsaque = ent.nextFloat();
                            System.out.printf("Defina o seu limite de transação:");
                            float limtrans = ent.nextFloat();

                            ContaSalario s = new ContaSalario(ag, senha1, randomNumber, "ativa", datacriacao, datacriacao, 0, limsaque, limtrans, c);
                            System.out.println("Conta criada com sucesso!");
                            c.adicionarConta(s);
                            DadosContas.inicializaContas();
                            DadosContas.cadastrar(s,randomNumber);
                            break;
                        case 2:
                            System.out.println("Digite a senha que deseja utilizar:");
                            senha1 = ent.nextInt();
                            System.out.println("Digite novamente para confirmar:");
                            senha2 = ent.nextInt();
                            while (senha1 != senha2) {
                                System.out.println("As senhas não são iguais. Digite novamente:");
                                senha2 = ent.nextInt();
                            }
                            randomNumber = generateRandomNumber(100000, 999999);
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas1 = DadosContas.getContas();
                            ContaCorrente cor = (ContaCorrente) contas1.get(randomNumber);
                            while (cor != null) {
                                randomNumber = generateRandomNumber(100000, 999999);
                                cor = (ContaCorrente) contas1.get(randomNumber);
                            }
                            System.out.printf("Seu numero da conta é: %d\n", randomNumber);
                            datacriacao = LocalDate.now();

                            System.out.printf("Seu limite inicial de cheque especial é de 2500,00.\n");
                            System.out.printf("O valor da taxa administrativa é: 3,59.\n");
                            ContaCorrente co = new ContaCorrente(ag, senha1, randomNumber, "ativa", datacriacao, datacriacao, 0, 2500F, 3.59, c);
                            System.out.println("Conta criada com sucesso!\n\n");
                            c.adicionarConta(co);
                            DadosContas.inicializaContas();
                            DadosContas.cadastrar(co,randomNumber);
                            break;
                        case 3:
                            System.out.println("Digite a senha que deseja utilizar:");
                            senha1 = ent.nextInt();
                            System.out.println("Digite novamente para confirmar:");
                            senha2 = ent.nextInt();
                            while (senha1 != senha2) {
                                System.out.println("As senhas não são iguais. Digite novamente:");
                                senha2 = ent.nextInt();
                            }
                            randomNumber = generateRandomNumber(100000, 999999);
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas2 = DadosContas.getContas();
                            ContaPoupanca pop = (ContaPoupanca) contas2.get(randomNumber);

                            while (pop != null) {
                                randomNumber = generateRandomNumber(100000, 999999);
                                pop = (ContaPoupanca) contas2.get(randomNumber);
                            }
                            System.out.printf("Seu numero da conta é: %d\n", randomNumber);
                            datacriacao = LocalDate.now();

                            ContaPoupanca po = new ContaPoupanca(ag, senha1, randomNumber, "ativa", datacriacao, datacriacao, 0, 0.68, c);
                            System.out.println("Conta criada com sucesso!\n\n");
                            c.adicionarConta(po);
                            DadosContas.inicializaContas();
                            DadosContas.cadastrar(po,randomNumber);

                            break;
                        }
                    DadosClientes.cadastrar(c, arquivo);

                    break;


                case 2:
                    System.out.printf("\nDigite seu CPF:");
                    cpf= ent.nextLine();
                    boolean resultado= false;
                    int opcaoCPF = 0;
                    while((resultado==false)&&(opcaoCPF < 3)){
                        try{
                            resultado=Pessoa.isCPF(cpf);
                        }catch(CPFInvalido ex){
                            opcaoCPF++;
                            System.out.println(ex.getMessage());
                            System.out.println("Você possui apenas 3 tentativas, restam: "+(3-opcaoCPF));
                        }
                        if(resultado)
                            break;
                        System.out.printf("\nDigite novamente seu CPF:");
                        cpf= ent.nextLine();
                    }
                    System.out.println("\nDigite o número da sua agência:");
                    int numeroAgencia = ent.nextInt();
                    String arquivoClientes = String.valueOf(numeroAgencia);
                    arquivoClientes = arquivoClientes.concat("Clientes.dat");
                    DadosClientes.inicializaClientes(arquivoClientes);
                    HashMap<String, Cliente> clientes2 = DadosClientes.getClientes();
                    Cliente cli = clientes2.get(cpf);

                    ArrayList<Conta> contas4 = cli.getContas();

                    for(int i=0; i < contas4.size(); i++) {
                        Conta contaCliente = contas4.get(i);
                        if (contaCliente instanceof ContaSalario) {
                            System.out.println("Informe a senha da sua conta salario:");
                            int senhaConta = ent.nextInt();
                            try {
                                if (contaCliente.isSenha(senhaConta)) {
                                    System.out.println("Número da conta: " + contaCliente.getNroconta());
                                    System.out.println("Data de abertura: " + contaCliente.getDataAbertura());
                                    System.out.println("Status da conta: " + contaCliente.getStatus());
                                    System.out.println(contaCliente.getSaldoatual());
                                    System.out.println("\n\n");
                                }
                            } catch (ErroTransacao e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (contaCliente instanceof ContaCorrente) {
                            System.out.println("Informe a senha da sua conta corrente:");
                            int senhaConta = ent.nextInt();
                            try {
                                if (contaCliente.isSenha(senhaConta)) {
                                    System.out.println("Número da conta: " + contaCliente.getNroconta());
                                    System.out.println("Data de abertura: " + contaCliente.getDataAbertura());
                                    System.out.println("Status da conta: " + contaCliente.getStatus());
                                    System.out.println(contaCliente.getSaldoatual());
                                    System.out.println("\n\n");
                                }
                            } catch (ErroTransacao e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (contaCliente instanceof ContaPoupanca) {
                            System.out.println("Informe a senha da sua conta poupanca:");
                            int senhaConta = ent.nextInt();
                            try {
                                if (contaCliente.isSenha(senhaConta)) {
                                    System.out.println("Número da conta: " + contaCliente.getNroconta());
                                    System.out.println("Data de abertura: " + contaCliente.getDataAbertura());
                                    System.out.println("Status da conta: " + contaCliente.getStatus());
                                    System.out.println(contaCliente.getSaldoatual());
                                    System.out.println("\n\n");
                                }
                            } catch (ErroTransacao e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.printf("Digite qual seu tipo de conta:\n1-Conta Salario;\n2-Conta Corrente;\n3-Conta Poupança.\n");
                    int op2 = ent.nextInt();
                    clearBuffer(ent);
                    switch (op2) {

                        case 1:
                            System.out.println("Digite o numero da sua conta salario:");
                            int nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas = DadosContas.getContas();
                            ContaSalario sa = (ContaSalario) contas.get(nroconta1);
                            if (sa == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            System.out.println("\n" + sa.getNroconta() + "\n");
                            DadosContas.remover(nroconta1);
                            LocalDate datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            int canal = ent.nextInt();
                            TransacaoBancaria t = new TransacaoBancaria(sa, datacriacao, canal, "depositar");
                            System.out.println("\nDigite sua senha:");
                            int senha = ent.nextInt();
                            System.out.println("\nDigite o valor do deposito:");
                            double valor = ent.nextDouble();

                            try {
                                t.depositar(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nDeposito realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq = String.valueOf(sa.getAgencia().getNroagencia());
                            arq = arq.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq);
                            Cliente clienteAux = DadosClientes.remover(sa.getClientes()[0].getCPF(), arq);
                            ArrayList<Conta> contasCliente = clienteAux.getContas();
                            for(int i =0; i<contasCliente.size(); i++){
                                if(contasCliente.get(i).getNroconta() == sa.getNroconta()){
                                    contasCliente.get(i).setSaldoatual(sa.getSaldoatual());
                                }
                            }
                            clienteAux.setContas(contasCliente);
                            DadosClientes.cadastrar(clienteAux, arq);
                            DadosContas.cadastrar(sa,nroconta1);
                            break;

                        case 2:
                            System.out.println("\nDigite o numero da sua conta corrente:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas2 = DadosContas.getContas();
                            ContaCorrente co = (ContaCorrente) contas2.get(nroconta1);
                            if (co == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + co.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(co, datacriacao, canal, "depositar");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            System.out.println("\nDigite o valor do deposito:");
                            valor = ent.nextDouble();

                            try {
                                t.depositar(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nDeposito realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq2 = String.valueOf(co.getAgencia().getNroagencia());
                            arq2 = arq2.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq2);
                            Cliente clienteAux2 = DadosClientes.remover(co.getClientes()[0].getCPF(), arq2);
                            ArrayList<Conta> contasCliente2 = clienteAux2.getContas();
                            for(int i =0; i<contasCliente2.size(); i++){
                                if(contasCliente2.get(i).getNroconta() == co.getNroconta()){
                                    contasCliente2.get(i).setSaldoatual(co.getSaldoatual());
                                }
                            }
                            clienteAux2.setContas(contasCliente2);
                            DadosClientes.cadastrar(clienteAux2, arq2);
                            DadosContas.cadastrar(co,nroconta1);
                            break;
                        case 3:
                            System.out.println("\nDigite o numero da sua conta poupanca:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas3 = DadosContas.getContas();
                            ContaPoupanca pop = (ContaPoupanca) contas3.get(nroconta1);
                            if (pop == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + pop.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(pop, datacriacao, canal, "depositar");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            System.out.println("\nDigite o valor do deposito:");
                            valor = ent.nextDouble();
                            t.setValortransacao(valor);

                            try {
                                t.depositar(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nDeposito realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq3 = String.valueOf(pop.getAgencia().getNroagencia());
                            arq3 = arq3.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq3);
                            Cliente clienteAux3 = DadosClientes.remover(pop.getClientes()[0].getCPF(), arq3);
                            ArrayList<Conta> contasCliente3 = clienteAux3.getContas();
                            for(int i =0; i<contasCliente3.size(); i++){
                                if(contasCliente3.get(i).getNroconta() == pop.getNroconta()){
                                    contasCliente3.get(i).setSaldoatual(pop.getSaldoatual());
                                }
                            }
                            clienteAux3.setContas(contasCliente3);
                            DadosClientes.cadastrar(clienteAux3, arq3);
                            DadosContas.cadastrar(pop,nroconta1);
                            break;
                    }
                    break;

                case 4:
                    System.out.printf("Digite qual seu tipo de conta:\n1-Conta Salario;\n2-Conta Corrente;\n3-Conta Poupança.\n");
                    int op3 = ent.nextInt();
                    clearBuffer(ent);
                    switch (op3) {

                        case 1:
                            System.out.println("Digite o numero da sua conta salario:");
                            int nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas = DadosContas.getContas();
                            ContaSalario sa = (ContaSalario) contas.get(nroconta1);
                            if (sa == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + sa.getNroconta() + "\n");
                            LocalDate datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            int canal = ent.nextInt();
                            TransacaoBancaria t = new TransacaoBancaria(sa, datacriacao, canal, "sacar");
                            System.out.println("\nDigite sua senha:");
                            int senha = ent.nextInt();
                            System.out.println("\nDigite o valor do saque:");
                            double valor = ent.nextDouble();

                            try {
                                t.sacar(senha,valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nSaque realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq = String.valueOf(sa.getAgencia().getNroagencia());
                            arq = arq.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq);
                            Cliente clienteAux = DadosClientes.remover(sa.getClientes()[0].getCPF(), arq);
                            ArrayList<Conta> contasCliente = clienteAux.getContas();
                            for(int i =0; i<contasCliente.size(); i++){
                                if(contasCliente.get(i).getNroconta() == sa.getNroconta()){
                                    contasCliente.get(i).setSaldoatual(sa.getSaldoatual());
                                }
                            }
                            clienteAux.setContas(contasCliente);
                            DadosClientes.cadastrar(clienteAux, arq);
                            DadosContas.cadastrar(sa,nroconta1);
                            break;

                        case 2:
                            System.out.println("\nDigite o numero da sua conta corrente:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas2 = DadosContas.getContas();
                            ContaCorrente co = (ContaCorrente) contas2.get(nroconta1);
                            if (co == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + co.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(co, datacriacao, canal, "sacar");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            System.out.println("\nDigite o valor do saque:");
                            valor = ent.nextDouble();

                            try {
                                t.sacar(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nSaque realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq2 = String.valueOf(co.getAgencia().getNroagencia());
                            arq2 = arq2.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq2);
                            Cliente clienteAux2 = DadosClientes.remover(co.getClientes()[0].getCPF(), arq2);
                            ArrayList<Conta> contasCliente2 = clienteAux2.getContas();
                            for(int i =0; i<contasCliente2.size(); i++){
                                if(contasCliente2.get(i).getNroconta() == co.getNroconta()){
                                    contasCliente2.get(i).setSaldoatual(co.getSaldoatual());
                                }
                            }
                            clienteAux2.setContas(contasCliente2);
                            DadosClientes.cadastrar(clienteAux2, arq2);
                            DadosContas.cadastrar(co,nroconta1);
                            break;
                        case 3:
                            System.out.println("\nDigite o numero da sua conta poupanca:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas3 = DadosContas.getContas();
                            ContaPoupanca pop = (ContaPoupanca) contas3.get(nroconta1);
                            if (pop == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + pop.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(pop, datacriacao, canal, "sacar");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            System.out.println("\nDigite o valor do saque:");
                            valor = ent.nextDouble();
                            t.setValortransacao(valor);

                            try {
                                t.sacar(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nSaque realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq3 = String.valueOf(pop.getAgencia().getNroagencia());
                            arq3 = arq3.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq3);
                            Cliente clienteAux3 = DadosClientes.remover(pop.getClientes()[0].getCPF(), arq3);
                            ArrayList<Conta> contasCliente3 = clienteAux3.getContas();
                            for(int i =0; i<contasCliente3.size(); i++){
                                if(contasCliente3.get(i).getNroconta() == pop.getNroconta()){
                                    contasCliente3.get(i).setSaldoatual(pop.getSaldoatual());
                                }
                            }
                            clienteAux3.setContas(contasCliente3);
                            DadosClientes.cadastrar(clienteAux3, arq3);
                            DadosContas.cadastrar(pop,nroconta1);
                            break;
                    }
                    break;
                case 5:
                    System.out.printf("Digite qual seu tipo de conta:\n1-Conta Salario;\n2-Conta Corrente;\n3-Conta Poupança.\n");
                    int op4 = ent.nextInt();
                    clearBuffer(ent);
                    switch (op4) {

                        case 1:
                            System.out.println("Digite o numero da sua conta salario:");
                            int nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas = DadosContas.getContas();
                            ContaSalario sa = (ContaSalario) contas.get(nroconta1);
                            if (sa == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            System.out.println("\n" + sa.getNroconta() + "\n");
                            LocalDate datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            int canal = ent.nextInt();
                            TransacaoBancaria t = new TransacaoBancaria(sa, datacriacao, canal, "consultar saldo");
                            System.out.println("\nDigite sua senha:");
                            int senha = ent.nextInt();
                            try {
                                t.consultarSaldo(senha);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());

                            }
                            break;

                        case 2:
                            System.out.println("\nDigite o numero da sua conta corrente:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas2 = DadosContas.getContas();
                            ContaCorrente co = (ContaCorrente) contas2.get(nroconta1);
                            if (co == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            System.out.println("\n" + co.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(co, datacriacao, canal, "consultar saldo");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            try {
                                t.consultarSaldo(senha);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("\nDigite o numero da sua conta poupanca:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas3 = DadosContas.getContas();
                            ContaPoupanca pop = (ContaPoupanca) contas3.get(nroconta1);
                            if (pop == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            System.out.println("\n" + pop.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(pop, datacriacao, canal, "consultar saldo");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            try {
                                t.consultarSaldo(senha);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            break;
                    }
                    break;

                case 6:
                    System.out.printf("Digite qual seu tipo de conta:\n1-Conta Salario;\n2-Conta Corrente;\n3-Conta Poupança.\n");
                    int op5 = ent.nextInt();
                    clearBuffer(ent);
                    switch (op5) {

                        case 1:
                            System.out.println("Digite o numero da sua conta salario:");
                            int nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas = DadosContas.getContas();
                            ContaSalario sa = (ContaSalario) contas.get(nroconta1);

                            if (sa == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + sa.getNroconta() + "\n");
                            LocalDate datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            int canal = ent.nextInt();
                            TransacaoBancaria t = new TransacaoBancaria(sa, datacriacao, canal, "pagamento");
                            System.out.println("\nDigite sua senha:");
                            int senha = ent.nextInt();
                            System.out.println("\nDigite o valor do pagamento:");
                            double valor = ent.nextDouble();

                            try {
                                t.efetuarPagamento(senha,valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nPagamento realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq = String.valueOf(sa.getAgencia().getNroagencia());
                            arq = arq.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq);
                            Cliente clienteAux = DadosClientes.remover(sa.getClientes()[0].getCPF(), arq);
                            ArrayList<Conta> contasCliente = clienteAux.getContas();
                            for(int i =0; i<contasCliente.size(); i++){
                                if(contasCliente.get(i).getNroconta() == sa.getNroconta()){
                                    contasCliente.get(i).setSaldoatual(sa.getSaldoatual());
                                }
                            }
                            clienteAux.setContas(contasCliente);
                            DadosClientes.cadastrar(clienteAux, arq);
                            DadosContas.cadastrar(sa,nroconta1);
                            break;

                        case 2:
                            System.out.println("\nDigite o numero da sua conta corrente:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas2 = DadosContas.getContas();
                            ContaCorrente co = (ContaCorrente) contas2.get(nroconta1);
                            if (co == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + co.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(co, datacriacao, canal, "pagamento");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            System.out.println("\nDigite o valor do pagamento:");
                            valor = ent.nextDouble();

                            try {
                                t.efetuarPagamento(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nPagamento realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq2 = String.valueOf(co.getAgencia().getNroagencia());
                            arq2 = arq2.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq2);
                            Cliente clienteAux2 = DadosClientes.remover(co.getClientes()[0].getCPF(), arq2);
                            ArrayList<Conta> contasCliente2 = clienteAux2.getContas();
                            for(int i =0; i<contasCliente2.size(); i++){
                                if(contasCliente2.get(i).getNroconta() == co.getNroconta()){
                                    contasCliente2.get(i).setSaldoatual(co.getSaldoatual());
                                }
                            }
                            clienteAux2.setContas(contasCliente2);
                            DadosClientes.cadastrar(clienteAux2, arq2);
                            DadosContas.cadastrar(co,nroconta1);
                            break;
                        case 3:
                            System.out.println("\nDigite o numero da sua conta poupanca:");
                            nroconta1 = ent.nextInt();
                            DadosContas.inicializaContas();
                            HashMap<Integer, Conta> contas3 = DadosContas.getContas();
                            ContaPoupanca pop = (ContaPoupanca) contas3.get(nroconta1);
                            if (pop == null) {
                                System.out.println("Essa conta não existe.");
                                break;
                            }
                            DadosContas.remover(nroconta1);
                            System.out.println("\n" + pop.getNroconta() + "\n");
                            datacriacao = LocalDate.now();
                            System.out.printf("Digite qual seu tipo de canal da transacao:\n1-Internet Banking;\n2-Caixa Eletronico;\n3-Caixa Fisico.\n");
                            canal = ent.nextInt();
                            t = new TransacaoBancaria(pop, datacriacao, canal, "pagamento");
                            System.out.println("\nDigite sua senha:");
                            senha = ent.nextInt();
                            System.out.println("\nDigite o valor do pagamento:");
                            valor = ent.nextDouble();
                            t.setValortransacao(valor);

                            try {
                                t.efetuarPagamento(senha, valor);
                            } catch (ErroTransacao erro) {
                                System.out.println(erro.getMessage());
                            }
                            System.out.println("\nSaque realizado com sucesso!\n");
                            t.imprimirDados();
                            String arq3 = String.valueOf(pop.getAgencia().getNroagencia());
                            arq3 = arq3.concat("Clientes.dat");
                            DadosClientes.inicializaClientes(arq3);
                            Cliente clienteAux3 = DadosClientes.remover(pop.getClientes()[0].getCPF(), arq3);
                            ArrayList<Conta> contasCliente3 = clienteAux3.getContas();
                            for(int i =0; i<contasCliente3.size(); i++){
                                if(contasCliente3.get(i).getNroconta() == pop.getNroconta()){
                                    contasCliente3.get(i).setSaldoatual(pop.getSaldoatual());
                                }
                            }
                            clienteAux3.setContas(contasCliente3);
                            DadosClientes.cadastrar(clienteAux3, arq3);
                            DadosContas.cadastrar(pop,nroconta1);
                            break;
                    }
                    break;

                case 7:
                    System.out.println("\n\nObrigada por usar nosso banco:)\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao invalida!\n\n");
                    break;
            }
        }while (op != 7) ;
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}

