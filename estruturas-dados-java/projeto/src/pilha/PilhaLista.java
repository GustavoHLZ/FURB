package pilha;

import fila.NoLista;

/**
 * Implementação de Pilha usando lista simplesmente encadeada.
 * O topo da pilha é sempre o primeiro nó da lista.
 */
public class PilhaLista<T> implements Pilha<T> {
    private NoLista<T> topo;

    public PilhaLista() {
        this.topo = null;
    }

    @Override
    public void push(T info) {
        NoLista<T> novo = new NoLista<>(info);
        novo.setProximo(topo);   // novo aponta para o antigo topo
        topo = novo;             // novo vira o topo
    }

    @Override
    public T pop() {
        T valor = peek();
        topo = topo.getProximo();
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) throw new PilhaVaziaException();
        return topo.getInfo();
    }

    @Override
    public boolean estaVazia() {
        return topo == null;
    }

    @Override
    public void liberar() {
        topo = null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        NoLista<T> atual = topo;
        while (atual != null) {
            s.append(atual.getInfo());
            if (atual.getProximo() != null) s.append(",");
            atual = atual.getProximo();
        }
        return s.toString();
    }

    public static void main(String[] args) {
        PilhaLista<Integer> pilha = new PilhaLista<>();
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        System.out.println(pilha);               // 30,20,10
        System.out.println("Pop: " + pilha.pop()); // 30
        System.out.println(pilha);               // 20,10
    }
}
