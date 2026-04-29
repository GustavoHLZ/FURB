/**
 * Lista Duplamente Encadeada (Generica) - segue o padrao da Lista 04.
 *
 * Conceito: Cada no aponta para o ANTERIOR e o PROXIMO. Permite percurso
 * nos dois sentidos.
 *
 *  null <- [ant|info|prox] <-> [ant|info|prox] <-> [ant|info|null]
 *           ^primeiro
 *
 * Insercao no INICIO (padrao da professora).
 */
public class ListaDupla<T> {
    private NoListaDupla<T> primeiro;

    /**
     * Construtor: define lista vazia.
     */
    public ListaDupla() {
        this.primeiro = null;
    }

    /**
     * Getter da variavel primeiro.
     */
    public NoListaDupla<T> getPrimeiro() {
        return primeiro;
    }

    /**
     * Insere um novo no no INICIO da lista, mantendo as ligacoes
     * anterior e proximo consistentes.
     * Complexidade: O(1).
     */
    public void inserir(T info) {
        NoListaDupla<T> novo = new NoListaDupla<>(info);
        if (primeiro != null) {
            novo.setProximo(primeiro);     // novo aponta pra frente para o antigo primeiro
            primeiro.setAnterior(novo);    // antigo primeiro aponta pra tras para o novo
        }
        primeiro = novo;
    }

    /**
     * Busca um no que contenha o dado informado.
     * Retorna o no ou null se nao encontrar.
     * Complexidade: O(n).
     */
    public NoListaDupla<T> buscar(T info) {
        NoListaDupla<T> atual = primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(info)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    /**
     * Remove um no que contenha o valor informado.
     * Aproveita o ponteiro "anterior" para reconectar em O(1) apos achar.
     * Complexidade: O(n).
     */
    public void retirar(T info) {
        NoListaDupla<T> atual = buscar(info);
        if (atual == null) return; // nao encontrou

        // Reconecta o anterior do atual com o proximo do atual
        if (atual.getAnterior() != null) {
            atual.getAnterior().setProximo(atual.getProximo());
        } else {
            // O no a remover eh o primeiro
            primeiro = atual.getProximo();
        }

        // Reconecta o proximo do atual com o anterior do atual
        if (atual.getProximo() != null) {
            atual.getProximo().setAnterior(atual.getAnterior());
        }

        // Limpa as referencias do no removido (boa pratica)
        atual.setAnterior(null);
        atual.setProximo(null);
    }

    /**
     * Exibe o conteudo da lista do ULTIMO no ate o PRIMEIRO.
     * So eh possivel porque cada no conhece o anterior.
     * Complexidade: O(n).
     */
    public void exibirOrdemInversa() {
        if (primeiro == null) {
            System.out.println("(lista vazia)");
            return;
        }
        // 1. Percorre ate o ultimo no
        NoListaDupla<T> atual = primeiro;
        while (atual.getProximo() != null) {
            atual = atual.getProximo();
        }
        // 2. Volta usando o ponteiro anterior, imprimindo
        StringBuilder sb = new StringBuilder();
        while (atual != null) {
            sb.append(atual.getInfo());
            if (atual.getAnterior() != null) sb.append(", ");
            atual = atual.getAnterior();
        }
        System.out.println(sb.toString());
    }

    /**
     * Limpa a estrutura removendo todos os encadeamentos.
     * Em vez de simplesmente fazer primeiro = null, percorre
     * todos os nos e limpa anterior/proximo de cada um.
     * Complexidade: O(n).
     */
    public void liberar() {
        NoListaDupla<T> atual = primeiro;
        while (atual != null) {
            NoListaDupla<T> proximo = atual.getProximo();
            atual.setAnterior(null);
            atual.setProximo(null);
            atual = proximo;
        }
        primeiro = null;
    }

    /**
     * Retorna os valores armazenados, do primeiro ao ultimo,
     * separados por virgula.
     */
    @Override
    public String toString() {
        if (primeiro == null) return "(lista vazia)";
        StringBuilder sb = new StringBuilder();
        NoListaDupla<T> atual = primeiro;
        while (atual != null) {
            sb.append(atual.getInfo());
            if (atual.getProximo() != null) sb.append(", ");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    /**
     * Imprime visualmente a lista, mostrando os ponteiros duplos (<->).
     * Atende ao requisito do trabalho de "interface visual com ligacoes".
     */
    public void imprimirVisual() {
        System.out.println("\n=== LISTA DUPLAMENTE ENCADEADA ===");
        if (primeiro == null) {
            System.out.println("(vazia)");
            return;
        }
        System.out.print("null <- ");
        NoListaDupla<T> atual = primeiro;
        while (atual != null) {
            System.out.print("[" + atual.getInfo() + "]");
            if (atual.getProximo() != null) {
                System.out.print(" <-> ");
            } else {
                System.out.print(" -> null");
            }
            atual = atual.getProximo();
        }
        System.out.println();
    }
}
