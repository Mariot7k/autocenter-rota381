package cadastro;

import modelo.Veiculo;

import java.util.Scanner;

import Menu;

public class CadastroVeiculo {

    public static boolean cadastrar(Scanner sc, Veiculo[] veiculos, int totalVeiculos, int maxVeiculos) {

        if (totalVeiculos >= maxVeiculos) {
            System.out.println("Limite de veiculos atingido.");
            return false;
        }

        System.out.println("\n=== CADASTRO DE VEICULO ===");

        String placa;
        while (true) {
            System.out.print("Placa: ");
            placa = sc.nextLine().trim().toUpperCase();

            boolean duplicado = false;
            for (int i = 0; i < totalVeiculos; i++) {
                if (veiculos[i].placa.equals(placa)) {
                    duplicado = true;
                    break;
                }
            }

            if (duplicado) {
                if (!Menu.perguntarSeRepete(sc, "Placa " + placa + " ja cadastrada.")) {
                    return false;
                }
            } else {
                break;
            }
        }

        System.out.print("Nome do dono: ");
        String nomeDono = sc.nextLine().trim().toLowerCase();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine().trim().toLowerCase();

        veiculos[totalVeiculos] = new Veiculo();
        veiculos[totalVeiculos].placa    = placa;
        veiculos[totalVeiculos].nomeDono = nomeDono;
        veiculos[totalVeiculos].modelo   = modelo;

        System.out.println("Veiculo cadastrado com sucesso.");
        return true;
    }
}
