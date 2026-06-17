import modelo.Mecanico;
import modelo.Peca;
import modelo.OrdemDeServico;

public class Relatorio {

    public static void comissaoEquipe(Mecanico[] mecanicos, int totalMecanicos,
                                      OrdemDeServico[] ordens, int totalOrdens) {

        System.out.println("\n=== RELATORIO A - COMISSAO DA EQUIPE ===");

        if (totalMecanicos == 0) {
            System.out.println("Nenhum mecanico cadastrado.");
            return;
        }

        System.out.printf("%-6s %-25s %-15s%n", "Cod", "Nome", "Total Mao de Obra");
        System.out.println("-".repeat(50));

        for (int i = 0; i < totalMecanicos; i++) {
            double totalMaoDeObra = 0;
            for (int j = 0; j < totalOrdens; j++) {
                if (ordens[j].codigoMecanico == mecanicos[i].codigo) {
                    totalMaoDeObra += ordens[j].valorMaoDeObra;
                }
            }
            System.out.printf("%-6d %-25s R$ %,.2f%n",
                mecanicos[i].codigo, mecanicos[i].nome, totalMaoDeObra);
        }
    }

    public static void inventarioCritico(Peca[] pecas, int totalPecas) {

        System.out.println("\n=== RELATORIO B - INVENTARIO CRITICO ===");

        if (totalPecas == 0) {
            System.out.println("Nenhuma peca cadastrada.");
            return;
        }

        System.out.printf("%-6s %-30s%n", "Cod", "Descricao");
        System.out.println("-".repeat(38));

        boolean encontrou = false;
        for (int i = 0; i < totalPecas; i++) {
            if (pecas[i].quantidadeEstoque == 0) {
                System.out.printf("%-6d %-30s%n", pecas[i].codigo, pecas[i].descricao);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma peca com estoque zerado.");
        }
    }

    public static void faturamentoPecas(OrdemDeServico[] ordens, int totalOrdens,
                                        Peca[] pecas, int totalPecas) {

        System.out.println("\n=== RELATORIO C - FATURAMENTO DE PECAS ===");

        if (totalOrdens == 0) {
            System.out.println("Nenhuma ordem de servico registrada.");
            return;
        }

        System.out.printf("%-6s %-30s %-6s %-12s %-12s%n",
            "OS", "Peca", "Qtd", "Preco Unit.", "Total");
        System.out.println("-".repeat(70));

        double totalGeral = 0;
        for (int i = 0; i < totalOrdens; i++) {
            for (int j = 0; j < totalPecas; j++) {
                if (pecas[j].codigo == ordens[i].codigoPeca) {
                    double totalOs = ordens[i].quantidadePecaUsada * pecas[j].precoUnitario;
                    totalGeral += totalOs;
                    System.out.printf("%-6d %-30s %-6d R$ %,-9.2f R$ %,.2f%n",
                        ordens[i].numero, pecas[j].descricao,
                        ordens[i].quantidadePecaUsada, pecas[j].precoUnitario, totalOs);
                    break;
                }
            }
        }

        System.out.println("-".repeat(70));
        System.out.printf("%-55s R$ %,.2f%n", "TOTAL GERAL:", totalGeral);
    }
}
