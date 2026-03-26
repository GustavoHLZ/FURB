// ============================================================
// Declara a classe pública ListaEstatica.
// "public" = pode ser usada por qualquer outra classe.
// ============================================================
public class ListaEstatica {

    // ----------------------------------------------------------
    // ATRIBUTOS (variáveis que pertencem a cada objeto da classe)
    // ----------------------------------------------------------

    // Vetor que guarda os inteiros da lista.
    // "private" = só esta classe pode acessar diretamente.
    // "int[]"   = tipo array de inteiros.
    private int[] info;

    // Contador de quantos elementos estão ocupados no vetor.
    // NÃO é o tamanho do vetor, mas sim quantos dados foram inseridos.
    private int tamanho;

    // Capacidade com que o vetor começa: 10 posições.
    // "static final" = constante de classe (igual para todos os objetos, nunca muda).
    private static final int CAPACIDADE_INICIAL = 10;

    // Quantidade de posições adicionadas a cada redimensionamento.
    private static final int INCREMENTO = 10;


    // ==========================================================
    // a) CONSTRUTOR
    // Chamado automaticamente quando fazemos: new ListaEstatica()
    // ==========================================================
    public ListaEstatica() {
        // Cria o vetor interno com 10 posições (todas com valor 0 por padrão em Java).
        info = new int[CAPACIDADE_INICIAL];

        // Lista começa sem nenhum elemento inserido.
        tamanho = 0;
    }


    // ==========================================================
    // b) REDIMENSIONAR  (privado — só usado internamente)
    // Aumenta a capacidade do vetor em +10 posições.
    // ==========================================================
    private void redimensionar() {
        // Cria um NOVO vetor com 10 posições a mais que o atual.
        // Ex.: se info tinha 10 posições, novoVetor terá 20.
        int[] novoVetor = new int[info.length + INCREMENTO];

        // Copia cada elemento do vetor antigo para o novo vetor,
        // posição por posição, do índice 0 até o último ocupado.
        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = info[i];
        }

