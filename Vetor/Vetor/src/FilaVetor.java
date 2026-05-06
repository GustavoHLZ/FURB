public class FilaVetor<T> implements Fila<T> {
    private T[] info;
    private int limite;
    private int tamanho;
    private int inicio;

    public FilaVetor(int limite) {
        // alínea a: inicializa com a capacidade fornecida [cite: 120]
        this.info = (T[]) new Object[limite];
        this.limite = limite;
        this.tamanho = 0;
        this.inicio = 0;
    }

    @Override
    public void inserir(T valor) {
        // alínea b: se não houver espaço, lança FilaCheiaException [cite: 122]
        if (tamanho == limite) {
            throw new FilaCheiaException();
        }

        // Lógica de Fila Circular: calcula a posição de inserção
        int posicaoInserir = (inicio + tamanho) % limite;
        info[posicaoInserir] = valor;
        tamanho++;
    }

    @Override
    public boolean estaVazia() {
        // alínea e: true se vazia, false se não [cite: 124]
        return tamanho == 0;
    }

    @Override
    public T peek() {
        // alínea c: retorna o dado do início. Se vazia, lança exceção [cite: 126, 127]
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        return info[inicio];
    }

    @Override
    public T retirar() {
        // alínea d: desenfileira o dado. Se vazia, lança exceção [cite: 128, 129]
        T valor = peek();

        info[inicio] = null; // Opcional: limpa a referência
        inicio = (inicio + 1) % limite; // Move o início (Circular)
        tamanho--;

        return valor;
    }

    @Override
    public void liberar() {
        // alínea f: desenfileira todos os dados [cite: 131]
        this.info = (T[]) new Object[limite];
        this.tamanho = 0;
        this.inicio = 0;
    }

    public FilaVetor<T> criarFilaConcatenada(FilaVetor<T> f2) {
        // alínea h: nova fila com a soma dos limites [cite: 132, 157]
        FilaVetor<T> f3 = new FilaVetor<>(this.limite + f2.getLimite());

        // Copia elementos da primeira fila (f1)
        int tempInicio1 = this.inicio;
        for (int i = 0; i < this.tamanho; i++) {
            f3.inserir(this.info[(tempInicio1 + i) % this.limite]);
        }

        // Copia elementos da segunda fila (f2)
        int tempInicio2 = f2.inicio;
        for (int i = 0; i < f2.tamanho; i++) {
            f3.inserir(f2.info[(tempInicio2 + i) % f2.limite]);
        }

        return f3;
    }

    @Override
    public String toString() {
        // alínea g: do início ao fim, separados por vírgula [cite: 162]
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            int pos = (inicio + i) % limite;
            s.append(info[pos]);
            if (i < tamanho - 1)
                s.append(",");
        }
        return s.toString();
    }

    public int getLimite() {
        return limite; // [cite: 164]
    }
}