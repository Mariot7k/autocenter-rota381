import modelo.Mecanico;
import modelo.Veiculo;
import os.GerenciadorOS;
import modelo.Peca;
import modelo.OrdemDeServico;
import persistencia.Persistencia;
import menu.Menu;
import java.util.Scanner;

import cadastro.CadastroMecanico;
import cadastro.CadastroPeca;
import cadastro.CadastroVeiculo;

public class Main {

    public static void main(String[] args) {

        // configurações
        String pastaDados = "dados";
        int maxMecanicos  = 50;
        int maxVeiculos   = 100;
        int maxPecas      = 200;
        int maxOS         = 500;

        // inicializar pasta de dados
        if (!Persistencia.inicializarPasta(pastaDados)) {
            System.out.println("Erro ao criar a pasta de dados. Encerrando o sistema.");
            return;
        }

        // vetores
        Mecanico[]     mecanicos = new Mecanico[maxMecanicos];
        Veiculo[]       veiculos = new Veiculo[maxVeiculos];
        Peca[]             pecas = new Peca[maxPecas];
        OrdemDeServico[]  ordens = new OrdemDeServico[maxOS];

        // contadores
        int totalMecanicos = 0;
        int totalVeiculos  = 0;
        int totalPecas     = 0;
        int totalOrdens    = 0;

        Scanner sc = new Scanner(System.in);

        int opcao = -1;
        while (opcao != 0) {
            opcao = Menu.menuPrincipal(sc);
            switch (opcao) {
                case 1 -> {
                    switch (Menu.menuCadastro(sc)) {
                        case 1 -> { if (CadastroMecanico.cadastrar(sc, mecanicos, totalMecanicos, maxMecanicos)) {
                                        totalMecanicos++;
                                    } }
                        case 2 -> { if(CadastroVeiculo.cadastrar(sc, veiculos, totalVeiculos, maxVeiculos)) {
                                        totalVeiculos++;
                                    } }
                        case 3 -> { if(CadastroPeca.cadastrar(sc, pecas, totalPecas, maxPecas)) {
                                        totalPecas++;
                                    } }
                    }
                }
                case 2 -> {
                    switch (Menu.menuOS(sc)) {
                        case 1 -> { if(GerenciadorOS.abrir(sc,  mecanicos,  totalMecanicos,
                                                           veiculos, totalVeiculos,  pecas,
                                                           totalPecas, ordens, totalOrdens, maxOS)){
                                        totalOrdens++;
                                    } }
                        case 2 -> { GerenciadorOS.listar(ordens, totalOrdens, mecanicos,
                                                         totalMecanicos, veiculos, totalVeiculos,  
                                                         pecas, totalPecas); }
                    }
                }
                case 3 -> {
                    switch (Menu.menuRelatorio(sc)) {
                        case 1 -> { /* Relatorio.comissaoEquipe()    */ }
                        case 2 -> { /* Relatorio.inventarioCritico() */ }
                        case 3 -> { /* Relatorio.faturamentoPecas()  */ }
                    }
                }
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opcao invalida.");
            }
        }

        sc.close();
    }
}
