import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your height in meters:");
        float height = in.nextFloat();
        System.out.println("--------------------");

        System.out.println("Enter your weight in kgs:");
        double weight = in.nextDouble();
        System.out.println("--------------------");

        double imc = weight / (height * height);
        System.out.println("Your IMC is " + Math.round(imc) + "!");

        if(imc < 18.5) {
            System.out.println("Underweight");
        } else if (imc >= 18.5 && imc <= 24.9) {
            System.out.println("Normal weight");
        } else if (imc >= 25 && imc <= 29.9) {
            System.out.println("Overweight");
        } else if (imc >= 30 && imc <= 34.9) {
            System.out.println("Obesity 1st degree");
        } else if (imc >= 35 && imc <= 39.9) {
            System.out.println("Obesity 2nd degree");
        } else {
            System.out.println("Obesity 3rd degree");
        }
    }
}