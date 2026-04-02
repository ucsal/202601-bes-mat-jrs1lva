package br.com.ucsal.olimpiadas;

public class Resposta {

	private long questaoId;
	private String respostaFornecida;
	private boolean correta;

	public long getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(long questaoId) {
		this.questaoId = questaoId;
	}

    public String getRespostaFornecida() {
        return respostaFornecida;
    }

    public void setRespostaFornecida(String respostaFornecida) {
        this.respostaFornecida = respostaFornecida;
    }

    public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

}
