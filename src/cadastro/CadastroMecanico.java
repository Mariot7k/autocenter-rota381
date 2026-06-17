package cadastro;

import modelo.Mecanico;

import java.util.Scanner;

import Menu;

public class CadastroMecanico {

    public static boolean cadastrar(Scanner sc, Mecanico[] mecanicos, int totalMecanicos, int maxMecanicos) {

        if (totalMecanicos >= maxMecanicos) {
            System.out.println("Limite de mecanicos atingido.");
            return false;
        }

        System.out.println("\n=== CADASTRO DE MECANICO ===");

        int codigo;
        while (true) {
            System.out.print("Codigo (0 para voltar): ");
            codigo = Menu.lerOpcao(sc);
            if (codigo == 0) return false;

            boolean duplicado = false;
            for (int i = 0; i < totalMecanicos; i++) {
                if (mecanicos[i].codigo == codigo) {
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

        System.out.print("Nome: ");
        String nome = sc.nextLine().trim().toLowerCase();

        System.out.print("Especialidade: ");
        String especialidade = sc.nextLine().trim().toLowerCase();

        mecanicos[totalMecanicos] = new Mecanico();
        mecanicos[totalMecanicos].codigo        = codigo;
        mecanicos[totalMecanicos].nome          = nome;
        mecanicos[totalMecanicos].especialidade = especialidade;

        System.out.println("Mecanico cadastrado com sucesso.");
        return true;
    }
}
