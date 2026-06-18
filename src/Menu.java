import java.util.Scanner;

public class Menu {

    public static int menuPrincipal(Scanner sc) {
        System.out.println("\n=== AUTO CENTER ROTA 381 ===");
        System.out.println("1. Cadastros");
        System.out.println("2. Ordem de Servico");
        System.out.println("3. Relatorios");
        System.out.println("0. Sair");
        System.out.print("Opcao: ");
        return lerOpcao(sc);
    }

    public static int menuCadastro(Scanner sc) {
        System.out.println("\n=== CADASTROS ===");
        System.out.println("1. Cadastrar Mecanico");
        System.out.println("2. Cadastrar Veiculo");
        System.out.println("3. Cadastrar Peca");
        System.out.println("0. Voltar");
        System.out.print("Opcao: ");
        return lerOpcao(sc);
    }

    public static int menuOS(Scanner sc) {
        System.out.println("\n=== ORDENS DE SERVICO ===");
        System.out.println("1. Abrir OS");
        System.out.println("2. Listar OS");
        System.out.println("3. Historico de OS por veiculo");
        System.out.println("0. Voltar");
        System.out.print("Opcao: ");
        return lerOpcao(sc);
    }

    public static int menuRelatorio(Scanner sc) {
        System.out.println("\n=== RELATORIOS ===");
        System.out.println("1. Comissao da Equipe");
        System.out.println("2. Inventario Critico");
        System.out.println("3. Faturamento de Pecas");
        System.out.println("0. Voltar");
        System.out.print("Opcao: ");
        return lerOpcao(sc);
    }

    public static int lerOpcao(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Digite um numero (0 para voltar): ");
            }
        }
    }

    public static boolean perguntarSeRepete(Scanner input, String mensagemErro) {
        System.out.println(mensagemErro);
        
        char opcao = ' ';
        do {
            System.out.print("Deseja tentar novamente? (S - Sim / N - Cancelar e voltar ao menu): ");
            String resposta = input.nextLine().trim().toUpperCase();
            opcao = resposta.isEmpty() ? ' ' : resposta.charAt(0);

            if (opcao == 'N') return false;
            if (opcao != 'S') System.out.println("[ERRO] Opção inválida! Digite apenas 'S' ou 'N'.");

        } while (opcao != 'S');
        return true;
    }
}
