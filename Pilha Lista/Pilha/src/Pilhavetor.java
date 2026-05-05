public class Pilhavetor<T> implements Pilha<T> {

    private T[] info;
    private int limite;
    private int tamanho;

    public Pilhavetor(int limite) {
        this.info = (T[]) new Object[limite];
        this.limite = limite;
        this.tamanho = 0;
    }

    @Override
    public void push(T info) {
        if (tamanho == limite) {
            throw new PilhaCheiaException();
        }
        this.info[tamanho] = info;
        tamanho++;
    }

    @Override
    public boolean estaVazia() {
        return (tamanho == 0);
    }

    @Override
    public T pop() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        T valor = info[tamanho - 1];
        info[tamanho - 1] = null; // Opcional, para ajudar o GC
        tamanho--;
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        return info[tamanho - 1];
        
    }

    @Override
    public void liberar() {
        tamanho = 0;
        // Opcional: info = (T[]) new Object[limite];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = tamanho - 1; i >= 0; i--) {
            sb.append(info[i]);
            if (i > 0) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void concatenar(Pilhavetor<T> p) {
        if (this.tamanho + p.tamanho > this.limite) {
            throw new PilhaCheiaException();
        }

        for (int i = 0; i < p.tamanho; i++) {
            this.push(p.info[i]);
        }
    }
}
