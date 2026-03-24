package org.example.service;

import org.example.model.Personagem;
import org.example.dano.Dano;

import java.util.Scanner;

public class Batalha {

    private Personagem p1;
    private Personagem p2;
    private Scanner scanner;

    public Batalha(Personagem p1, Personagem p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {

        System.out.println("⚔️ Batalha entre " + p1.getNome() + " e " + p2.getNome() + "!\n");

        int turno = 1;

        while (p1.getHp() > 0 && p2.getHp() > 0) {

            System.out.println("\n===== TURNO " + turno + " =====");

            executarTurno(p1, p2);
            if (p2.getHp() <= 0) break;

            executarTurno(p2, p1);
            if (p1.getHp() <= 0) break;

            turno++;
        }

        System.out.println("\n🏁 FIM DA BATALHA!");

        if (p1.getHp() > 0) {
            System.out.println("🏆 " + p1.getNome() + " venceu!");
        } else {
            System.out.println("🏆 " + p2.getNome() + " venceu!");
        }
    }

    private void executarTurno(Personagem atacante, Personagem defensor) {

        System.out.println("\n🎮 Vez de " + atacante.getNome());
        System.out.println("1 - Ataque Normal");
        System.out.println("2 - Habilidade Especial");
        System.out.println("3 - Defender");
        System.out.print("Escolha: ");

        int escolha = scanner.nextInt();

        switch (escolha) {

            case 1:
                Dano danoNormal = atacante.atacar();
                defensor.receberDano(danoNormal);
                break;

            case 2:
                Dano danoEspecial = atacante.usarHabilidadeEspecial();
                defensor.receberDano(danoEspecial);
                break;

            case 3:
                int defesa = atacante.defender();
                System.out.println(atacante.getNome() + " se preparou para defender!");
                break;

            default:
                System.out.println("Opção inválida! Perdendo turno...");
        }
    }
}