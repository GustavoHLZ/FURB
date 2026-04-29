/**
 * Lista Circular (Generica) - segue o mesmo estilo das Listas 03 e 04.
 *
 * Conceito: O ultimo no aponta de volta para o primeiro, formando um CICLO.
 *
 * primeiro -> [info|prox] -> [info|prox] -> [info|prox] -+
 * ^ |
 * +-----------------------------------------+
 *
 * Insercao no INICIO (consistente com o padrao da professora).
 */
public class ListaCircular<T> {
    private NoListaCircular<T> primeiro;
    private NoListaCircular<T> atual; // ponteiro do "elemento da vez" (rodizio)

    /**
     * Construtor: define lista vazia.
     */
    public ListaCircular() {
        this.primeiro = null;
        this.atual = null;
    }

    /**
     * Getter da variavel primeiro.
     */
    public NoListaCircular<T> getPrimeiro() {
        return primeiro;
    }

    /**
     * Insere um novo no na lista, mantendo a estrutura circular
     * (o ultimo no sempre aponta para o primeiro).
     * Complexidade: O(n) - precisa achar o ultimo para manter o ciclo.
     */
    public void inserir(T info) {
        NoListaCircular<T> novo = new NoListaCircular<>(info);
        if (primeiro == null) {
            // Primeiro no: aponta para si mesmo (ciclo de 1 elemento)
            primeiro = novo;
            novo.setProximo(primeiro);
            atual = primeiro;
        } else {
            // Acha o ultimo (aquele cujo proximo eh o primeiro)
            NoListaCircular<T> ultimo = primeiro;
            while (ultimo.getProximo() != primeiro) {
                ultimo = ultimo.getProximo();
            }
            // Insere o novo apos o ultimo, fechando o ciclo
            ultimo.setProximo(novo);
            novo.setProximo(primeiro);
        }
    }

    /**
     * Verifica se a lista esta vazia.
     */
    public boolean estaVazia() {
        return primeiro == null;
    }

    /**
     * Busca um no que contenha o dado informado.
     * Usa do-while porque a condicao "voltei ao inicio" so faz sentido
     * apos visitar pelo menos um no.
     * Complexidade: O(n).
     */
    public NoListaCircular<T> buscar(T info) {
        if (primeiro == null)
            return null;
        NoListaCircular<T> p = primeiro;
        do {
            if (p.getInfo().equals(info)) {
                return p;
            }
            p = p.getProximo();
        } while (p != primeiro);
        return null;
    }

    /**
     * Remove o primeiro no que contiver o valor informado,
     * preservando a circularidade da lista.
     * Complexidade: O(n).
     */
    public void retirar(T info) {
        if (primeiro == null)
            return;

        // Caso 1: o no a remover eh o primeiro
        if (primeiro.getInfo().equals(info)) {
            if (primeiro.getProximo() == primeiro) {
                // Soh tinha 1 elemento na lista
                primeiro = null;
                atual = null;
            } else {
                // Acha o ultimo para reconectar o ciclo
                NoListaCircular<T> ultimo = primeiro;
                while (ultimo.getProximo() != primeiro) {
                    ultimo = ultimo.getProximo();
                }
                if (atual == primeiro)
                    atual = primeiro.getProximo();
                primeiro = primeiro.getProximo();
                ultimo.setProximo(primeiro);
            }
            return;
        }

        // Caso 2: percorrer mantendo referencia do anterior
        NoListaCircular<T> anterior = primeiro;
        NoListaCircular<T> p = primeiro.getProximo();
        while (p != primeiro) {
            if (p.getInfo().equals(info)) {
                anterior.setProximo(p.getProximo());
                if (atual == p)
                    atual = p.getProximo();
                return;
            }
            anterior = p;
            p = p.getProximo();
        }
    }

    /**
     * Retorna a quantidade de nos da lista.
     * Complexidade: O(n).
     */
    public int obterComprimento() {
        if (primeiro == null)
            return 0;
        int qtd = 0;
        NoListaCircular<T> p = primeiro;
        do {
            qtd++;
            p = p.getProximo();
        } while (p != primeiro);
        return qtd;
    }

    /**
     * OPERACAO ESPECIFICA da Lista Circular:
     * Avanca o ponteiro "atual" para o proximo elemento.
     * Aproveita a circularidade: nao precisa verificar fim de lista.
     * Complexidade: O(1).
     */
    public T proximoDoRodizio() {
        if (atual == null)
            return null;
        T info = atual.getInfo();
        atual = atual.getProximo(); // avanca; volta ao primeiro automaticamente
        return info;
    }

    /**
     * Retorna o conteudo da lista, do primeiro ate o ultimo,
     * separado por virgula.
     */
    @Override
    public String toString() {
        if (primeiro == null)
            return "(lista vazia)";
        StringBuilder sb = new StringBuilder();
        NoListaCircular<T> p = primeiro;
        do {
            sb.append(p.getInfo());
            p = p.getProximo();
            if (p != primeiro)
                sb.append(", ");
        } while (p != primeiro);
        return sb.toString();
    }

    /**
     * Imprime visualmente a lista, evidenciando a circularidade.
     * Marca com * o "elemento da vez" (atual).
     */
    public void imprimirVisual() {
        System.out.println("\n=== LISTA CIRCULAR ===");
        if (primeiro == null) {
            System.out.println("(vazia)");
            return;
        }
        System.out.print("primeiro -> ");
        NoListaCircular<T> p = primeiro;
        do {
            String marcador = (p == atual) ? "*" : "";
            System.out.print(marcador + "[" + p.getInfo() + "]" + marcador);
            p = p.getProximo();
            if (p == primeiro) {
                System.out.print(" -+");
            } else {
                System.out.print(" -> ");
            }
        } while (p != primeiro);
        System.out.println();
        System.out.println("            ^                                                |");
        System.out.println("            +------------------------------------------------+");
        System.out.println("(* = elemento da vez)  Comprimento: " + obterComprimento());
    }
}
