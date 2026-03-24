package org.example.model;

import org.example.dano.*;

public abstract class Personagem {

    private String nome;
    private int hp;
    private int hpMax;
    private int forca;
    private int defesa;
    private int agilidade;
    private String classe;
    private int recursoEspecial;
    private int recursoMaximo;

    public Personagem(String nome, int hp, int hpMax, int forca, int defesa, int agilidade,
                      String classe, int recursoEspecial, int recursoMaximo) {
        this.nome = nome;
        this.hp = hp;
        this.hpMax = hpMax;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.classe = classe;
        this.recursoEspecial = recursoEspecial;
        this.recursoMaximo = recursoMaximo;
    }

    public String getNome() { return nome; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getForca() { return forca; }
    public int getDefesa() { return defesa; }
    public int getAgilidade() { return agilidade; }
    public String getClasse() { return classe; }
    public int getRecursoEspecial() { return recursoEspecial; }
    public int getRecursoMaximo() { return recursoMaximo; }

    public void setForca(int forca) { this.forca = forca; }
    public void setRecursoEspecial(int recursoEspecial) { this.recursoEspecial = recursoEspecial; }

    public void receberDano(Dano dano) {

        if (dano.isEhCritico()) {
            System.out.println("💥 GOLPE CRÍTICO! 💥");
        }

        dano.exibir();

        int danoFinal = dano.calcularDanoFinal(this);

        this.hp = Math.max(0, this.hp - danoFinal);

        System.out.println(nome + " recebeu " + danoFinal + " de dano! HP: "
                + hp + "/" + hpMax);
    }

    public Dano criarDanoNormal() {
        int valor = this.forca + (int)(Math.random() * 10);
        boolean ehCritico = Math.random() < 0.1;

        return new Dano(
                valor,
                Dano.TipoDano.FISICO,
                ehCritico,
                "Ataque Normal"
        );
    }

    public Dano atacar() {
        return criarDanoNormal();
    }

    public abstract Dano habilidadeEspecial();

    public Dano usarHabilidadeEspecial() {
        return habilidadeEspecial();
    }

    public abstract int defender();
}