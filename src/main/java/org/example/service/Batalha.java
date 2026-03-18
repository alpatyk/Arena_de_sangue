package org.example.service;

import org.example.model.Personagem;
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
        System.out.println("\n⚔️ BATALHA INICIADA ⚔️");
        System.out.println(p1);
        System.out.println("VS");
        System.out.println(p2);
        System.out.println("===================\n");

        while (!batalhaTerminou()) {
            rodada();
        }

        declararVencedor();
    }

    private void rodada() {
        System.out.println("\n--- RODADA ---");
        System.out.println(p1);
        System.out.println(p2);

        // Quem ataca primeiro (baseado na agilidade)
        if (p1.getAgilidade() >= p2.getAgilidade()) {
            executarTurno(p1, p2);
            if (p2.estaVivo()) {
                executarTurno(p2, p1);
            }
        } else {
            executarTurno(p2, p1);
            if (p1.estaVivo()) {
                executarTurno(p1, p2);
            }
        }
    }

    private void executarTurno(Personagem atacante, Personagem defensor) {
        System.out.println("\n🎮 Vez de " + atacante.getNome());
        System.out.println("1 - Ataque Normal");
        System.out.println("2 - Habilidade Especial");
        System.out.println("3 - Defender");

        int escolha = scanner.nextInt();
        int dano = 0;

        switch (escolha) {
            case 1:
                dano = atacante.atacar();
                break;
            case 2:
                dano = atacante.habilidadeEspecial();
                break;
            case 3:
                atacante.defender();
                System.out.println(atacante.getNome() + " se defendeu!");
                return;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        defensor.receberDano(dano);
    }

    private boolean batalhaTerminou() {
        return !p1.estaVivo() || !p2.estaVivo();
    }

    private void declararVencedor() {
        if (!p1.estaVivo()) {
            System.out.println("\n🏆 " + p2.getNome() + " VENCEU! 🏆");
        } else {
            System.out.println("\n🏆 " + p1.getNome() + " VENCEU! 🏆");
        }
    }
}