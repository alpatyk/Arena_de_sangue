package org.example.dano;

import org.example.model.Personagem;

public class DanoMagico extends Dano {
    private int custoMana;
    private boolean temEfeitoQueimadura;

    public DanoMagico(int valor, int custoMana) {
        super(valor, TipoDano.MAGICO);
        this.custoMana = custoMana;
        this.temEfeitoQueimadura = false;
    }

    public DanoMagico(int valor, int custoMana, String origem) {
        super(valor, TipoDano.MAGICO, origem);
        this.custoMana = custoMana;
        this.temEfeitoQueimadura = false;
    }

    public DanoMagico(int valor, int custoMana, boolean ehCritico, String origem) {
        super(valor, TipoDano.MAGICO, ehCritico, origem);
        this.custoMana = custoMana;
        this.temEfeitoQueimadura = false;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public boolean podeConjurar(int manaAtual) {
        return manaAtual >= custoMana;
    }

    // Método corrigido - agora funciona corretamente
    public void aplicarQueimadura(Personagem alvo) {
        if (temEfeitoQueimadura) {
            System.out.println("🔥 " + alvo.getNome() + " sofreu queimadura!");

        }
    }

    public void setTemEfeitoQueimadura(boolean temEfeito) {
        this.temEfeitoQueimadura = temEfeito;
    }

    public boolean hasEfeitoQueimadura() {
        return temEfeitoQueimadura;
    }
}