public class PilhaVetor<T> implements Pilha<T> {
    private T[] info;
    private int limite;
    private int tamanho;

    public PilhaVetor(int limite) {
        this.info = (T[]) new Object[limite];
        this.limite = limite;
        this.tamanho = 0;
    }

    @Override
    public void push(T info) {
        if (this.tamanho == this.limite) {
            throw new PilhaCheiaException();
        }
        this.info[this.tamanho] = info;
        this.tamanho++;
    }

    @Override
    public T pop() {
        T valor = peek();
        this.tamanho--;
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        return this.info[this.tamanho - 1];
    }

    @Override
    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    @Override
    public void liberar() {
        this.tamanho = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = this.tamanho - 1; i >= 0; i--) {
            s.append(this.info[i]);
            if (i > 0)
                s.append(",");
        }
        return s.toString();
    }

    public void concatenar(PilhaVetor<T> p) {
        if (this.tamanho + p.tamanho > this.limite) {
            throw new PilhaCheiaException();
        }
        for (int i = 0; i < p.tamanho; i++) {
            this.push(p.info[i]);
        }
    }

    public boolean verificaDelimitadores(String expressao) {
        PilhaVetor<Character> pilha = new PilhaVetor<>(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            // Se for abertura, empilha
            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            }
            // Se for fechamento
            else if (c == ')' || c == ']' || c == '}') {
                if (pilha.estaVazia())
                    return false; // Fechamento sem abertura

                char topo = pilha.pop();
                if (!formaPar(topo, c))
                    return false; // Par errado (ex: (]) )
            }
        }
        return pilha.estaVazia(); // Se sobrar algo, está errado
    }

    private boolean formaPar(char abrindo, char fechando) {
        return (abrindo == '(' && fechando == ')') ||
                (abrindo == '[' && fechando == ']') ||
                (abrindo == '{' && fechando == '}');
    }

}