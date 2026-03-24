package org.example.model;

import org.example.dano.*;

import static java.lang.Math.random;

public class Mago extends Personagem {

    private int mana;
    private final int MANA_MAXIMA = 100;
    private final int CUSTO_BOLA_FOGO = 35;
    private final int CUSTO_RAIO = 45;

    public Mago(String nome) {
        super(nome, 120, 80, 20, 60, 35, "Mago", 0, 100);
        this.mana = 100;
    }

    @Override
    public Dano habilidadeEspecial() {
        System.out.println("Escolha a magia:");
        System.out.println("1 - Bola de Fogo (35 mana)");
        System.out.println("2 - Raio (45 mana)");

        int escolha = (int)(random() * 2) + 1;

        if (escolha == 1 && mana >= CUSTO_BOLA_FOGO) {
            mana -= CUSTO_BOLA_FOGO;

            int valor = this.getForca() * 2 + (int)(random() * 30);

            System.out.println("🔮 " + getNome() + " conjura BOLA DE FOGO! (Mana: " + mana + "/" + MANA_MAXIMA + ")");

            return new Dano(
                    valor,
                    Dano.TipoDano.MAGICO,
                    false,
                    "Bola de Fogo"
            );

        } else if (escolha == 2 && mana >= CUSTO_RAIO) {
            mana -= CUSTO_RAIO;

            int valor = this.getForca() * 3 + (int)(random() * 20);

            System.out.println("⚡ " + getNome() + " conjura RAIO! (Mana: " + mana + "/" + MANA_MAXIMA + ")");

            return new Dano(
                    valor,
                    Dano.TipoDano.MAGICO,
                    false,
                    "Raio"
            );

        } else {
            System.out.println("⚠️ " + getNome() + " sem mana suficiente! (Mana: " + mana + "/" + MANA_MAXIMA + ")");
            return atacar();
        }
    }

    @Override
    public Dano usarHabilidadeEspecial() {
        return habilidadeEspecial();
    }

    @Override
    public Dano atacar() {
        mana = Math.min(MANA_MAXIMA, mana + 8);

        System.out.println("✨ " + getNome() + " lança Seta Arcana! (Mana: +8)");

        int valor = this.getForca() + (int)(random() * 15);

        return new Dano(
                valor,
                Dano.TipoDano.MAGICO,
                false,
                "Seta Arcana"
        );
    }

    @Override
    public int defender() {
        if (mana >= 10) {
            mana -= 10;
            System.out.println("🛡️ " + getNome() + " cria barreira mágica! (Mana: -10)");
            return (int)(random() * 40);
        } else {
            System.out.println("⚠️ " + getNome() + " sem mana para defesa mágica!");
            return 5;
        }
    }

    public int getMana() {
        return mana;
    }
}