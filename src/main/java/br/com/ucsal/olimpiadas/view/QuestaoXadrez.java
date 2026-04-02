package br.com.ucsal.olimpiadas.view;

import br.com.ucsal.olimpiadas.Questao;

public class QuestaoXadrez extends Questao implements ComDica {
	private String[] alternativas = new String[5];
    private char alternativaCorreta;
    private String fenInicial;
    private String dica;

    public void setDica(String dica) {
        this.dica = dica;
    }

    public char getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public String getFenInicial() {
        return fenInicial;
    }

    public void setAlternativas(String[] alternativas) {
        if (alternativas == null || alternativas.length != 5) {
            throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
        }
        this.alternativas = Arrays.copyOf(alternativas, 5);
    }

    public void setAlternativaCorreta(char alternativaCorreta) {
        this.alternativaCorreta = normalizar(alternativaCorreta);
    }

    public void setFenInicial(String fenInicial) {
        this.fenInicial = fenInicial;
    }

    public static char normalizar(char c) {
        char up = Character.toUpperCase(c);
        if (up < 'A' || up > 'E') {
            throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
        }
        return up;
    }

    public boolean isRespostaCorreta(char marcada) {
        return normalizar(marcada) == alternativaCorreta;
    }

    @Override
    public void exibirParaAluno() {
        System.out.println(getEnunciado());
        System.out.println("Posição inicial: ");
        Xadrez.imprimirTabuleiroFen(fenInicial);
        for(String alt : alternativas){
            System.out.println(alt);
        }
    }

    @Override
    public boolean verificaResposta(String entrada) {
        try {
            char marcada = Character.toUpperCase(entrada.trim().charAt(0));
            return marcada == alternativaCorreta;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String obterDica() {
        return this.dica;
    }
}
