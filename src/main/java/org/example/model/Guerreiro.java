package org.example.model;

public class Guerreiro extends Personagem {
    private int furia;
    private final int FURIA_MAXIMA = 100;
    private final int CUSTO_ESPECIAL = 40;

    public Guerreiro(String nome) {
        super(nome, 180, 70, 50, 40, "Guerreiro");
        this.furia = 0;
    }

    @Override
    public int habilidadeEspecial() {
        if (furia >= CUSTO_ESPECIAL) {
            furia -= CUSTO_ESPECIAL;
            System.out.println("⚔️ " + getNome() + " usa GOLPE FURIOSO! (Fúria: " + furia + "/" + FURIA_MAXIMA + ")");
            return this.getForca() * 2 + 20;
        } else {
            System.out.println("⚠️ " + getNome() + " não tem fúria suficiente! (Fúria: " + furia + "/" + FURIA_MAXIMA + ")");
            return atacar();
        }
    }

    @Override
    public int atacar() {
        // Guerreiro ganha fúria ao atacar
        furia = Math.min(FURIA_MAXIMA, furia + 15);
        System.out.println("🛡️ " + getNome() + " ataca com sua espada! (Fúria: +15)");
        return super.atacar() + 10;
    }

    @Override
    public int defender() {
        // Defender também gera um pouco de fúria
        furia = Math.min(FURIA_MAXIMA, furia + 5);
        System.out.println("🛡️ " + getNome() + " levanta o escudo! (Fúria: +5)");
        return this.getDefesa() + (int)(Math.random() * 20);
    }

    // Getter para a barra de fúria
    public int getFuria() {
        return furia;
    }
}