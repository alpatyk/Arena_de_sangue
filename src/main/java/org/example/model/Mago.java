package org.example.model;

public class Mago extends Personagem {
    private int mana;
    private final int MANA_MAXIMA = 100;
    private final int CUSTO_BOLA_FOGO = 35;
    private final int CUSTO_RAIO = 45;

    public Mago(String nome) {
        super(nome, 120, 80, 20, 60, "Mago");
        this.mana = 100; // Começa com mana cheia
    }

    @Override
    public int habilidadeEspecial() {
        System.out.println("Escolha a magia:");
        System.out.println("1 - Bola de Fogo (35 mana)");
        System.out.println("2 - Raio (45 mana)");

        // Aqui você precisaria receber a escolha do jogador
        // Por simplicidade, vou usar um random
        int escolha = (int)(Math.random() * 2) + 1;

        if (escolha == 1 && mana >= CUSTO_BOLA_FOGO) {
            mana -= CUSTO_BOLA_FOGO;
            System.out.println("🔮 " + getNome() + " conjura BOLA DE FOGO! (Mana: " + mana + "/" + MANA_MAXIMA + ")");
            return this.getForca() * 2 + (int)(Math.random() * 30);

        } else if (escolha == 2 && mana >= CUSTO_RAIO) {
            mana -= CUSTO_RAIO;
            System.out.println("⚡ " + getNome() + " conjura RAIO! (Mana: " + mana + "/" + MANA_MAXIMA + ")");
            return this.getForca() * 3 + (int)(Math.random() * 20);

        } else {
            System.out.println("⚠️ " + getNome() + " sem mana suficiente! (Mana: " + mana + "/" + MANA_MAXIMA + ")");
            return atacar();
        }
    }

    @Override
    public int atacar() {
        // Mago recupera mana ao atacar
        mana = Math.min(MANA_MAXIMA, mana + 8);
        System.out.println("✨ " + getNome() + " lança seta arcana! (Mana: +8)");
        return this.getForca() + (int)(Math.random() * 15);
    }

    @Override
    public int defender() {
        // Defesa mágica gasta mana
        if (mana >= 10) {
            mana -= 10;
            System.out.println("🛡️ " + getNome() + " cria barreira mágica! (Mana: -10)");
            return (int)(Math.random() * 40); // Defesa forte mas gasta mana
        } else {
            System.out.println("⚠️ " + getNome() + " sem mana para defesa mágica!");
            return 5; // Defesa fraca sem mana
        }
    }

    public int getMana() {
        return mana;
    }
}