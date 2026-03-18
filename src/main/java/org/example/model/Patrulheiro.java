package org.example.model;

public class Patrulheiro extends Personagem {
    private int flechas;
    private final int MAX_FLECHAS = 12;
    private boolean modoFurtivo;
    private int rodadasFurtivo;

    public Patrulheiro(String nome) {
        super(nome, 150, 60, 35, 80, "Patrulheiro");
        this.flechas = MAX_FLECHAS;
        this.modoFurtivo = false;
        this.rodadasFurtivo = 0;
    }

    @Override
    public int habilidadeEspecial() {
        System.out.println("Escolha a habilidade:");
        System.out.println("1 - Flecha Precisa (3 flechas)");
        System.out.println("2 - Modo Furtivo (5 flechas, dura 2 rodadas)");

        // Simulando escolha (em produção, receberia do jogador)
        int escolha = (int)(Math.random() * 2) + 1;

        if (escolha == 1 && flechas >= 3) {
            flechas -= 3;
            System.out.println("🎯 " + getNome() + " dispara FLECHA PRECISA! (Flechas: " + flechas + "/" + MAX_FLECHAS + ")");
            return this.getForca() * 2 + this.getAgilidade();

        } else if (escolha == 2 && flechas >= 5 && !modoFurtivo) {
            flechas -= 5;
            modoFurtivo = true;
            rodadasFurtivo = 2;
            System.out.println("👤 " + getNome() + " entra em MODO FURTIVO por 2 rodadas! (Flechas: " + flechas + "/" + MAX_FLECHAS + ")");
            return 0; // Não causa dano, apenas entra em modo furtivo

        } else {
            if (escolha == 2 && modoFurtivo) {
                System.out.println("⚠️ " + getNome() + " já está em modo furtivo!");
            } else {
                System.out.println("⚠️ " + getNome() + " sem flechas suficientes!");
            }
            return atacar();
        }
    }

    @Override
    public int atacar() {
        if (modoFurtivo) {
            // Ataque furtivo não gasta flecha
            System.out.println("🗡️ " + getNome() + " ataca das SOMBRAS! (Ataque crítico)");
            rodadasFurtivo--;
            if (rodadasFurtivo <= 0) {
                modoFurtivo = false;
                System.out.println("👤 " + getNome() + " saiu do modo furtivo");
            }
            return super.atacar() * 2;

        } else if (flechas > 0) {
            // Ataque normal com flecha
            flechas--;
            System.out.println("🏹 " + getNome() + " dispara uma flecha! (Flechas: " + flechas + "/" + MAX_FLECHAS + ")");
            return super.atacar() + 5;

        } else {
            // Sem flechas - ataque fraco com adaga
            System.out.println("🔪 " + getNome() + " ataca com adaga! (Sem flechas)");
            return (int)(this.getForca() * 0.5);
        }
    }

    @Override
    public int defender() {
        if (modoFurtivo) {
            System.out.println("💨 " + getNome() + " usa a furtividade para desviar!");
            return this.getAgilidade() * 2;
        } else {
            System.out.println("🛡️ " + getNome() + " tenta se esquivar!");
            return this.getDefesa() + this.getAgilidade() / 2;
        }
    }

    // Método especial para recarregar flechas
    public void recarregar() {
        this.flechas = MAX_FLECHAS;
        System.out.println("🏹 " + getNome() + " recarregou todas as flechas!");
    }

    public int getFlechas() {
        return flechas;
    }

    public boolean isModoFurtivo() {
        return modoFurtivo;
    }
}