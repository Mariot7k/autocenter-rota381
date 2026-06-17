package os;

import modelo.Mecanico;
import modelo.Veiculo;
import modelo.Peca;
import modelo.OrdemDeServico;
import menu.Menu;
import java.util.Scanner;

public class GerenciadorOS {

    public static boolean abrir(Scanner sc, Mecanico[] mecanicos, int totalMecanicos,
                                            Veiculo[]  veiculos,  int totalVeiculos,
                                            Peca[]     pecas,     int totalPecas,
                                            OrdemDeServico[] ordens, int totalOrdens,
                                            int maxOS) {

        if (totalOrdens >= maxOS) {
            System.out.println("Limite de ordens de servico atingido.");
            return false;
        }

        System.out.println("\n=== ABERTURA DE ORDEM DE SERVICO ===");

        // numero da OS
        int numero;
        while (true) {
            System.out.print("Numero da OS (0 para voltar): ");
            numero = Menu.lerOpcao(sc);
            if (numero == 0) return false;

            boolean duplicado = false;
            for (int i = 0; i < totalOrdens; i++) {
                if (ordens[i].numero == numero) {
                    duplicado = true;
                    break;
                }
            }

            if (duplicado) {
                if (!Menu.perguntarSeRepete(sc, "OS " + numero + " ja existe.")) {
                    return false;
                }
            } else {
                break;
            }
        }

        // placa do veiculo
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
                    return false;
                }
            } else {
                break;
            }
        }

        // codigo do mecanico
        int codigoMecanico;
        int idxMecanico;
        while (true) {
            System.out.print("Codigo do mecanico: ");
            codigoMecanico = Menu.lerOpcao(sc);
            if (codigoMecanico == 0) return false;

            idxMecanico = -1;
            for (int i = 0; i < totalMecanicos; i++) {
                if (mecanicos[i].codigo == codigoMecanico) {
                    idxMecanico = i;
                    break;
                }
            }

            if (idxMecanico == -1) {
                if (!Menu.perguntarSeRepete(sc, "Mecanico " + codigoMecanico + " nao cadastrado.")) {
                    return false;
                }
            } else {
                break;
            }
        }

        // codigo da peca
        int codigoPeca;
        int idxPeca;
        while (true) {
            System.out.print("Codigo da peca: ");
            codigoPeca = Menu.lerOpcao(sc);
            if (codigoPeca == 0) return false;

            idxPeca = -1;
            for (int i = 0; i < totalPecas; i++) {
                if (pecas[i].codigo == codigoPeca) {
                    idxPeca = i;
                    break;
                }
            }

            if (idxPeca == -1) {
                if (!Menu.perguntarSeRepete(sc, "Peca " + codigoPeca + " nao cadastrada.")) {
                    return false;
                }
            } else {
                break;
            }
        }

        // quantidade da peca
        int quantidade;
        while (true) {
            System.out.print("Quantidade utilizada: ");
            quantidade = Menu.lerOpcao(sc);
            if (quantidade == 0) return false;

            if (quantidade > pecas[idxPeca].quantidadeEstoque) {
                if (!Menu.perguntarSeRepete(sc, "Estoque insuficiente. Disponivel: " + pecas[idxPeca].quantidadeEstoque + ".")) {
                    return false;
                }
            } else {
                break;
            }
        }

        // valor da mao de obra
        double valorMaoDeObra;
        while (true) {
            System.out.print("Valor da mao de obra: ");
            try {
                valorMaoDeObra = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                break;
            } catch (NumberFormatException e) {
                if (!Menu.perguntarSeRepete(sc, "Valor invalido.")) {
                    return false;
                }
            }
        }

        // descontar estoque
        pecas[idxPeca].quantidadeEstoque -= quantidade;

        // registrar OS
        ordens[totalOrdens] = new OrdemDeServico();
        ordens[totalOrdens].numero              = numero;
        ordens[totalOrdens].placaVeiculo        = placa;
        ordens[totalOrdens].codigoMecanico      = codigoMecanico;
        ordens[totalOrdens].codigoPeca          = codigoPeca;
        ordens[totalOrdens].quantidadePecaUsada = quantidade;
        ordens[totalOrdens].valorMaoDeObra      = valorMaoDeObra;

        System.out.println("Ordem de servico aberta com sucesso.");
        return true;
    }

    public static void listar(OrdemDeServico[] ordens, int totalOrdens,
                              Mecanico[] mecanicos, int totalMecanicos,
                              Veiculo[]  veiculos,  int totalVeiculos,
                              Peca[]     pecas,     int totalPecas) {
 
        if (totalOrdens == 0) {
            System.out.println("Nenhuma ordem de servico registrada.");
            return;
        }
 
        System.out.println("\n=== ORDENS DE SERVICO ===");
        System.out.printf("%-6s %-10s %-20s %-20s %-15s %-6s %-12s %-12s%n",
            "OS", "Placa", "Dono", "Mecanico", "Peca", "Qtd", "Mao de Obra", "Total Pecas");
        System.out.println("-".repeat(105));
 
        for (int i = 0; i < totalOrdens; i++) {
            OrdemDeServico os = ordens[i];
 
            // buscar nome do dono
            String nomeDono = "-";
            for (int j = 0; j < totalVeiculos; j++) {
                if (veiculos[j].placa.equals(os.placaVeiculo)) {
                    nomeDono = veiculos[j].nomeDono;
                    break;
                }
            }
 
            // buscar nome do mecanico
            String nomeMecanico = "-";
            for (int j = 0; j < totalMecanicos; j++) {
                if (mecanicos[j].codigo == os.codigoMecanico) {
                    nomeMecanico = mecanicos[j].nome;
                    break;
                }
            }
 
            // buscar descricao e preco da peca
            String descricaoPeca = "-";
            double valorTotalPecasOS = 0;
            for (int j = 0; j < totalPecas; j++) {
                if (pecas[j].codigo == os.codigoPeca) {
                    descricaoPeca = pecas[j].descricao;
                    valorTotalPecasOS   = os.quantidadePecaUsada * pecas[j].precoUnitario;
                    break;
                }
            }
 
            System.out.printf("%-6d %-10s %-20s %-20s %-15s %-6d R$%-10.2f R$%-10.2f%n",
                os.numero, os.placaVeiculo, nomeDono, nomeMecanico,
                descricaoPeca, os.quantidadePecaUsada, os.valorMaoDeObra, valorTotalPecasOS);
        }
    }
}

