package org.example.model;

import org.example.dano.Dano;
import org.example.dano.DanoPerfuracao;

public class Patrulheiro extends Personagem {

    private boolean modoFurtivo;
    private int rodadasFurtivo;
    private static final int MAX_FLECHAS = 12;

    public Patrulheiro(String nome) {
        super(nome, 150, 60, 35, 80, 90, "Patrulheiro", MAX_FLECHAS, MAX_FLECHAS);
        this.modoFurtivo = false;
        this.rodadasFurtivo = 0;
    }

    @Override
    public Dano habilidadeEspecial() {
        int escolha = (int)(Math.random() * 2) + 1;

        if (escolha == 1) {
            return flechaPrecisa(); // ✅ sem getValor()
        } else {
            return modoFurtivoHabilidade(); // ✅ sem getValor()
        }
    }

    @Override
    public Dano usarHabilidadeEspecial() {
        return habilidadeEspecial(); // ✅ corrigido
    }

    private Dano flechaPrecisa() {
        if (getRecursoEspecial() >= 3) {

            setRecursoEspecial(getRecursoEspecial() - 3);

            int valor = getForca() * 2 + getAgilidade() + (int)(Math.random() * 25);
            boolean ehCritico = Math.random() < 0.25;

            DanoPerfuracao dano = new DanoPerfuracao(valor, 15, "Flecha Precisa");
            dano.setEhCritico(ehCritico);

            System.out.println("🎯 " + getNome() + " dispara FLECHA PRECISA!");

            return dano;

        } else {
            return criarDanoNormal();
        }
    }

    private Dano modoFurtivoHabilidade() {
        if (getRecursoEspecial() >= 5 && !modoFurtivo) {

            setRecursoEspecial(getRecursoEspecial() - 5);

            modoFurtivo = true;
            rodadasFurtivo = 2;

            System.out.println("👤 " + getNome() + " entrou em modo furtivo!");

            return new Dano(0, Dano.TipoDano.PERFURACAO, false, "Modo Furtivo");

        } else {
            return criarDanoNormal();
        }
    }

    @Override
    public Dano atacar() {

        if (modoFurtivo) return ataqueFurtivo();
        if (getRecursoEspecial() > 0) return ataqueComArco();

        return ataqueComAdaga();
    }

    private Dano ataqueFurtivo() {

        rodadasFurtivo--;

        if (rodadasFurtivo <= 0) {
            modoFurtivo = false;
        }

        System.out.println("🗡️ " + getNome() + " ataca das sombras!");

        Dano dano = criarDanoNormal();
        dano.multiplicar(2);
        dano.setEhCritico(true);

        return dano; // ✅ corrigido
    }

    private Dano ataqueComArco() {

        setRecursoEspecial(getRecursoEspecial() - 1);

        System.out.println("🏹 " + getNome() + " dispara uma flecha!");

        Dano dano = criarDanoNormal();

        if (Math.random() < 0.1) {
            dano.setEhCritico(true);
            dano.multiplicar(1.5);
        }

        return dano; // ✅ corrigido
    }

    private Dano ataqueComAdaga() {

        int danoBase = getForca() + (int)(Math.random() * 10);

        return new Dano(
                (int)(danoBase * 0.5),
                Dano.TipoDano.FISICO,
                false,
                "Adaga"
        );
    }

    @Override
    public int defender() {
        if (modoFurtivo) {
            return getAgilidade() * 2;
        } else {
            return getDefesa() + (getAgilidade() / 2);
        }
    }
}