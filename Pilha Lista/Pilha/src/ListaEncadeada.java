public class ListaEncadeada<T> {
    private NoLista<T> primeiro;

    public ListaEncadeada() {
        this.primeiro = null; // a) Começa vazia
    }

    public NoLista<T> getPrimeiro() {
        return primeiro; // b) Getter
    }

    public void inserir(T valor) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(valor);
        novo.setProximo(primeiro); // c) Novo aponta para o antigo primeiro
        this.primeiro = novo; // Agora o novo é o primeiro[cite: 4]
    }

    public boolean estaVazia() {
        return primeiro == null; // d)[cite: 4]
    }

    public NoLista<T> buscar(T valor) {
        NoLista<T> atual = primeiro;
        while (atual != null) { // e) Percorre até o fim[cite: 4]
            if (atual.getInfo().equals(valor)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void retirar(T valor) {
        NoLista<T> anterior = null;
        NoLista<T> atual = primeiro;

        while (atual != null && !atual.getInfo().equals(valor)) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual != null) {
            if (anterior == null) { // f) Era o primeiro da lista[cite: 4]
                this.primeiro = atual.getProximo();
            } else { // Estava no meio ou fim[cite: 4]
                anterior.setProximo(atual.getProximo());
            }
        }
    }

    public int obterComprimento() {
        int cont = 0;
        NoLista<T> atual = primeiro;
        while (atual != null) { // g) Conta um por um[cite: 4]
            cont++;
            atual = atual.getProximo();
        }
        return cont;
    }

    public NoLista<T> obterNo(int idx) {
        if (idx < 0) { // h) Validação de índice[cite: 4]
            throw new IndexOutOfBoundsException("Índice negativo: " + idx);
        }

        NoLista<T> atual = primeiro;
        for (int i = 0; i < idx && atual != null; i++) {
            atual = atual.getProximo();
        }

        if (atual == null) { // h) Índice maior que a lista[cite: 4]
            throw new IndexOutOfBoundsException("Índice fora do limite: " + idx);
        }
        return atual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoLista<T> atual = primeiro;
        while (atual != null) { // i) Monta a string separada por vírgula[cite: 4]
            sb.append(atual.getInfo());
            if (atual.getProximo() != null)
                sb.append(",");
            atual = atual.getProximo();
        }
        return sb.toString();
    }
}