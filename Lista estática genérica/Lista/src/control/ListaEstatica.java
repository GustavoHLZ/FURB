package control;

public class ListaEstatica<T> {

    private Object[] info;
    private int tamanho;

    private static final int TAMANHO_INICIAL = 10;
    private static final int INCREMENTO = 10;

    // Construtor
    public ListaEstatica() {
        info = new Object[TAMANHO_INICIAL];
        tamanho = 0;
    }

    // Redimensiona o vetor interno quando necessário
    private void redimensionar() {
        Object[] novoVetor = new Object[info.length + INCREMENTO];
        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = info[i];
        }
        info = novoVetor;
    }

    // Insere um elemento no final da lista
    public void inserir(T valor) {
        if (tamanho == info.length) {
            redimensionar();
        }
        info[tamanho] = valor;
        tamanho++;
    }

    // Exibe todos os elementos da lista
    public void exibir() {
        System.out.print("[");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(info[i]);
            if (i < tamanho - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Busca um elemento e retorna sua posição (-1 se não encontrado)
    public int buscar(T valor) {
        for (int i = 0; i < tamanho; i++) {
            if (info[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    // Remove a primeira ocorrência do valor
    public void retirar(T valor) {
        int pos = buscar(valor);
        if (pos == -1) {
            throw new IndexOutOfBoundsException("Elemento não encontrado: " + valor);
        }
        for (int i = pos; i < tamanho - 1; i++) {
            info[i] = info[i + 1];
        }
        info[tamanho - 1] = null;
        tamanho--;
    }

    // Libera todos os elementos da lista
    public void liberar() {
        for (int i = 0; i < tamanho; i++) {
            info[i] = null;
        }
        tamanho = 0;
    }

    // Obtém o elemento em uma determinada posição
    @SuppressWarnings("unchecked")
    public T obterElemento(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        return (T) info[posicao];
    }

    // Verifica se a lista está vazia
    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Retorna o número de elementos na lista
    public int getTamanho() {
        return tamanho;
    }

    // Retorna representação textual da lista
    @Override
    public String toString() {
        if (tamanho == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append(info[i]);
            if (i < tamanho - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    // Inverte a ordem dos elementos na lista (sem usar novos vetores ou coleções Java)
    public void inverter() {
        int inicio = 0;
        int fim = tamanho - 1;
        while (inicio < fim) {
            Object temp = info[inicio];
            info[inicio] = info[fim];
            info[fim] = temp;
            inicio++;
            fim--;
        }
    }
}
