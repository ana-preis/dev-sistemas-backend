import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Digite uma frase: ");
        String frase = in.nextLine();
        System.out.println("--------------------");
        System.out.println(frase);

        String[] arrayPalavras = frase.split(" ");
        System.out.println(Arrays.toString(arrayPalavras));
        int qtdePalavras = arrayPalavras.length;
        System.out.println("Quantidade de palavras: " + qtdePalavras);
    }
}