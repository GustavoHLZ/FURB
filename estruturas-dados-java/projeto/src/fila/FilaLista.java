package fila;

/**
 * Implementação dinâmica de Fila usando lista simplesmente encadeada.
 * Mantém referência ao último nó para inserção em O(1).
 */
public class FilaLista<T> implements Fila<T> {
    private NoLista<T> primeiro;
    private NoLista<T> ultimo;

    public FilaLista() {
        this.primeiro = null;
        this.ultimo = null;
    }

    @Override
    public void inserir(T valor) {
        NoLista<T> novo = new NoLista<>(valor);
        if (estaVazia()) {
            primeiro = novo;
        } else {
            ultimo.setProximo(novo);
        }
        ultimo = novo;
    }

    @Override
    public boolean estaVazia() {
        return primeiro == null;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        return primeiro.getInfo();
    }

    @Override
    public T retirar() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        T valor = primeiro.getInfo();
        primeiro = primeiro.getProximo();
        if (primeiro == null) {
            ultimo = null; // fila ficou vazia
        }
        return valor;
    }

    @Override
    public void liberar() {
        primeiro = null;
        ultimo = null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        NoLista<T> atual = primeiro;
        while (atual != null) {
            s.append(atual.getInfo());
            if (atual.getProximo() != null) s.append(",");
            atual = atual.getProximo();
        }
        return s.toString();
    }

    public static void main(String[] args) {
        FilaLista<Integer> fila = new FilaLista<>();
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        System.out.println(fila);                // 10,20,30
        System.out.println("Retirar: " + fila.retirar()); // 10
        System.out.println(fila);                // 20,30
        System.out.println("Peek: " + fila.peek()); // 20
    }
}
