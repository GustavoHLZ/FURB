/**
 * Questão 2 – Plano de Testes PL01
 * Valida o funcionamento da implementação estática de lista (ListaEstatica).
 * Também cobre os métodos extras adicionados à classe.
 *
 * Como executar:
 *   javac ListaEstatica.java TestePL01.java
 *   java TestePL01
 */
public class TestePL01 {

    // -------------------------------------------------------
    // Utilitários de asserção simples
    // -------------------------------------------------------
    private static int passou = 0;
    private static int falhou = 0;

    private static void verificar(String nomeCaso, boolean condicao) {
        if (condicao) {
            System.out.println("  [OK]   " + nomeCaso);
            passou++;
        } else {
            System.out.println("  [FALHA] " + nomeCaso);
            falhou++;
        }
    }

    private static void cabecalho(String titulo) {
        System.out.println("\n========================================");
        System.out.println(" " + titulo);
        System.out.println("========================================");
    }

    // -------------------------------------------------------
    // main
    // -------------------------------------------------------
    public static void main(String[] args) {

        // ===================================================
        //  CASOS DO ENUNCIADO (Casos 1 a 9)
        // ===================================================
        cabecalho("PL01 — Casos do enunciado");

        // Caso 1 — inserir + toString
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            verificar("Caso 1 – toString após inserções",
                      "5,10,15,20".equals(lista.toString()));
        }

        // Caso 2 — getTamanho
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            verificar("Caso 2 – getTamanho == 4",
                      lista.getTamanho() == 4);
        }

        // Caso 3 — buscar elemento existente
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            verificar("Caso 3 – buscar(15) == 2",
                      lista.buscar(15) == 2);
        }

        // Caso 4 — buscar elemento inexistente
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            verificar("Caso 4 – buscar(30) == -1",
                      lista.buscar(30) == -1);
        }

        // Caso 5 — retirar
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            lista.retirar(10);
            verificar("Caso 5a – toString após retirar(10)",
                      "5,15,20".equals(lista.toString()));
            verificar("Caso 5b – getTamanho == 3 após retirar",
                      lista.getTamanho() == 3);
        }

        // Caso 6 — redimensionamento automático (15 elementos)
        {
            ListaEstatica lista = new ListaEstatica();
            for (int i = 1; i <= 15; i++) lista.inserir(i);
            verificar("Caso 6a – toString com 15 elementos",
                      "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15".equals(lista.toString()));
            verificar("Caso 6b – getTamanho == 15",
                      lista.getTamanho() == 15);
        }

        // Caso 7 — obterElemento posição válida
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            verificar("Caso 7 – obterElemento(3) == 20",
                      lista.obterElemento(3) == 20);
        }

        // Caso 8 — obterElemento lança exceção
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            boolean excecaoLancada = false;
            try {
                lista.obterElemento(5);
            } catch (IndexOutOfBoundsException e) {
                excecaoLancada = true;
            }
            verificar("Caso 8 – obterElemento(5) lança IndexOutOfBoundsException",
                      excecaoLancada);
        }

        // Caso 9 — liberar + estaVazia
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(10);
            lista.inserir(15);
            lista.inserir(20);
            lista.liberar();
            verificar("Caso 9 – estaVazia() após liberar()",
                      lista.estaVazia());
        }

        // ===================================================
        //  TESTES DOS MÉTODOS EXTRAS
        // ===================================================
        cabecalho("Métodos Extras");

        // getCapacidade — deve crescer de 10 para 20 após 11ª inserção
        {
            ListaEstatica lista = new ListaEstatica();
            verificar("Extra – capacidade inicial == 10",
                      lista.getCapacidade() == 10);
            for (int i = 0; i < 11; i++) lista.inserir(i);
            verificar("Extra – capacidade após 11 inserções == 20",
                      lista.getCapacidade() == 20);
        }

        // inserirNaPosicao
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(1);
            lista.inserir(3);
            lista.inserir(4);
            lista.inserirNaPosicao(1, 2);   // insere 2 na posição 1
            verificar("Extra – inserirNaPosicao(1, 2)",
                      "1,2,3,4".equals(lista.toString()));
        }

        // inserirNaPosicao — exceção
        {
            ListaEstatica lista = new ListaEstatica();
            boolean excecao = false;
            try {
                lista.inserirNaPosicao(5, 99);
            } catch (IndexOutOfBoundsException e) {
                excecao = true;
            }
            verificar("Extra – inserirNaPosicao posição inválida lança exceção",
                      excecao);
        }

        // retirarDaPosicao
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(10);
            lista.inserir(20);
            lista.inserir(30);
            lista.retirarDaPosicao(1);  // remove 20
            verificar("Extra – retirarDaPosicao(1)",
                      "10,30".equals(lista.toString()));
        }

        // substituir
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(1);
            lista.inserir(99);
            lista.inserir(3);
            lista.substituir(1, 2);
            verificar("Extra – substituir(1, 2)",
                      "1,2,3".equals(lista.toString()));
        }

        // getMaior / getMenor
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(1);
            lista.inserir(8);
            lista.inserir(3);
            verificar("Extra – getMaior() == 8", lista.getMaior() == 8);
            verificar("Extra – getMenor() == 1", lista.getMenor() == 1);
        }

        // getMaior — exceção em lista vazia
        {
            ListaEstatica lista = new ListaEstatica();
            boolean excecao = false;
            try {
                lista.getMaior();
            } catch (IllegalStateException e) {
                excecao = true;
            }
            verificar("Extra – getMaior() em lista vazia lança exceção", excecao);
        }

        // inverter
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(1);
            lista.inserir(2);
            lista.inserir(3);
            lista.inverter();
            verificar("Extra – inverter()",
                      "3,2,1".equals(lista.toString()));
        }

        // contarOcorrencias
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(5);
            lista.inserir(3);
            lista.inserir(5);
            lista.inserir(5);
            verificar("Extra – contarOcorrencias(5) == 3",
                      lista.contarOcorrencias(5) == 3);
            verificar("Extra – contarOcorrencias(9) == 0",
                      lista.contarOcorrencias(9) == 0);
        }

        // ordenar
        {
            ListaEstatica lista = new ListaEstatica();
            lista.inserir(4);
            lista.inserir(1);
            lista.inserir(3);
            lista.inserir(2);
            lista.ordenar();
            verificar("Extra – ordenar() Bubble Sort",
                      "1,2,3,4".equals(lista.toString()));
        }

        // copiar — independência de cópias
        {
            ListaEstatica original = new ListaEstatica();
            original.inserir(10);
            original.inserir(20);
            ListaEstatica copia = original.copiar();
            copia.inserir(30);                              // altera só a cópia
            verificar("Extra – copiar() — cópia independente",
                      original.getTamanho() == 2 && copia.getTamanho() == 3);
        }

        // ===================================================
        //  Resumo
        // ===================================================
        System.out.println("\n========================================");
        System.out.printf(  " Resultado: %d OK  |  %d FALHA(S)%n", passou, falhou);
        System.out.println("========================================\n");

        if (falhou > 0) System.exit(1);
    }
}