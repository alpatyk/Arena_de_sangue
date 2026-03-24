package org.example.dano;

import org.example.model.Personagem;

public class Dano {

    private int valor;
    private TipoDano tipo;
    private boolean ehCritico;
    private String origem;

    public enum TipoDano {
        FISICO("💥 Físico"),
        MAGICO("🔮 Mágico"),
        PERFURACAO("🏹 Perfuração"),
        VERDADEIRO("✨ Verdadeiro");

        private final String descricao;

        TipoDano(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public Dano(int valor, TipoDano tipo) {
        this(valor, tipo, false, "Ataque");
    }

    public Dano(int valor, TipoDano tipo, String origem) {
        this(valor, tipo, false, origem);
    }

    public Dano(int valor, TipoDano tipo, boolean ehCritico, String origem) {
        this.valor = valor;
        this.tipo = tipo;
        this.ehCritico = ehCritico;
        this.origem = origem;
    }

    public Dano multiplicar(double fator) {
        this.valor = (int)(this.valor * fator);
        return this;
    }

    public Dano adicionarBonus(int bonus) {
        this.valor += bonus;
        return this;
    }

    public Dano aplicarVantagem(double vantagem) {
        this.valor = (int)(this.valor * vantagem);
        return this;
    }

    public int calcularDanoFinal(Personagem alvo) {

        int danoFinal = this.valor;

        switch (this.tipo) {
            case FISICO:
                danoFinal -= alvo.getDefesa();
                break;

            case MAGICO:
                danoFinal -= (int)(alvo.getDefesa() * 0.7);
                break;

            case PERFURACAO:
                danoFinal -= (int)(alvo.getDefesa() * 0.5);
                break;

            case VERDADEIRO:
                // ignora defesa
                break;
        }

        return Math.max(1, danoFinal);
    }

    public void exibir() {
        String criticoStr = ehCritico ? " 💥 CRÍTICO! 💥" : "";

        System.out.println(origem + " causou " + valor + " de dano "
                + tipo.getDescricao() + criticoStr);
    }

    @Override
    public String toString() {
        return origem + " [" + tipo + "] = " + valor + (ehCritico ? " (CRÍTICO)" : "");
    }

    public int getValor() { return valor; }
    public TipoDano getTipo() { return tipo; }
    public boolean isEhCritico() { return ehCritico; }
    public String getOrigem() { return origem; }

    public Dano setValor(int valor) {
        this.valor = valor;
        return this;
    }

    public Dano setEhCritico(boolean ehCritico) {
        this.ehCritico = ehCritico;
        return this;
    }
}