# Auto Center Rota 381 – Sistema de Gestão
**Disciplina:** Desenvolvimento de Programas Estruturados e Modularização  
**Professor:** Raffael Carvalho | UNIVAS

## Como compilar e executar
```bash
cd src
javac *.java
java Main
```

## Estrutura dos arquivos
| Arquivo | Responsabilidade |
|---|---|
| `Structs.java` | Tipos de dados: Mecanico, Veiculo, Peca, OrdemDeServico |
| `Constantes.java` | Tamanhos dos vetores e nomes dos arquivos CSV |
| `Busca.java` | Funções de busca nos vetores (retornam índice ou -1) |
| `Persistencia.java` | Salvar/carregar vetores em arquivos .csv |
| `Relatorios.java` | Os 3 relatórios: Comissão, Inventário Crítico, Faturamento |
| `Main.java` | Menus, lógica principal, vetores globais |

## Arquivos CSV gerados (na mesma pasta do executável)
- `mecanicos.csv` — codigo;nome;especialidade
- `veiculos.csv` — placa;nomeDono;modelo
- `pecas.csv` — codigo;descricao;quantidade;preco
- `ordens.csv` — numero;placa;codMecanico;codPeca;qtdUsada;maoDeObra

## Regras técnicas respeitadas
- ✅ Lógica estruturada apenas (if / for / while)
- ✅ Vetores de tamanho fixo: 50 mecânicos / 100 veículos / 200 peças / 500 OS
- ✅ Persistência via arquivos .csv (BufferedReader / BufferedWriter)
- ❌ Sem OO (sem métodos dentro de structs)
- ❌ Sem ArrayList / List
- ❌ Sem banco de dados

## Regras de negócio implementadas
- OS só abre se placa E mecânico estiverem cadastrados
- OS bloqueia se a peça não tiver estoque suficiente
- Ao abrir OS, o estoque é descontado automaticamente
- Dados são salvos no arquivo imediatamente após cada operação
