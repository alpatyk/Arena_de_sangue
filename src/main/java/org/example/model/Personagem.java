package org.example.model;

public abstract class Personagem {
    private String nome;
    private int hp;
    private int hpMax;
    private int forca;
    private int defesa;
    private int agilidade;
    private String classe;

    public Personagem(String nome, int hp, int forca, int defesa, int agilidade, String classe) {
        this.nome = nome;
        this.hp = hp;
        this.hpMax = hp;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.classe = classe;
    }

    // Métodos abstratos
    public abstract int habilidadeEspecial();
    public abstract int defender();

    // Métodos concretos
    public int atacar() {
        return this.forca + (int)(Math.random() * 10);
    }

    public void receberDano(int dano) {
        int danoCalculado = Math.max(0, dano - this.defesa);
        this.hp = Math.max(0, this.hp - danoCalculado);
        System.out.println(this.nome + " recebeu " + danoCalculado + " de dano! HP: " + this.hp + "/" + this.hpMax);
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getHpMax() { return hpMax; }

    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }

    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    public int getAgilidade() { return agilidade; }
    public void setAgilidade(int agilidade) { this.agilidade = agilidade; }

    public String getClasse() { return classe; }

    public boolean estaVivo() {
        return hp > 0;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - %d/%d HP", nome, classe, hp, hpMax);
    }
}