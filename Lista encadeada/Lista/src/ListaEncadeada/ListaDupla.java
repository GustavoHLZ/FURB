package ListaEncadeada;

import model.NoListaDupla;

public class ListaDupla<T> {

    private NoListaDupla<T> primeiro;

    public ListaDupla() {

    }

    public NoListaDupla<T> getPrimeiro() {
        return primeiro;
    }

    // -------------------------------------------------------
    // MÉTODOS BASE (corrigidos)
    // -------------------------------------------------------

    /**
     * Insere no INÍCIO da lista.
     * Atualiza proximo e anterior corretamente.
     */
    public void inserir(T info) {
        NoListaDupla<T> novo = new NoListaDupla<>();
        novo.setInfo(info);
        novo.setProximo(primeiro);
        novo.setAnterior(null);

        if (primeiro != null) {
            primeiro.setAnterior(novo); // corrigido: era setAnterior(null)
        }
        primeiro = novo;
    }

    /**
     * Procura nó pelo valor. Retorna o nó ou null.
     * Corrigido: usa getInfo().equals() em vez de noAtual.equals()
     */
    public NoListaDupla<T> buscar(T valor) {
        NoListaDupla<T> noAtual = primeiro;
        while (noAtual != null) {
            if (noAtual.getInfo().equals(valor)) { // corrigido
                return noAtual;
            }
            noAtual = noAtual.getProximo();
        }
        return null;
    }

    /**
     * Remove o nó que contém o valor informado.
     * Corrigido: busca o nó real antes de remover.
     */
    public void retirar(T valor) {
        NoListaDupla<T> no = buscar(valor); // corrigido: busca o nó real
        if (no == null)
            return;

        if (no == primeiro) {
            primeiro = no.getProximo();
        } else {
            no.getAnterior().setProximo(no.getProximo());
        }

        if (no.getProximo() != null) {
            no.getProximo().setAnterior(no.getAnterior());
        }
    }

    /**
     * Retorna o último nó da lista.
     */
    private NoListaDupla<T> getUltimo() {
        NoListaDupla<T> noAtual = primeiro;
        while (noAtual.getProximo() != null) {
            noAtual = noAtual.getProximo();
        }
        return noAtual;
    }

    /**
     * Exibe a lista de trás para frente usando o ponteiro anterior.
     */
    public String exibirOrdemInversa() {
        if (primeiro == null)
            return "Lista Vazia";
        NoListaDupla<T> noAtual = getUltimo();
        String dados = "";
        while (noAtual != null) {
            dados += noAtual.getInfo() + ", ";
            noAtual = noAtual.getAnterior();
        }
        return dados;
    }

    /**
     * Libera todos os encadeamentos nó a nó.
     */
    public void liberar() {
        NoListaDupla<T> noAtual = primeiro;
        while (noAtual != null) {
            NoListaDupla<T> proximo = noAtual.getProximo();
            noAtual.setAnterior(null);
            noAtual.setProximo(null);
            noAtual = proximo;
        }
        primeiro = null;
    }

    /**
     * Verifica se a lista está vazia.
     */
    public boolean estaVazia() {
        return primeiro == null;
    }

    /**
     * Retorna a quantidade de nós da lista.
     */
    public int obterComprimento() {
        NoListaDupla<T> p = primeiro;
        int contador = 0;
        while (p != null) {
            contador++;
            p = p.getProximo();
        }
        return contador;
    }

    public String toString() {
        NoListaDupla<T> noAtual = primeiro;
        String dados = "";
        if (noAtual == null)
            return "Lista Vazia";
        while (noAtual != null) {
            dados += noAtual.getInfo() + ", ";
            noAtual = noAtual.getProximo();
        }
        return dados;
    }

    // -------------------------------------------------------
    // MÉTODOS EXTRAS - POSSÍVEIS NA PROVA
    // -------------------------------------------------------

    /**
     * Insere no FINAL da lista.
     * Lembra de atualizar anterior do novo nó!
     * Ex: lista=[3,2,1] -> inserirNoFinal(0) -> [3,2,1,0]
     */
    public void inserirNoFinal(T info) {
        NoListaDupla<T> novo = new NoListaDupla<>();
        novo.setInfo(info);
        novo.setProximo(null);

        if (primeiro == null) {
            novo.setAnterior(null);
            primeiro = novo;
        } else {
            NoListaDupla<T> ultimo = getUltimo();
            ultimo.setProximo(novo);
            novo.setAnterior(ultimo); // ponto chave da lista dupla
        }
    }

