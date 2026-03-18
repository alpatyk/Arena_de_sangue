package org.example.model;

public class Patrulheiro extends Personagem {
    private boolean modoFurtivo;
    private int flechas;

    public Patrulheiro(String nome) {
        super(nome, 150, 60, 35, 80, "Patrulheiro");
        this.modoFurtivo = false;
        this.flechas = 10;
    }

    @Override
    public int habilidadeEspecial() {
        if (flechas >= 3) {
            flechas -= 3;
            modoFurtivo = true;
            System.out.println("🏹 " + getNome() + " dispara FLECHA PRECISA! Flechas: " + flechas);
            return this.getForca() * 2 + this.getAgilidade();
        } else {
            System.out.println("⚠️ " + getNome() + " sem flechas! Usa ataque normal.");
            return atacar();
        }
    }

    @Override
    public int atacar() {
        if (modoFurtivo) {
            modoFurtivo = false;
            System.out.println("🗡️ " + getNome() + " ataca das SOMBRAS!");
            return super.atacar() * 2;
        } else {
            System.out.println("🏹 " + getNome() + " dispara uma flecha!");
            return super.atacar();
        }
    }

    @Override
    public int defender() {
        int esquiva = this.getAgilidade() / 2;
        System.out.println("💨 " + getNome() + " tenta esquivar!");
        return this.getDefesa() + esquiva;
    }
}