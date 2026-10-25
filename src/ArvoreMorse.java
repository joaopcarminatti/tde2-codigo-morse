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
}
