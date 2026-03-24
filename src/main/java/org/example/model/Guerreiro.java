package org.example.model;

import org.example.dano.*;

public class Guerreiro extends Personagem {

    private int bonusDefesa;
    private static final int FURIA_MAXIMA = 100;
    private static final int CUSTO_ESPECIAL = 40;

    public Guerreiro(String nome) {
        super(nome, 180, 70, 50, 40, 80, "Guerreiro", FURIA_MAXIMA, CUSTO_ESPECIAL);
        this.bonusDefesa = 0;
    }

    @Override
    public Dano habilidadeEspecial() {
        if (getRecursoEspecial() >= CUSTO_ESPECIAL) {

            setRecursoEspecial(getRecursoEspecial() - CUSTO_ESPECIAL);

            int valor = getForca() * 2 + bonusDefesa + (int)(Math.random() * 20);
            boolean ehCritico = Math.random() < 0.15;

            bonusDefesa += 10;

            Dano dano = new Dano(valor, Dano.TipoDano.FISICO, ehCritico, "Golpe Furioso");

            System.out.println("⚔️ " + getNome() + " usa GOLPE FURIOSO!");
            System.out.println("   Fúria restante: " + getRecursoEspecial() + "/" + FURIA_MAXIMA);
            System.out.println("   Bônus de fúria acumulado: +" + bonusDefesa);

            if (ehCritico) {
                System.out.println("   💥 GOLPE CRÍTICO! 💥");
            }

            return dano;

        } else {
            System.out.println("⚠️ " + getNome() + " não tem fúria suficiente!");
            return atacar();
        }
    }

    @Override
    public Dano usarHabilidadeEspecial() {
        return habilidadeEspecial();
    }

    @Override
    public Dano atacar() {

        int novaFuria = Math.min(FURIA_MAXIMA, getRecursoEspecial() + 15);
        setRecursoEspecial(novaFuria);

        System.out.println("🛡️ " + getNome() + " ataca com sua espada!");
        System.out.println("   Fúria: +15 (Total: " + getRecursoEspecial() + "/" + FURIA_MAXIMA + ")");

        int valor = getForca() + (int)(Math.random() * 15);

        boolean ehCritico = Math.random() < 0.1;

        if (ehCritico) {
            valor *= 1.5;
            System.out.println("   💥 ACERTO CRÍTICO! 💥");
        }

        Dano dano = new Dano(valor + 5, Dano.TipoDano.FISICO, ehCritico, "Ataque com Espada");

        return dano;
    }

    @Override
    public int defender() {

        int novaFuria = Math.min(FURIA_MAXIMA, getRecursoEspecial() + 5);
        setRecursoEspecial(novaFuria);

        boolean posturaDefensiva = Math.random() < 0.3;

        int bonus = (int)(Math.random() * 20);
        int defesaTotal = getDefesa() + bonus;

        if (posturaDefensiva) {
            defesaTotal += 15;
            System.out.println("🛡️ " + getNome() + " entra em POSTURA DEFENSIVA!");
        }

        System.out.println("🛡️ " + getNome() + " levanta o escudo!");
        System.out.println("   Defesa total: " + defesaTotal);
        System.out.println("   Fúria: +5 (Total: " + getRecursoEspecial() + "/" + FURIA_MAXIMA + ")");

        return defesaTotal;
    }

    public Dano ataqueFurioso() {
        if (getRecursoEspecial() >= 30) {

            int furiaGasta = Math.min(getRecursoEspecial(), 50);
            setRecursoEspecial(getRecursoEspecial() - furiaGasta);

            int valor = getForca() * 2 + furiaGasta + (int)(Math.random() * 25);

            System.out.println("⚔️ " + getNome() + " usa ATAQUE FURIOSO!");
            System.out.println("   Gasta " + furiaGasta + " de fúria!");

            return new Dano(valor, Dano.TipoDano.FISICO, false, "Ataque Furioso");

        } else {
            System.out.println("⚠️ Fúria insuficiente!");
            return atacar();
        }
    }

    public Dano gritoDeGuerra() {
        if (getRecursoEspecial() >= 20) {

            setRecursoEspecial(getRecursoEspecial() - 20);

            int forcaBonus = 15;
            setForca(getForca() + forcaBonus);

            System.out.println("📢 " + getNome() + " usa GRITO DE GUERRA!");
            System.out.println("   Força aumentada!");

            return new Dano(0, Dano.TipoDano.FISICO, false, "Grito de Guerra");

        } else {
            System.out.println("⚠️ Fúria insuficiente!");
            return atacar();
        }
    }

    public void posturaImpenetravel() {
        if (getRecursoEspecial() >= 30) {
            setRecursoEspecial(getRecursoEspecial() - 30);
            bonusDefesa += 30;

            System.out.println("🛡️ " + getNome() + " entra em POSTURA IMPENETRÁVEL!");
        }
    }

    public int getFuria() {
        return getRecursoEspecial();
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public void resetarBonusDefesa() {
        this.bonusDefesa = 0;
    }
}