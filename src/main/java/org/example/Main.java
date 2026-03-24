package org.example;

import org.example.model.*;
import org.example.service.Batalha;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ARENA DE SANGUE ===\n");

        System.out.print("Nome do Gladiador 1: ");
        String nome1 = scanner.nextLine();
        Personagem p1 = escolherPersonagem(nome1, scanner);

        System.out.print("\nNome do Gladiador 2: ");
        String nome2 = scanner.nextLine();
        Personagem p2 = escolherPersonagem(nome2, scanner);

        System.out.println("\n🔥 INICIANDO BATALHA 🔥\n");

        Batalha batalha = new Batalha(p1, p2);
        batalha.iniciar();

        scanner.close();
    }

    private static Personagem escolherPersonagem(String nome, Scanner scanner) {

        int escolha = 0;

        while (escolha < 1 || escolha > 3) {
            System.out.println("\nEscolha a classe para " + nome + ":");
            System.out.println("1 - Guerreiro 🛡️");
            System.out.println("2 - Mago 🔮");
            System.out.println("3 - Patrulheiro 🏹");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
            } else {
                System.out.println("❌ Digite um número válido!");
                scanner.nextLine();
            }
        }

        switch (escolha) {
            case 1:
                System.out.println("🛡️ " + nome + " escolhido como Guerreiro!");
                return new Guerreiro(nome);

            case 2:
                System.out.println("🔮 " + nome + " escolhido como Mago!");
                return new Mago(nome);

            case 3:
                System.out.println("🏹 " + nome + " escolhido como Patrulheiro!");
                return new Patrulheiro(nome);

            default:

                return new Guerreiro(nome);
        }
    }
}