package org.example;

import org.example.model.*;
import org.example.service.Batalha;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ARENA DE SANGUE ===\n");

        System.out.println("Nome do Gladiador 1:");
        String nome1 = scanner.nextLine();
        Personagem p1 = escolherPersonagem(nome1, scanner);

        System.out.println("\nNome do Gladiador 2:");
        String nome2 = scanner.nextLine();
        Personagem p2 = escolherPersonagem(nome2, scanner);

        Batalha batalha = new Batalha(p1, p2);
        batalha.iniciar();

        scanner.close();
    }

    private static Personagem escolherPersonagem(String nome, Scanner scanner) {
        System.out.println("Escolha a classe:");
        System.out.println("1 - Guerreiro 🛡️");
        System.out.println("2 - Mago 🔮");
        System.out.println("3 - Patrulheiro 🏹");

        int escolha = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        switch (escolha) {
            case 1:
                return new Guerreiro(nome);
            case 2:
                return new Mago(nome);
            case 3:
                return new Patrulheiro(nome);
            default:
                System.out.println("Opção inválida! Criando Guerreiro...");
                return new Guerreiro(nome);
        }
    }
}