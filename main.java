import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAOImpl();

        while (true) {
            System.out.println("\n### Menu ###");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Visualizar clientes");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Excluir cliente");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (escolha) {
                case 1:
                    cadastrarCliente(scanner, clienteDAO);
                    break;
                case 2:
                    visualizarClientes(clienteDAO);
                    break;
                case 3:
                    atualizarCliente(scanner, clienteDAO);
                    break;
                case 4:
                    excluirCliente(scanner, clienteDAO);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    static void cadastrarCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a idade do cliente: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Cliente cliente = new Cliente(nome, cpf, idade);
        clienteDAO.salvar(cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    static void visualizarClientes(ClienteDAO clienteDAO) {
        List<Cliente> todosClientes = clienteDAO.listarTodos();
        System.out.println("\n### Clientes Cadastrados ###");
        for (Cliente cliente : todosClientes) {
            System.out.println(cliente);
        }
    }

    static void atualizarCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.print("Digite o código do cliente que deseja atualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        Cliente clienteExistente = clienteDAO.buscarPorCodigo(codigo);
        if (clienteExistente != null) {
            System.out.print("Digite o novo nome do cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o novo CPF do cliente: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a nova idade do cliente: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Cliente clienteAtualizado = new Cliente(nome, cpf, idade);
            clienteAtualizado.setCodigo(codigo); // Definindo o código do cliente
            clienteDAO.atualizar(clienteAtualizado);
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente com código " + codigo + " não encontrado.");
        }
    }

    static void excluirCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.print("Digite o código do cliente que deseja excluir: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        clienteDAO.excluir(codigo);
        System.out.println("Cliente com código " + codigo + " excluído com sucesso.");
    }

    static class Cliente {
        private static int proximoCodigo = 1;
        private int codigo;
        private String nome;
        private String cpf;
        private int idade;

        public Cliente(String nome, String cpf, int idade) {
            this.codigo = proximoCodigo++;
            this.nome = nome;
            this.cpf = cpf;
            this.idade = idade;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }

        @Override
        public String toString() {
            return "Código: " + codigo + ", Nome: " + nome + ", CPF: " + cpf + ", Idade: " + idade;
        }
    }

    interface ClienteDAO {
        void salvar(Cliente cliente);
        Cliente buscarPorCodigo(int codigo);
        List<Cliente> listarTodos();
        void atualizar(Cliente cliente);
        void excluir(int codigo);
    }

    static class ClienteDAOImpl implements ClienteDAO {
        private List<Cliente> clientes = new ArrayList<>();

        @Override
        public void salvar(Cliente cliente) {
            clientes.add(cliente);
        }

        @Override
        public Cliente buscarPorCodigo(int codigo) {
            for (Cliente cliente : clientes) {
                if (cliente.getCodigo() == codigo) {
                    return cliente;
                }
            }
            return null;
        }

        @Override
        public List<Cliente> listarTodos() {
            return new ArrayList<>(clientes);
        }

        @Override
        public void atualizar(Cliente cliente) {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getCodigo() == cliente.getCodigo()) {
                    clientes.set(i, cliente);
                    return;
                }
            }
        }

        @Override
        public void excluir(int codigo) {
            clientes.removeIf(cliente -> cliente.getCodigo() == codigo);
        }
    }
}
