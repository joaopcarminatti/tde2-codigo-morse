/**
 * Árvore binária do Código Morse.
 *
 * A regra é uma só:
 *  - PONTO (.) -> vai para a ESQUERDA
 *  - TRAÇO (-) -> vai para a DIREITA
 *
 * Exemplo: a letra A é ".-"
 * Saindo da raiz: ponto (esquerda), depois traço (direita). Ali fica o A.
 */
public class ArvoreMorse {

    // Letras e seus códigos. A posição 0 de um vetor combina com a posição 0
    // do outro, a posição 1 com a posição 1, e assim por diante.
    private char[] letras = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private String[] codigos = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
        "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
        "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
    };

    // A raiz não tem letra: é só o ponto de partida.
    private No raiz;

    public ArvoreMorse() {
        raiz = new No();
        carregarAlfabeto();
    }

    public No getRaiz() {
        return raiz;
    }

    // ===================== INSERÇÃO =====================

    // Insere todas as letras e números na árvore, um por um.
    private void carregarAlfabeto() {
        for (int i = 0; i < letras.length; i++) {
            inserir(letras[i], codigos[i]);
        }
    }

    // Anda pela árvore seguindo o código. Se o caminho ainda não existe,
    // cria um nó vazio para poder continuar. No fim do código, coloca a letra.
    public void inserir(char letra, String codigo) {
        No atual = raiz;

        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);

            if (simbolo == '.') {
                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(new No());
                }
                atual = atual.getEsquerda();
            } else {
                if (atual.getDireita() == null) {
                    atual.setDireita(new No());
                }
                atual = atual.getDireita();
            }
        }

        atual.setLetra(letra);
    }

    // ===================== DECODIFICAÇÃO =====================

    // Transforma UM código (ex: ".-") em letra (ex: 'A').
    // Se o código não levar a nenhuma letra, devolve '?'.
    public char decodificarLetra(String codigo) {
        No atual = raiz;

        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);

            if (simbolo == '.') {
                atual = atual.getEsquerda();
            } else if (simbolo == '-') {
                atual = atual.getDireita();
            } else {
                return '?'; // símbolo que não é ponto nem traço
            }

            if (atual == null) {
                return '?'; // o caminho não existe na árvore
            }
        }

        if (atual.temLetra()) {
            return atual.getLetra();
        }
        return '?'; // parou num nó vazio
    }

    // Transforma uma mensagem inteira em texto.
    // Letras separadas por espaço, palavras separadas por "/".
    // Exemplo: "... --- ... / --- -.-" -> "SOS OK"
    public String decodificarMensagem(String mensagem) {
        String resultado = "";
        String[] palavras = mensagem.split("/");

        for (String palavra : palavras) {
            String[] codigosDaPalavra = palavra.trim().split(" ");

            for (String codigo : codigosDaPalavra) {
                if (!codigo.isEmpty()) {
                    resultado += decodificarLetra(codigo);
                }
            }
            resultado += " ";
        }

        return resultado.trim();
    }

    // ===================== CODIFICAÇÃO =====================

    // Transforma UMA letra (ex: 'A') em código (ex: ".-").
    public String codificarLetra(char letra) {
        String codigo = buscarCodigo(raiz, Character.toUpperCase(letra), "");

        if (codigo == null) {
            return "?"; // letra que não existe na árvore
        }
        return codigo;
    }

    // Procura a letra na árvore inteira, anotando o caminho percorrido:
    // "." quando desce para a esquerda e "-" quando desce para a direita.
    // Quando encontra a letra, o caminho anotado É o código Morse dela.
    private String buscarCodigo(No no, char letra, String caminho) {
        if (no == null) {
            return null; // chegou no fim de um galho sem achar
        }

        if (no.temLetra() && no.getLetra() == letra) {
            return caminho; // achou!
        }

        String resultadoEsquerda = buscarCodigo(no.getEsquerda(), letra, caminho + ".");
        if (resultadoEsquerda != null) {
            return resultadoEsquerda;
        }

        return buscarCodigo(no.getDireita(), letra, caminho + "-");
    }

    // Transforma um texto inteiro em Morse.
    // Exemplo: "SOS OK" -> "... --- ... / --- -.-"
    public String codificarMensagem(String texto) {
        String resultado = "";
        String[] palavras = texto.trim().split(" ");

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                if (!resultado.isEmpty()) {
                    resultado += "/ "; // separa uma palavra da outra
                }

                for (int i = 0; i < palavra.length(); i++) {
                    resultado += codificarLetra(palavra.charAt(i)) + " ";
                }
            }
        }

        return resultado.trim();
    }

    // ===================== IMPRESSÃO =====================

    // Imprime a árvore DEITADA: a raiz fica na esquerda da tela.
    // Gire a cabeça para a esquerda e ela vira uma árvore normal:
    // os traços (direita) ficam em cima e os pontos (esquerda) embaixo.
    public void imprimir() {
        imprimirNo(raiz, 0);
        System.out.println();
        System.out.println("RAIZ = início | * = nó vazio | para cima = traço (-) | para baixo = ponto (.)");
    }

    // Ordem: primeiro o lado direito, depois o próprio nó, depois o lado esquerdo.
    // O nível (profundidade) define quantos espaços vêm antes do nó.
    private void imprimirNo(No no, int nivel) {
        if (no == null) {
            return;
        }

        imprimirNo(no.getDireita(), nivel + 1);

        String espacos = "";
        for (int i = 0; i < nivel; i++) {
            espacos += "        ";
        }
        System.out.println(espacos + rotuloDoNo(no, nivel));

        imprimirNo(no.getEsquerda(), nivel + 1);
    }

    private String rotuloDoNo(No no, int nivel) {
        if (nivel == 0) {
            return "RAIZ";
        }
        if (no.temLetra()) {
            return "|-- " + no.getLetra();
        }
        return "|-- *";
    }
}
