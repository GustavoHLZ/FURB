public class PilhaLista<T> implements Pilha<T> {

    private ListaEncadeada<T> lista;

    public PilhaLista() {
        this.lista = new ListaEncadeada<>();
    }

    @Override
    public void push(T info) {
        // Na pilha com lista, o topo é sempre o início (primeiro nó).
        // O método inserir() da sua ListaEncadeada já faz isso: coloca no início.
        lista.inserir(info);
    }

    @Override
    public T pop() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }

        // Pegamos o valor do topo (primeiro nó da lista)
        T valor = lista.getPrimeiro().getInfo();

        // Retiramos o primeiro nó da lista. 
        // O segundo nó (se existir) passará a ser o primeiro (novo topo).
        lista.retirar(valor);

        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        // Retorna a informação do primeiro nó sem remover nada.
        return lista.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void liberar() {
        // Reinicializa a lista, efetivamente esvaziando a pilha.
        this.lista = new ListaEncadeada<>();
    }

    @Override
    public String toString() {
        // Usa o toString da ListaEncadeada que já formata os elementos separados por vírgula.
        return lista.toString();
    }
}
