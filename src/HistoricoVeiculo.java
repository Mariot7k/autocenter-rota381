import modelo.Mecanico;
import modelo.Peca;
import modelo.OrdemDeServico;
import modelo.Veiculo;
import menu.Menu;
import java.util.Scanner;

public class HistoricoVeiculo {

    public static void exibir(Scanner sc, Veiculo[] veiculos, int totalVeiculos,
                                          OrdemDeServico[] ordens, int totalOrdens,
                                          Mecanico[] mecanicos, int totalMecanicos,
                                          Peca[] pecas, int totalPecas) {

        System.out.println("\n=== HISTORICO DE SERVICOS POR VEICULO ===");

        String placa;
        int idxVeiculo;
        while (true) {
            System.out.print("Placa do veiculo: ");
            placa = sc.nextLine().trim().toUpperCase();

            idxVeiculo = -1;
            for (int i = 0; i < totalVeiculos; i++) {
                if (veiculos[i].placa.equals(placa)) {
                    idxVeiculo = i;
                    break;
                }
            }

            if (idxVeiculo == -1) {
                if (!Menu.perguntarSeRepete(sc, "Placa " + placa + " nao cadastrada.")) {
                    return;
                }
            } else {
                break;
            }
        }

        System.out.println("\nVeiculo : " + veiculos[idxVeiculo].placa + " - " + veiculos[idxVeiculo].modelo);
        System.out.println("Dono    : " + veiculos[idxVeiculo].nomeDono);
        System.out.println("-".repeat(75));

        boolean encontrou = false;
        double totalGeralMaoDeObra = 0;
        double totalGeralPecas     = 0;

        for (int i = 0; i < totalOrdens; i++) {
            if (ordens[i].placaVeiculo.equals(placa)) {

                // buscar mecanico
                String nomeMecanico = "-";
                for (int j = 0; j < totalMecanicos; j++) {
                    if (mecanicos[j].codigo == ordens[i].codigoMecanico) {
                        nomeMecanico = mecanicos[j].nome;
                        break;
                    }
                }

                // buscar peca
                String descricaoPeca = "-";
                double totalPecasOs  = 0;
                for (int j = 0; j < totalPecas; j++) {
                    if (pecas[j].codigo == ordens[i].codigoPeca) {
                        descricaoPeca = pecas[j].descricao;
                        totalPecasOs  = ordens[i].quantidadePecaUsada * pecas[j].precoUnitario;
                        break;
                    }
                }

                double totalOs = ordens[i].valorMaoDeObra + totalPecasOs;
                totalGeralMaoDeObra += ordens[i].valorMaoDeObra;
                totalGeralPecas     += totalPecasOs;

                System.out.printf("OS %-5d | Mecanico: %-20s | Peca: %-25s | Qtd: %-3d%n",
                    ordens[i].numero, nomeMecanico, descricaoPeca, ordens[i].quantidadePecaUsada);
                System.out.printf("         | Mao de obra: R$ %,-10.2f | Pecas: R$ %,-10.2f | Total OS: R$ %,.2f%n",
                    ordens[i].valorMaoDeObra, totalPecasOs, totalOs);
                System.out.println("-".repeat(75));

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma ordem de servico encontrada para esta placa.");
            return;
        }

        System.out.printf("%-45s R$ %,.2f%n", "Total geral mao de obra:", totalGeralMaoDeObra);
        System.out.printf("%-45s R$ %,.2f%n", "Total geral pecas:", totalGeralPecas);
        System.out.printf("%-45s R$ %,.2f%n", "TOTAL GERAL:", totalGeralMaoDeObra + totalGeralPecas);
    }
}
