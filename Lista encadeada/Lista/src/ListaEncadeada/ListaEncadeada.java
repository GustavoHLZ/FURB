package ListaEncadeada;

import model.NoLista;

public class ListaEncadeada<T> {

    private NoLista<T> primeiro;

    public ListaEncadeada() {
        this.primeiro = null;
    }

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    public void inserir(T info) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(info);
        novo.setProximo(this.getPrimeiro());
        this.primeiro = novo;
    }

    public boolean estaVazia() {
        if (this.getPrimeiro() == null) {
            return true;
        }
        return false;
    }

    public NoLista<T> buscar(T info) {
        NoLista<T> p = getPrimeiro();
        while (p != null) {
            if (p.getInfo().equals(info)) {
                return p;
            }
            p = p.getProximo();
        }
        return null;
    }

    public void retirar(T info) {
        NoLista<T> anterior = null;
        NoLista<T> p = this.getPrimeiro();
        while (p != null && !p.getInfo().equals(info)) {
            anterior = p;
            p = p.getProximo();
        }
        if (p != null) {
            if (p == this.getPrimeiro()) {
                this.primeiro = p.getProximo();
            } else {
                anterior.setProximo(p.getProximo());
            }
        }
    }

    public int obterComprimento() {
        NoLista<T> p = this.getPrimeiro();
        int contador = 0;
        while (p != null) {
            contador++;
            p = p.getProximo();
        }
        return contador;
    }

    public NoLista<T> obterNo(int posicao) {
        if (posicao < 0) {
            throw new IndexOutOfBoundsException();
        }
        int contador = 0;
        NoLista<T> p = this.getPrimeiro();
        while (p != null) {
            if (contador == posicao) {
                return p;
            }
            contador++;
            p = p.getProximo();
        }
        throw new IndexOutOfBoundsException();
    }

    public String toString() {
        NoLista<T> p = this.getPrimeiro();
        String str = "";
        if (p == null) {
            return "Lista Vazia";
        }
        while (p != null) {
            str += p.getInfo() + ", ";
            p = p.getProximo();
        }
        return str;
    }

    public void liberar() {
        primeiro = null;
    }

    // -------------------------------------------------------
    // MÉTODOS EXTRAS - POSSÍVEIS NA PROVA
    // -------------------------------------------------------

    /**
     * Copia todos os nós da lista e retorna uma nova lista com os mesmos valores.
     * A ordem dos elementos é mantida.
     */
    public ListaEncadeada<T> copiar() {
        ListaEncadeada<T> novaLista = new ListaEncadeada<T>();
        ListaEncadeada<T> temp = new ListaEncadeada<T>();

        NoLista<T> p = getPrimeiro();
        while (p != null) {
            temp.inserir(p.getInfo());
            p = p.getProximo();
        }

        NoLista<T> q = temp.getPrimeiro();
        while (q != null) {
            novaLista.inserir(q.getInfo());
            q = q.getProximo();
        }

        return novaLista;
    }

    /**
     * Insere um novo nó no FINAL da lista.
     * Ex: lista=[3,2,1] -> inserirNoFinal(0) -> [3,2,1,0]
     */
    public void inserirNoFinal(T info) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(info);
        novo.setProximo(null);

        if (this.estaVazia()) {
            this.primeiro = novo;
        } else {
            NoLista<T> p = this.getPrimeiro();
            while (p.getProximo() != null) {
                p = p.getProximo();
            }
            p.setProximo(novo);
        }
    }

    /**
     * Inverte a ordem dos elementos da lista.
     * Ex: [3, 2, 1] vira [1, 2, 3]
     */
    public void inverter() {
        NoLista<T> anterior = null;
        NoLista<T> atual = this.getPrimeiro();
        NoLista<T> proximo = null;

        while (atual != null) {
            proximo = atual.getProximo(); // guarda o próximo
            atual.setProximo(anterior); // inverte o ponteiro
            anterior = atual; // avança anterior
            atual = proximo; // avança atual
        }
        this.primeiro = anterior;
    }

    /**
     * Verifica se um elemento existe na lista.
     * Retorna true se existir, false caso contrário.
     */
    public boolean contem(T info) {
        NoLista<T> p = getPrimeiro();
        while (p != null) {
            if (p.getInfo().equals(info)) {
                return true;
            }
            p = p.getProximo();
        }
        return false;
    }

    /**
     * Remove todos os nós duplicados, mantendo apenas a primeira ocorrência.
     * Ex: [1, 2, 1, 3, 2] vira [1, 2, 3]
     */
    public void removerDuplicatas() {
        NoLista<T> p = this.getPrimeiro();
        while (p != null) {
            NoLista<T> anterior = p;
            NoLista<T> q = p.getProximo();
            while (q != null) {
                if (q.getInfo().equals(p.getInfo())) {
                    anterior.setProximo(q.getProximo());
                } else {
                    anterior = q;
                }
                q = q.getProximo();
            }
            p = p.getProximo();
        }
    }

    /**
     * Verifica se duas listas são iguais (mesmos elementos na mesma ordem).
     * Retorna true se forem iguais, false caso contrário.
     */
    public boolean igual(ListaEncadeada<T> outra) {
        NoLista<T> p = this.getPrimeiro();
        NoLista<T> q = outra.getPrimeiro();

        while (p != null && q != null) {
            if (!p.getInfo().equals(q.getInfo())) {
                return false;
            }
            p = p.getProximo();
            q = q.getProximo();
        }
        return p == null && q == null;
    }

    /**
     * Junta (concatena) outra lista ao final desta lista.
     * Ex: lista=[1,2] + outra=[3,4] -> lista=[1,2,3,4]
     */
    public void juntar(ListaEncadeada<T> outra) {
        NoLista<T> p = outra.getPrimeiro();
        ListaEncadeada<T> temp = new ListaEncadeada<T>();

        while (p != null) {
            temp.inserir(p.getInfo());
            p = p.getProximo();
        }

        NoLista<T> q = temp.getPrimeiro();
        while (q != null) {
            this.inserirNoFinal(q.getInfo());
            q = q.getProximo();
        }
    }

    /**
     * Retorna o valor do último nó da lista.
     */
    public T obterUltimo() {
        if (this.estaVazia()) {
            throw new IndexOutOfBoundsException("Lista vazia");
        }
        NoLista<T> p = this.getPrimeiro();
        while (p.getProximo() != null) {
            p = p.getProximo();
        }
        return p.getInfo();
    }

    /**
     * Retorna o valor do primeiro nó da lista.
     */
    public T obterPrimeiro() {
        if (this.estaVazia()) {
            throw new IndexOutOfBoundsException("Lista vazia");
        }
        return this.getPrimeiro().getInfo();
    }

    // -------------------------------------------------------
    // MAIN - testa todos os métodos extras
    // -------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("=== COPIAR ===");
        ListaEncadeada<Integer> lista1 = new ListaEncadeada<>();
        lista1.inserirNoFinal(1);
        lista1.inserirNoFinal(2);
        lista1.inserirNoFinal(3);
        System.out.println("Original: " + lista1); // 1, 2, 3
        System.out.println("Cópia:    " + lista1.copiar()); // 1, 2, 3

        System.out.println("\n=== INSERIR NO FINAL ===");
        ListaEncadeada<Integer> lista2 = new ListaEncadeada<>();
        lista2.inserir(1); // início: [1]
        lista2.inserirNoFinal(2); // fim: [1, 2]
        lista2.inserirNoFinal(3); // fim: [1, 2, 3]
        System.out.println("Lista: " + lista2); // 1, 2, 3

        System.out.println("\n=== INVERTER ===");
        ListaEncadeada<Integer> lista3 = new ListaEncadeada<>();
        lista3.inserir(1);
        lista3.inserir(2);
        lista3.inserir(3);
        System.out.println("Antes:  " + lista3); // 3, 2, 1
        lista3.inverter();
        System.out.println("Depois: " + lista3); // 1, 2, 3

        System.out.println("\n=== CONTEM ===");
        ListaEncadeada<Integer> lista4 = new ListaEncadeada<>();
        lista4.inserirNoFinal(5);
        lista4.inserirNoFinal(10);
        lista4.inserirNoFinal(15);
        System.out.println("Contém 10? " + lista4.contem(10)); // true
        System.out.println("Contém 99? " + lista4.contem(99)); // false

        System.out.println("\n=== REMOVER DUPLICATAS ===");
        ListaEncadeada<Integer> lista5 = new ListaEncadeada<>();
        lista5.inserirNoFinal(1);
        lista5.inserirNoFinal(2);
        lista5.inserirNoFinal(1);
        lista5.inserirNoFinal(3);
        lista5.inserirNoFinal(2);
        System.out.println("Antes:  " + lista5); // 1, 2, 1, 3, 2
        lista5.removerDuplicatas();
        System.out.println("Depois: " + lista5); // 1, 2, 3

        System.out.println("\n=== IGUAL ===");
        ListaEncadeada<Integer> listaA = new ListaEncadeada<>();
        ListaEncadeada<Integer> listaB = new ListaEncadeada<>();
        listaA.inserirNoFinal(1);
        listaA.inserirNoFinal(2);
        listaA.inserirNoFinal(3);
        listaB.inserirNoFinal(1);
        listaB.inserirNoFinal(2);
        listaB.inserirNoFinal(3);
        System.out.println("Iguais? " + listaA.igual(listaB)); // true
        listaB.inserirNoFinal(4);
        System.out.println("Iguais? " + listaA.igual(listaB)); // false

        System.out.println("\n=== JUNTAR ===");
        ListaEncadeada<Integer> listaX = new ListaEncadeada<>();
        ListaEncadeada<Integer> listaY = new ListaEncadeada<>();
        listaX.inserirNoFinal(1);
        listaX.inserirNoFinal(2);
        listaY.inserirNoFinal(3);
        listaY.inserirNoFinal(4);
        System.out.println("X: " + listaX);
        System.out.println("Y: " + listaY);
        listaX.juntar(listaY);
        System.out.println("X após juntar Y: " + listaX); // 1, 2, 3, 4

        System.out.println("\n=== OBTER PRIMEIRO E ÚLTIMO ===");
        ListaEncadeada<Integer> lista6 = new ListaEncadeada<>();
        lista6.inserirNoFinal(10);
        lista6.inserirNoFinal(20);
        lista6.inserirNoFinal(30);
        System.out.println("Primeiro: " + lista6.obterPrimeiro()); // 10
        System.out.println("Último:   " + lista6.obterUltimo()); // 30
    }
}