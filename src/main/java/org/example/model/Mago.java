package org.example.model;

public class Mago extends Personagem {
    private int mana;

    public Mago(String nome) {
        super(nome, 120, 80, 20, 60, "Mago");
        this.mana = 100;
    }

    @Override
    public int habilidadeEspecial() {
        if (mana >= 30) {
            mana -= 30;
            System.out.println("🔮 " + getNome() + " conjura BOLA DE FOGO! Mana: " + mana);
            return this.getForca() * 3 + (int)(Math.random() * 25);
        } else {
            System.out.println("⚠️ " + getNome() + " sem mana! Usa ataque normal.");
            return atacar();
        }
    }

    @Override
    public int atacar() {
        mana = Math.min(100, mana + 5);
        System.out.println("✨ " + getNome() + " lança seta arcana! Mana: " + mana);
        return this.getForca() + (int)(Math.random() * 15);
    }

    @Override
    public int defender() {
        int escudoMagico = (int)(Math.random() * 30);
        System.out.println("🛡️ " + getNome() + " cria barreira mágica!");
        return escudoMagico;
    }
}