    /**
     * Copia a lista mantendo a mesma ordem.
     * Retorna nova lista independente.
     */
    public ListaDupla<T> copiar() {
        ListaDupla<T> novaLista = new ListaDupla<T>();
        ListaDupla<T> temp = new ListaDupla<T>();

        // passa tudo para temp (inverte)
        NoListaDupla<T> p = primeiro;
        while (p != null) {
            temp.inserir(p.getInfo());
            p = p.getProximo();
        }

        // insere de temp na nova lista (inverte de volta)
        NoListaDupla<T> q = temp.getPrimeiro();
        while (q != null) {
            novaLista.inserir(q.getInfo());
            q = q.getProximo();
        }

        return novaLista;
    }

    /**
     * Inverte a ordem dos elementos da lista.
     * Na lista dupla: troca proximo <-> anterior de cada nó.
     * Ex: [3, 2, 1] vira [1, 2, 3]
     */
    public void inverter() {
        NoListaDupla<T> atual = primeiro;
        NoListaDupla<T> temp = null;

        while (atual != null) {
            // troca proximo e anterior
            temp = atual.getAnterior();
            atual.setAnterior(atual.getProximo());
            atual.setProximo(temp);

            // avança para o próximo (que agora é o anterior)
            atual = atual.getAnterior();
        }

        // ajusta o primeiro para o último nó original
        if (temp != null) {
            primeiro = temp.getAnterior();
        }
    }

    /**
     * Verifica se um elemento existe na lista. Retorna boolean.
     */
    public boolean contem(T valor) {
        return buscar(valor) != null;
    }

    /**
     * Remove duplicatas, mantendo apenas a primeira ocorrência.
     * Ex: [1, 2, 1, 3, 2] vira [1, 2, 3]
     */
    public void removerDuplicatas() {
        NoListaDupla<T> p = primeiro;
        while (p != null) {
            NoListaDupla<T> q = p.getProximo();
            while (q != null) {
                NoListaDupla<T> proximo = q.getProximo();
                if (q.getInfo().equals(p.getInfo())) {
                    // remove q
                    if (q.getAnterior() != null) {
                        q.getAnterior().setProximo(q.getProximo());
                    }
                    if (q.getProximo() != null) {
                        q.getProximo().setAnterior(q.getAnterior());
                    }
                }
                q = proximo;
            }
            p = p.getProximo();
        }
    }

