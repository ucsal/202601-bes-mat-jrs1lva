package br.com.ucsal.olimpiadas.interfaces;

import java.util.List;

import br.com.ucsal.olimpiadas.Participante;
import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.Tentativa;

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
