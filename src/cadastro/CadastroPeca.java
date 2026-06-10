package cadastro;

import modelo.Peca;
import menu.Menu;
import java.util.Scanner;

public class CadastroPeca {

    public static boolean cadastrar(Scanner sc, Peca[] pecas, int totalPecas, int maxPecas) {

        if (totalPecas >= maxPecas) {
            System.out.println("Limite de pecas atingido.");
            return false;
        }

        System.out.println("\n=== CADASTRO DE PECA ===");

        int codigo;
        while (true) {
            System.out.print("Codigo (0 para voltar): ");
            codigo = Menu.lerOpcao(sc);
            if (codigo == 0) return false;

            boolean duplicado = false;
            for (int i = 0; i < totalPecas; i++) {
                if (pecas[i].codigo == codigo) {
                    duplicado = true;
                    break;
                }
            }

            if (duplicado) {
                if (!Menu.perguntarSeRepete(sc, "Codigo " + codigo + " ja cadastrado.")) {
                    return false;
                }
            } else {
                break;
            }
        }

        System.out.print("Descricao: ");
        String descricao = sc.nextLine().trim().toLowerCase();

        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque = Menu.lerOpcao(sc);

        System.out.print("Preco unitario: ");
        double precoUnitario;
        while (true) {
            try {
                precoUnitario = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                break;
            } catch (NumberFormatException e) {
                if (!Menu.perguntarSeRepete(sc, "Valor invalido.")) {
                    return false;
                }
                System.out.print("Preco unitario: ");
            }
        }

        pecas[totalPecas] = new Peca();
        pecas[totalPecas].codigo            = codigo;
        pecas[totalPecas].descricao         = descricao;
        pecas[totalPecas].quantidadeEstoque = quantidadeEstoque;
        pecas[totalPecas].precoUnitario     = precoUnitario;

        System.out.println("Peca cadastrada com sucesso.");
        return true;
    }
}