    /**
     * Verifica se duas listas são iguais (mesmos valores na mesma ordem).
     */
    public boolean igual(ListaDupla<T> outra) {
        NoListaDupla<T> p = this.primeiro;
        NoListaDupla<T> q = outra.getPrimeiro();

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
     * Concatena outra lista ao final desta.
     * Ex: lista=[1,2] + outra=[3,4] -> lista=[1,2,3,4]
     */
    public void juntar(ListaDupla<T> outra) {
        NoListaDupla<T> p = outra.getPrimeiro();
        while (p != null) {
            this.inserirNoFinal(p.getInfo());
            p = p.getProximo();
        }
    }

    /**
     * Retorna o valor do primeiro nó.
     */
    public T obterPrimeiro() {
        if (estaVazia())
            throw new IndexOutOfBoundsException("Lista vazia");
        return primeiro.getInfo();
    }

    /**
     * Retorna o valor do último nó.
     */
    public T obterUltimo() {
        if (estaVazia())
            throw new IndexOutOfBoundsException("Lista vazia");
        return getUltimo().getInfo();
    }

    /**
     * Retorna o nó na posição informada (0 = primeiro).
     * Percorre a lista apenas uma vez.
     */
    public NoListaDupla<T> obterNo(int posicao) {
        if (posicao < 0)
            throw new IndexOutOfBoundsException();
        int contador = 0;
        NoListaDupla<T> p = primeiro;
        while (p != null) {
            if (contador == posicao)
                return p;
            contador++;
            p = p.getProximo();
        }
        throw new IndexOutOfBoundsException();
    }

    // -------------------------------------------------------
    // MAIN - testa tudo
    // -------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("=== INSERIR (início) e toString ===");
        ListaDupla<Integer> lista = new ListaDupla<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        System.out.println("Direta:  " + lista); // 20, 15, 10, 5
        System.out.println("Inversa: " + lista.exibirOrdemInversa()); // 5, 10, 15, 20

        System.out.println("\n=== BUSCAR ===");
        NoListaDupla<Integer> no = lista.buscar(10);
        System.out.println("Buscar 10: " + (no != null ? no : "null")); // 10
        System.out.println("Buscar 99: " + lista.buscar(99)); // null

        System.out.println("\n=== RETIRAR ===");
        lista.retirar(20); // remove o primeiro
        System.out.println("Após retirar 20: " + lista); // 15, 10, 5
        lista.retirar(10); // remove do meio
        System.out.println("Após retirar 10: " + lista); // 15, 5

        System.out.println("\n=== ESTA VAZIA e COMPRIMENTO ===");
        ListaDupla<Integer> vazia = new ListaDupla<>();
        System.out.println("Vazia? " + vazia.estaVazia()); // true
        System.out.println("Comprimento: " + lista.obterComprimento()); // 2

        System.out.println("\n=== INSERIR NO FINAL ===");
        ListaDupla<Integer> lista2 = new ListaDupla<>();
        lista2.inserir(1); // início: [1]
        lista2.inserirNoFinal(2); // fim: [1, 2]
        lista2.inserirNoFinal(3); // fim: [1, 2, 3]
        System.out.println("Lista: " + lista2); // 1, 2, 3
        System.out.println("Inversa: " + lista2.exibirOrdemInversa()); // 3, 2, 1

        System.out.println("\n=== COPIAR ===");
        ListaDupla<Integer> copia = lista2.copiar();
        System.out.println("Original: " + lista2); // 1, 2, 3
        System.out.println("Cópia:    " + copia); // 1, 2, 3

        System.out.println("\n=== INVERTER ===");
        ListaDupla<Integer> lista3 = new ListaDupla<>();
        lista3.inserirNoFinal(1);
        lista3.inserirNoFinal(2);
        lista3.inserirNoFinal(3);
        System.out.println("Antes:  " + lista3); // 1, 2, 3
        lista3.inverter();
        System.out.println("Depois: " + lista3); // 3, 2, 1

        System.out.println("\n=== CONTEM ===");
        ListaDupla<Integer> lista4 = new ListaDupla<>();
        lista4.inserirNoFinal(5);
        lista4.inserirNoFinal(10);
        lista4.inserirNoFinal(15);
        System.out.println("Contém 10? " + lista4.contem(10)); // true
        System.out.println("Contém 99? " + lista4.contem(99)); // false

        System.out.println("\n=== REMOVER DUPLICATAS ===");
        ListaDupla<Integer> lista5 = new ListaDupla<>();
        lista5.inserirNoFinal(1);
        lista5.inserirNoFinal(2);
        lista5.inserirNoFinal(1);
        lista5.inserirNoFinal(3);
        lista5.inserirNoFinal(2);
        System.out.println("Antes:  " + lista5); // 1, 2, 1, 3, 2
        lista5.removerDuplicatas();
        System.out.println("Depois: " + lista5); // 1, 2, 3

        System.out.println("\n=== IGUAL ===");
        ListaDupla<Integer> listaA = new ListaDupla<>();
        ListaDupla<Integer> listaB = new ListaDupla<>();
        listaA.inserirNoFinal(1);
        listaA.inserirNoFinal(2);
        listaB.inserirNoFinal(1);
        listaB.inserirNoFinal(2);
        System.out.println("Iguais? " + listaA.igual(listaB)); // true
        listaB.inserirNoFinal(3);
        System.out.println("Iguais? " + listaA.igual(listaB)); // false

        System.out.println("\n=== JUNTAR ===");
        ListaDupla<Integer> listaX = new ListaDupla<>();
        ListaDupla<Integer> listaY = new ListaDupla<>();
        listaX.inserirNoFinal(1);
        listaX.inserirNoFinal(2);
        listaY.inserirNoFinal(3);
        listaY.inserirNoFinal(4);
        System.out.println("X: " + listaX);
        System.out.println("Y: " + listaY);
        listaX.juntar(listaY);
        System.out.println("X após juntar Y: " + listaX); // 1, 2, 3, 4

        System.out.println("\n=== OBTER PRIMEIRO E ÚLTIMO ===");
        ListaDupla<Integer> lista6 = new ListaDupla<>();
        lista6.inserirNoFinal(10);
        lista6.inserirNoFinal(20);
        lista6.inserirNoFinal(30);
        System.out.println("Primeiro: " + lista6.obterPrimeiro()); // 10
        System.out.println("Último:   " + lista6.obterUltimo()); // 30

        System.out.println("\n=== OBTER NÓ ===");
        System.out.println("Posição 0: " + lista6.obterNo(0)); // 10
        System.out.println("Posição 2: " + lista6.obterNo(2)); // 30

        System.out.println("\n=== LIBERAR ===");
        lista6.liberar();
        System.out.println("Após liberar: " + lista6); // Lista Vazia
    }
}