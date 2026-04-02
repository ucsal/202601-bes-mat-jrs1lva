package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.olimpiadas.Participante;
import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.interfaces.OlimpiadasRepository;

public class DataBase implements OlimpiadasRepository {
	public static long proximoParticipanteId = 1;
	public static long proximaProvaId = 1;
	public static long proximaQuestaoId = 1;
	public static long proximaTentativaId = 1;

	public static final List<Participante> participantes = new ArrayList<>();
	public static final List<Prova> provas = new ArrayList<>();
	public static final List<Questao> questoes = new ArrayList<>();
	public static final List<Tentativa> tentativas = new ArrayList<>();


    @Override
    public void salvarParticipante(Participante p) {
        p.setId(proximoParticipanteId++);
        participantes.add(p);
    }

    @Override
    public List<Participante> listarParticipantes() {
        return participantes;
    }

    @Override
    public Participante buscarParticipantePorId(long id) {
        return participantes.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void salvarProva(Prova p) {
        p.setId(proximaProvaId++);
        provas.add(p);
    }

    @Override
    public List<Prova> listarProvas() {
        return provas;
    }

    @Override
    public Prova buscarProvaPorId(long id) {
        return provas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void salvarQuestao(Questao q) {
        q.setId(proximaQuestaoId++);
        questoes.add(q);
    }

    @Override
    public List<Questao> buscarQuestoesPorProva(long provaId) {
        return questoes.stream().filter(q -> q.getProvaId() == provaId).toList();
    }

    @Override
    public void salvarTentativa(Tentativa t) {
        t.setId(proximaTentativaId++);
        tentativas.add(t);
    }

    @Override
    public List<Tentativa> listarTentativas() {
        return tentativas;
    }
}
