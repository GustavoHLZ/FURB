/**
 * Lista Encadeada Simples (Generica).
 *
 * Conceito: Cada no aponta APENAS para o proximo. Sentido unico.
 *  primeiro -> [info|prox] -> [info|prox] -> [info|null]
 *
 * Insercao no INICIO (padrao da Lista 03 - O(1)).
 */
public class ListaEncadeada<T> {
    private NoLista<T> primeiro;

    /**
     * Construtor: define lista vazia.
     */
    public ListaEncadeada() {
        this.primeiro = null;
    }

    /**
     * Getter da variavel primeiro.
     */
    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    /**
     * Insere um novo no no INICIO da lista.
     * Complexidade: O(1) - sem percorrer a lista.
     */
    public void inserir(T info) {
        NoLista<T> novo = new NoLista<>(info);
        novo.setProximo(primeiro); // novo aponta para o que era o primeiro
        primeiro = novo;            // primeiro agora eh o novo
    }

    /**
     * Verifica se a lista esta vazia.
     * Complexidade: O(1).
     */
    public boolean estaVazia() {
        return primeiro == null;
    }

    /**
     * Busca um no que contenha o dado informado.
     * Retorna o no ou null se nao encontrar.
     * Complexidade: O(n).
     */
    public NoLista<T> buscar(T info) {
        NoLista<T> atual = primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(info)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    /**
     * Remove o primeiro no que contiver o dado informado.
     * Complexidade: O(n).
     */
    public void retirar(T info) {
        if (primeiro == null) return; // lista vazia

        // Caso 1: o primeiro no eh o que queremos remover
        if (primeiro.getInfo().equals(info)) {
            primeiro = primeiro.getProximo();
            return;
        }

        // Caso 2: percorrer mantendo referencia do anterior
        NoLista<T> anterior = primeiro;
        NoLista<T> atual = primeiro.getProximo();
        while (atual != null) {
            if (atual.getInfo().equals(info)) {
                anterior.setProximo(atual.getProximo()); // pula o no removido
                return;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
    }

    /**
     * Retorna a quantidade de nos da lista.
     * Sem usar variavel de instancia (atributo) - percorre e conta.
     * Complexidade: O(n).
     */
    public int obterComprimento() {
        int qtd = 0;
        NoLista<T> atual = primeiro;
        while (atual != null) {
            qtd++;
            atual = atual.getProximo();
        }
        return qtd;
    }

    /**
     * Retorna o no na posicao informada (0 = primeiro).
     * Lanca IndexOutOfBoundsException se idx for invalido.
     * Percorre a lista no maximo uma vez.
     * Complexidade: O(n).
     */
    public NoLista<T> obterNo(int idx) {
        if (idx < 0) {
            throw new IndexOutOfBoundsException("Indice negativo: " + idx);
        }
        NoLista<T> atual = primeiro;
        int posAtual = 0;
        while (atual != null) {
            if (posAtual == idx) {
                return atual;
            }
            atual = atual.getProximo();
            posAtual++;
        }
        // Se saiu do laco, idx eh maior que comprimento - 1
        throw new IndexOutOfBoundsException("Indice fora do tamanho: " + idx);
    }

    /**
     * OPERACAO ESPECIFICA da Lista Encadeada Simples:
     * Inverte a ordem dos elementos da lista.
     * Demonstra manipulacao classica de 3 ponteiros simultaneos.
     * Complexidade: O(n).
     */
    public void inverter() {
        NoLista<T> anterior = null;
        NoLista<T> atual = primeiro;
        NoLista<T> proximo;
        while (atual != null) {
            proximo = atual.getProximo(); // guarda o proximo
            atual.setProximo(anterior);   // inverte a seta
            anterior = atual;             // anterior avanca
            atual = proximo;              // atual avanca
        }
        primeiro = anterior; // o que era ultimo virou primeiro
    }

    /**
     * Retorna o conteudo da lista separado por virgula.
     */
    @Override
    public String toString() {
        if (primeiro == null) return "(lista vazia)";
        StringBuilder sb = new StringBuilder();
        NoLista<T> atual = primeiro;
        while (atual != null) {
            sb.append(atual.getInfo());
            if (atual.getProximo() != null) sb.append(", ");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    /**
     * Imprime visualmente a lista, mostrando as ligacoes (setas ->).
     * Atende ao requisito do trabalho de "interface visual com ligacoes".
     */
    public void imprimirVisual() {
        System.out.println("\n=== LISTA ENCADEADA SIMPLES ===");
        if (primeiro == null) {
            System.out.println("(vazia)");
            return;
        }
        System.out.print("primeiro -> ");
        NoLista<T> atual = primeiro;
        while (atual != null) {
            System.out.print("[" + atual.getInfo() + "]");
            if (atual.getProximo() != null) {
                System.out.print(" -> ");
            } else {
                System.out.print(" -> null");
            }
            atual = atual.getProximo();
        }
        System.out.println("\nComprimento: " + obterComprimento());
    }
}
