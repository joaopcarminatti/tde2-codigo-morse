import java.util.Scanner;

/**
 * Programa principal: cria a árvore e mostra o menu no console.
 */
public class Main {

    public static void main(String[] args) {
        ArvoreMorse arvore = new ArvoreMorse();
        Scanner teclado = new Scanner(System.in);
        String opcao = "";

        while (!opcao.equals("0")) {
            mostrarMenu();
            opcao = teclado.nextLine().trim();

            if (opcao.equals("1")) {
                decodificar(teclado, arvore);
            } else if (opcao.equals("2")) {
                codificar(teclado, arvore);
            } else if (opcao.equals("0")) {
                System.out.println("Até mais!");
            } else {
                System.out.println("Opção inválida.");
            }
        }

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== CÓDIGO MORSE =====");
        System.out.println("1 - Decodificar (Morse -> texto)");
        System.out.println("2 - Codificar (texto -> Morse)");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void decodificar(Scanner teclado, ArvoreMorse arvore) {
        System.out.println("Digite o código (letras separadas por espaço, palavras por /):");
        System.out.println("Exemplo: ... --- ... / --- -.-");
        System.out.print("> ");
        String mensagem = teclado.nextLine();

        System.out.println("Resultado: " + arvore.decodificarMensagem(mensagem));
    }

    private static void codificar(Scanner teclado, ArvoreMorse arvore) {
        System.out.println("Digite o texto (letras A-Z e números 0-9):");
        System.out.print("> ");
        String texto = teclado.nextLine();

        System.out.println("Resultado: " + arvore.codificarMensagem(texto));
    }
}
