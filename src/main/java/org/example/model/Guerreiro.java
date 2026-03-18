package org.example.model;

public class Guerreiro extends Personagem {
    private int bonusDefesa;

    public Guerreiro(String nome) {
        super(nome, 180, 70, 50, 40, "Guerreiro");
        this.bonusDefesa = 0;
    }

    @Override
    public int habilidadeEspecial() {
        bonusDefesa += 15;
        System.out.println("⚔️ " + getNome() + " usa POSTURA DEFENSIVA! Defesa +" + bonusDefesa);
        return this.getForca() * 2 + bonusDefesa;
    }

    @Override
    public int atacar() {
        System.out.println("🛡️ " + getNome() + " ataca com sua espada!");
        return super.atacar() + 10;
    }

    @Override
    public int defender() {
        int defesaExtra = (int)(Math.random() * 20);
        System.out.println("🛡️ " + getNome() + " levanta o escudo!");
        return this.getDefesa() + defesaExtra;
    }
}