        // Aponta o atributo "info" para o novo vetor maior.
        // O vetor antigo será descartado pelo Garbage Collector do Java.
        info = novoVetor;
    }


    // ==========================================================
    // c) INSERIR
    // Adiciona um inteiro no final da lista.
    // ==========================================================
    public void inserir(int valor) {
        // Verifica se o vetor está completamente cheio
        // (todos os índices de 0 até info.length-1 já estão ocupados).
        if (tamanho == info.length) {
            // Chama o redimensionamento antes de inserir.
            redimensionar();
        }

        // Coloca o novo valor na primeira posição livre.
        // "tamanho" sempre aponta para o próximo índice disponível.
        // Ex.: se tamanho==3, os índices 0,1,2 já estão usados; insere em [3].
        info[tamanho] = valor;

        // Incrementa o contador de elementos ocupados.
        tamanho++;
    }


    // ==========================================================
    // d) EXIBIR
    // Imprime todos os elementos no console com formatação [ a, b, c ].
    // ==========================================================
    public void exibir() {
        // Imprime o colchete de abertura SEM quebra de linha (print, não println).
        System.out.print("[");

        // Percorre do índice 0 até o último elemento ocupado.
        for (int i = 0; i < tamanho; i++) {
            // Imprime o valor atual.
            System.out.print(info[i]);

            // Se não for o último elemento, imprime ", " como separador.
            if (i < tamanho - 1) System.out.print(", ");
        }

        // Fecha o colchete e pula a linha.
        System.out.println("]");
    }


    // ==========================================================
    // e) BUSCAR
    // Procura um valor e retorna o índice onde ele está, ou -1.
    // ==========================================================
    public int buscar(int valor) {
        // Percorre a lista do início ao fim (busca linear/sequencial).
        for (int i = 0; i < tamanho; i++) {
            // Se o elemento na posição i for igual ao valor buscado...
            if (info[i] == valor) {
                // ...retorna o índice imediatamente (para a busca aqui).
                return i;
            }
        }

        // Se saiu do laço sem encontrar, retorna -1 (convenção: não encontrado).
        return -1;
    }


    // ==========================================================
    // f) RETIRAR
    // Remove um elemento pelo seu VALOR (não pelo índice).
    // Desloca todos os seguintes uma posição à esquerda.
    // ==========================================================
    public void retirar(int valor) {
        // Usa o método buscar() para descobrir em qual índice está o valor.
        int pos = buscar(valor);

        // Se buscar() retornou -1, o elemento não existe na lista; não faz nada.
        if (pos == -1) return;

        // Desloca todos os elementos que estão APÓS a posição removida
        // uma posição para a esquerda, sobrescrevendo o elemento removido.
        // Ex.: lista [5,10,15,20], remove posição 1 (valor 10):
        //   i=1: info[1] = info[2]  →  [5,15,15,20]
        //   i=2: info[2] = info[3]  →  [5,15,20,20]
        for (int i = pos; i < tamanho - 1; i++) {
            info[i] = info[i + 1];
        }

        // Decrementa o contador: a última posição (agora duplicada) é "esquecida".
        // A lista passa a ter um elemento a menos.
        tamanho--;
    }


    // ==========================================================
    // g) LIBERAR
    // Descarta todos os dados e volta ao estado inicial.
    // ==========================================================
    public void liberar() {
        // Cria um novo vetor vazio de 10 posições, descartando o anterior.
        info = new int[CAPACIDADE_INICIAL];

        // Zera o contador: lista está vazia novamente.
        tamanho = 0;
    }


    // ==========================================================
    // h) OBTER ELEMENTO
    // Retorna o valor de uma posição específica.
    // Lança exceção se a posição não existir ou não estiver ocupada.
    // ==========================================================
    public int obterElemento(int posicao) {
        // Valida o índice:
        //   posicao < 0        → índice negativo (impossível)
        //   posicao >= tamanho → índice além dos dados inseridos
        if (posicao < 0 || posicao >= tamanho) {
            // Lança IndexOutOfBoundsException com mensagem explicativa.
            // Isso interrompe a execução do método imediatamente.
            throw new IndexOutOfBoundsException(
                "Posição inválida: " + posicao + ". Tamanho atual: " + tamanho);
        }

        // Se chegou aqui, a posição é válida: retorna o valor armazenado.
        return info[posicao];
    }


    // ==========================================================
    // i) ESTA VAZIA
    // Retorna true se não há nenhum elemento na lista.
    // ==========================================================
    public boolean estaVazia() {
        // Se tamanho == 0, não há nenhum dado → retorna true.
        // Caso contrário retorna false.
        return tamanho == 0;
    }


    // ==========================================================
    // j) GET TAMANHO  (getter)
    // Retorna quantos elementos estão armazenados na lista.
    // ==========================================================
    public int getTamanho() {
        // Simplesmente devolve o valor do atributo privado "tamanho".
        return tamanho;
    }


    // ==========================================================
    // k) TO STRING
    // Retorna todos os elementos como texto separados por vírgula.
    // Ex.: "5,10,15,20"
    // @Override indica que este método substitui o toString() herdado de Object.
    // ==========================================================
    @Override
    public String toString() {
        // StringBuilder é mais eficiente que concatenar Strings com "+" dentro de laços.
        StringBuilder sb = new StringBuilder();

        // Percorre todos os elementos ocupados.
        for (int i = 0; i < tamanho; i++) {
            // Adiciona o valor atual ao texto em construção.
            sb.append(info[i]);

            // Adiciona vírgula separadora, EXCETO após o último elemento.
            if (i < tamanho - 1) sb.append(",");
        }

        // Converte o StringBuilder para String e retorna.
        return sb.toString();
    }


    // ==========================================================
    //  MÉTODOS EXTRAS — úteis em prova
    // ==========================================================

    // ----------------------------------------------------------
    // GET CAPACIDADE
    // Retorna o tamanho FÍSICO do vetor (posições totais, incluindo
    // as vazias). Diferente de getTamanho() que conta só os inseridos.
    // ----------------------------------------------------------
    public int getCapacidade() {
        // info.length = número de posições alocadas na memória.
        return info.length;
    }


    // ----------------------------------------------------------
    // INSERIR NA POSIÇÃO
    // Insere um valor em um índice específico, empurrando os
    // elementos seguintes uma posição para a direita.
    // ----------------------------------------------------------
    public void inserirNaPosicao(int posicao, int valor) {
        // Valida a posição: pode ser de 0 até tamanho (inserir no final é válido).
        // Por isso usa "> tamanho" e não ">= tamanho".
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        // Se o vetor estiver cheio, aumenta antes de deslocar.
        if (tamanho == info.length) {
            redimensionar();
        }

        // Desloca os elementos da direita para a direita, de trás para frente,
        // para abrir espaço na posição desejada.
        // Ex.: [1,3,4], inserir 2 na posição 1:
        //   i=2 (tamanho): info[3] = info[2]  →  [1,3,4,4]
        //   i=1:           info[2] = info[1]  →  [1,3,3,4]
        for (int i = tamanho; i > posicao; i--) {
            info[i] = info[i - 1];
        }

        // Coloca o novo valor na posição que foi aberta.
        info[posicao] = valor;   // → [1,2,3,4]

        // Incrementa o contador de elementos.
        tamanho++;
    }


    // ----------------------------------------------------------
    // RETIRAR DA POSIÇÃO
    // Remove o elemento de um índice específico (e não pelo valor).
    // ----------------------------------------------------------
    public void retirarDaPosicao(int posicao) {
        // Valida: posição deve existir e estar ocupada.
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        // Desloca todos os elementos seguintes uma posição à esquerda,
        // sobrescrevendo o elemento removido (mesma lógica do retirar()).
        for (int i = posicao; i < tamanho - 1; i++) {
            info[i] = info[i + 1];
        }

        // Diminui o contador: o último valor duplicado é "esquecido".
        tamanho--;
    }


    // ----------------------------------------------------------
    // SUBSTITUIR
    // Troca o valor armazenado em uma posição por um novo valor.
    // ----------------------------------------------------------
    public void substituir(int posicao, int novoValor) {
        // Valida se a posição existe e está ocupada.
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        // Sobrescreve o valor na posição indicada.
        info[posicao] = novoValor;
        // (tamanho não muda: nenhum elemento foi adicionado ou removido)
    }


    // ----------------------------------------------------------
    // GET MAIOR
    // Percorre a lista e retorna o maior valor encontrado.
    // ----------------------------------------------------------
    public int getMaior() {
        // Não faz sentido buscar o maior de uma lista vazia.
        if (estaVazia()) throw new IllegalStateException("Lista vazia.");

        // Assume que o primeiro elemento é o maior (ponto de partida).
        int maior = info[0];

        // Percorre a partir do segundo elemento (índice 1).
        for (int i = 1; i < tamanho; i++) {
            // Se o elemento atual for maior que o registrado até agora...
            if (info[i] > maior) {
                // ...atualiza o maior.
                maior = info[i];
            }
        }

        // Retorna o maior encontrado após percorrer tudo.
        return maior;
    }


    // ----------------------------------------------------------
    // GET MENOR
    // Percorre a lista e retorna o menor valor encontrado.
    // (Lógica espelhada do getMaior, mas compara com <)
    // ----------------------------------------------------------
    public int getMenor() {
        // Lista vazia não tem menor.
        if (estaVazia()) throw new IllegalStateException("Lista vazia.");

        // Começa assumindo que o primeiro é o menor.
        int menor = info[0];

        // Percorre do segundo em diante.
        for (int i = 1; i < tamanho; i++) {
            // Se encontrar algo menor, atualiza.
            if (info[i] < menor) {
                menor = info[i];
            }
        }

        return menor;
    }


    // ----------------------------------------------------------
    // INVERTER
    // Inverte a ordem dos elementos dentro do próprio vetor,
    // usando dois ponteiros que caminham de fora para o centro.
    // ----------------------------------------------------------
    public void inverter() {
        // "esq" começa no primeiro índice.
        int esq = 0;

        // "dir" começa no último índice ocupado.
        int dir = tamanho - 1;

        // Enquanto os dois ponteiros não se cruzarem (ou se encontrarem)...
        while (esq < dir) {
            // Troca os valores das posições esq e dir usando variável temporária.
            int temp  = info[esq];   // guarda o da esquerda temporariamente
            info[esq] = info[dir];   // esquerda recebe o valor da direita
            info[dir] = temp;        // direita recebe o valor guardado

            // Avança o ponteiro da esquerda para dentro.
            esq++;

            // Recua o ponteiro da direita para dentro.
            dir--;
        }
        // Quando esq >= dir, todos os pares já foram trocados.
    }


    // ----------------------------------------------------------
    // CONTAR OCORRÊNCIAS
    // Conta quantas vezes um valor aparece na lista.
    // ----------------------------------------------------------
    public int contarOcorrencias(int valor) {
        // Contador de aparições começa em zero.
        int count = 0;

        // Percorre todos os elementos.
        for (int i = 0; i < tamanho; i++) {
            // Se o elemento atual for igual ao valor buscado, incrementa o contador.
            if (info[i] == valor) count++;
        }

        // Retorna quantas vezes o valor apareceu (pode ser 0 se não existir).
        return count;
    }


    // ----------------------------------------------------------
    // ORDENAR  (Bubble Sort crescente)
    // Compara pares adjacentes e troca os que estão fora de ordem,
    // repetindo passagens até que a lista esteja ordenada.
    // ----------------------------------------------------------
    public void ordenar() {
        // Laço externo: controla o número de passagens.
        // A cada passagem, o maior elemento restante "borbulha"
        // para a última posição não-ordenada.
        // Precisamos de no máximo (tamanho - 1) passagens.
        for (int i = 0; i < tamanho - 1; i++) {

            // Laço interno: compara pares adjacentes [j] e [j+1].
            // A cada passagem "i", os últimos "i" elementos já estão no lugar,
            // por isso o limite encurta em "- i".
            for (int j = 0; j < tamanho - 1 - i; j++) {

                // Se o elemento da esquerda for MAIOR que o da direita, eles estão fora de ordem.
                if (info[j] > info[j + 1]) {
                    // Troca os dois usando variável temporária.
                    int temp    = info[j];       // guarda o da esquerda
                    info[j]     = info[j + 1];   // esquerda recebe o menor
                    info[j + 1] = temp;          // direita recebe o maior
                }
            }
        }
        // Ao final, os elementos estão em ordem crescente.
    }


    // ----------------------------------------------------------
    // COPIAR
    // Cria e retorna uma nova ListaEstatica com os mesmos elementos.
    // As duas listas são INDEPENDENTES: alterar uma não afeta a outra.
    // ----------------------------------------------------------
    public ListaEstatica copiar() {
        // Cria um objeto novo com seu próprio vetor interno.
        ListaEstatica copia = new ListaEstatica();

        // Insere cada elemento desta lista na cópia, um a um.
        // inserir() é chamado no objeto "copia", então os dados
        // ficam armazenados no vetor DELE, não no desta lista.
        for (int i = 0; i < tamanho; i++) {
            copia.inserir(info[i]);
        }

        // Retorna a referência para o novo objeto criado.
        return copia;
    }
}