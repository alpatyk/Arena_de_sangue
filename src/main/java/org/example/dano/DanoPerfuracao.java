package org.example.dano;

import org.example.model.Personagem;

public class DanoPerfuracao extends Dano {
    private int penetracao;
    private boolean ignoraEscudo;

    public DanoPerfuracao(int valor, int penetracao) {
        super(valor, TipoDano.PERFURACAO);
        this.penetracao = penetracao;
        this.ignoraEscudo = false;
    }

    public DanoPerfuracao(int valor, int penetracao, String origem) {
        super(valor, TipoDano.PERFURACAO, origem);
        this.penetracao = penetracao;
        this.ignoraEscudo = false;
    }

    public DanoPerfuracao(int valor, int penetracao, boolean ehCritico, String origem) {
        super(valor, TipoDano.PERFURACAO, ehCritico, origem);
        this.penetracao = penetracao;
        this.ignoraEscudo = false;
    }

    @Override
    public int calcularDanoFinal(Personagem alvo) {
        int defesaReduzida = alvo.getDefesa() - penetracao;
        defesaReduzida = Math.max(0, defesaReduzida);

        if (ignoraEscudo) {
            System.out.println("⚡ Ataque perfurou a defesa!");
        }

        int danoFinal = getValor() - defesaReduzida;
        return Math.max(1, danoFinal);
    }

    public void setIgnoraEscudo(boolean ignora) {
        this.ignoraEscudo = ignora;
    }

    public int getPenetracao() {
        return penetracao;
    }
}
