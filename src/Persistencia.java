import modelo.Mecanico;
import modelo.Veiculo;
import modelo.Peca;
import modelo.OrdemDeServico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Persistencia {

    // ── INICIALIZAÇÃO ─────────────────────────────────────────

    public static boolean inicializarPasta(String pastaDados) {
        File pasta = new File(pastaDados);
        if (!pasta.exists()) {
            return pasta.mkdirs();
        }
        return true;
    }

    public static void inicializarArquivos(String pastaDados) {
        criarArquivoSeNaoExiste(pastaDados + "/mecanicos.csv",  "codigo;nome;especialidade");
        criarArquivoSeNaoExiste(pastaDados + "/veiculos.csv",   "placa;nomeDono;modelo");
        criarArquivoSeNaoExiste(pastaDados + "/pecas.csv",      "codigo;descricao;quantidadeEstoque;precoUnitario");
        criarArquivoSeNaoExiste(pastaDados + "/ordens.csv",     "numero;placaVeiculo;codigoMecanico;codigoPeca;quantidadePecaUsada;valorMaoDeObra");
    }

    private static void criarArquivoSeNaoExiste(String caminho, String cabecalho) {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
                writer.println(cabecalho);
            } catch (IOException e) {
                System.out.println("[ERRO] Nao foi possivel criar o arquivo " + caminho + ": " + e.getMessage());
            }
        }
    }

    // ── SALVAR ────────────────────────────────────────────────

    public static void salvarMecanicos(Mecanico[] mecanicos, int totalMecanicos, String pastaDados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pastaDados + "/mecanicos.csv", false))) {
            writer.println("codigo;nome;especialidade");
            for (int i = 0; i < totalMecanicos; i++) {
                writer.println(mecanicos[i].codigo + ";" + mecanicos[i].nome + ";" + mecanicos[i].especialidade);
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel salvar mecanicos: " + e.getMessage());
        }
    }

    public static void salvarVeiculos(Veiculo[] veiculos, int totalVeiculos, String pastaDados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pastaDados + "/veiculos.csv", false))) {
            writer.println("placa;nomeDono;modelo");
            for (int i = 0; i < totalVeiculos; i++) {
                writer.println(veiculos[i].placa + ";" + veiculos[i].nomeDono + ";" + veiculos[i].modelo);
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel salvar veiculos: " + e.getMessage());
        }
    }

    public static void salvarPecas(Peca[] pecas, int totalPecas, String pastaDados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pastaDados + "/pecas.csv", false))) {
            writer.println("codigo;descricao;quantidadeEstoque;precoUnitario");
            for (int i = 0; i < totalPecas; i++) {
                writer.println(pecas[i].codigo + ";" + pecas[i].descricao + ";" +
                               pecas[i].quantidadeEstoque + ";" + pecas[i].precoUnitario);
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel salvar pecas: " + e.getMessage());
        }
    }

    public static void salvarOrdens(OrdemDeServico[] ordens, int totalOrdens, String pastaDados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pastaDados + "/ordens.csv", false))) {
            writer.println("numero;placaVeiculo;codigoMecanico;codigoPeca;quantidadePecaUsada;valorMaoDeObra");
            for (int i = 0; i < totalOrdens; i++) {
                writer.println(ordens[i].numero + ";" + ordens[i].placaVeiculo + ";" +
                               ordens[i].codigoMecanico + ";" + ordens[i].codigoPeca + ";" +
                               ordens[i].quantidadePecaUsada + ";" + ordens[i].valorMaoDeObra);
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel salvar ordens: " + e.getMessage());
        }
    }

    // ── CARREGAR ──────────────────────────────────────────────

    public static int carregarMecanicos(Mecanico[] mecanicos, String pastaDados) {
        int total = 0;
        try (Scanner leitor = new Scanner(new File(pastaDados + "/mecanicos.csv"))) {
            if (leitor.hasNextLine()) leitor.nextLine(); // pular cabeçalho
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] partes = linha.split(";");
                mecanicos[total] = new Mecanico();
                mecanicos[total].codigo        = Integer.parseInt(partes[0]);
                mecanicos[total].nome          = partes[1];
                mecanicos[total].especialidade = partes[2];
                total++;
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel carregar mecanicos: " + e.getMessage());
        }
        return total;
    }

    public static int carregarVeiculos(Veiculo[] veiculos, String pastaDados) {
        int total = 0;
        try (Scanner leitor = new Scanner(new File(pastaDados + "/veiculos.csv"))) {
            if (leitor.hasNextLine()) leitor.nextLine(); // pular cabeçalho
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] partes = linha.split(";");
                veiculos[total] = new Veiculo();
                veiculos[total].placa    = partes[0];
                veiculos[total].nomeDono = partes[1];
                veiculos[total].modelo   = partes[2];
                total++;
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel carregar veiculos: " + e.getMessage());
        }
        return total;
    }

    public static int carregarPecas(Peca[] pecas, String pastaDados) {
        int total = 0;
        try (Scanner leitor = new Scanner(new File(pastaDados + "/pecas.csv"))) {
            if (leitor.hasNextLine()) leitor.nextLine(); // pular cabeçalho
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] partes = linha.split(";");
                pecas[total] = new Peca();
                pecas[total].codigo            = Integer.parseInt(partes[0]);
                pecas[total].descricao         = partes[1];
                pecas[total].quantidadeEstoque = Integer.parseInt(partes[2]);
                pecas[total].precoUnitario     = Double.parseDouble(partes[3]);
                total++;
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel carregar pecas: " + e.getMessage());
        }
        return total;
    }

    public static int carregarOrdens(OrdemDeServico[] ordens, String pastaDados) {
        int total = 0;
        try (Scanner leitor = new Scanner(new File(pastaDados + "/ordens.csv"))) {
            if (leitor.hasNextLine()) leitor.nextLine(); // pular cabeçalho
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] partes = linha.split(";");
                ordens[total] = new OrdemDeServico();
                ordens[total].numero              = Integer.parseInt(partes[0]);
                ordens[total].placaVeiculo        = partes[1];
                ordens[total].codigoMecanico      = Integer.parseInt(partes[2]);
                ordens[total].codigoPeca          = Integer.parseInt(partes[3]);
                ordens[total].quantidadePecaUsada = Integer.parseInt(partes[4]);
                ordens[total].valorMaoDeObra      = Double.parseDouble(partes[5]);
                total++;
            }
        } catch (IOException e) {
            System.out.println("[ERRO] Nao foi possivel carregar ordens: " + e.getMessage());
        }
        return total;
    }
}
