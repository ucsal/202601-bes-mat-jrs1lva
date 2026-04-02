package br.com.ucsal.olimpiadas.interfaces;

import br.com.ucsal.olimpiadas.Participante;

public interface OlimpiadasRepository {
	void salvarParticipante(Participante p);
    List<Participante> listarParticipantes();
    Participante buscarParticipantePorId(long id);

    void salvarProva(Prova p);
    List<Prova> listarProvas();
    Prova buscarProvaPorId(long id);

    void salvarQuestao(Questao q);
    List<Questao> buscarQuestoesPorProva(long provaId);

    void salvarTentativa(Tentativa t);
    List<Tentativa> listarTentativas();
}
