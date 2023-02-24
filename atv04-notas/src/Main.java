import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Quantas notas vai colocar?");
        int qtdNotas = in.nextInt();
        System.out.println("--------------------");

        float somaNotas = 0;
        for (int i = 0; i < qtdNotas; i++) {
            System.out.println("Digite a " + (i+1) + "ª nota:");
            float nota = in.nextFloat();
            somaNotas += nota;
        }
        float media = somaNotas/qtdNotas;
        System.out.println("A média das notas é: " + media);
        if (media > 7) {
            System.out.println("Aluno aprovado!");
        } else {
            System.out.println("Aluno reprovado!");
        }
    }
}