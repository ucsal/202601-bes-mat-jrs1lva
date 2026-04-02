package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.interfaces.ComDica;

public class App {
	
	private static OlimpiadasRepository repository;

	static void main() {

        repository = new DataBase();
		seed();

		while (true) {
			ComandoDeConsole.exibirMenu();

			switch (ComandoDeConsole.pegaInput("")) {
			case "1" -> cadastrarParticipante();
			case "2" -> cadastrarProva();
			case "3" -> cadastrarQuestao();
			case "4" -> aplicarProva();
			case "5" -> listarTentativas();
			case "0" -> {
				System.out.println("tchau");
				return;
			}
			default -> System.out.println("opção inválida");
			}
		}
	}

	static void cadastrarParticipante() {
		var nome = ComandoDeConsole.pegaInput("Nome: ");
		var email = ComandoDeConsole.pegaInput("Email (opcional): ");

		if (nome == null || nome.isBlank()) {
			System.out.println("nome inválido");
			return;
		}

		var p = new Participante();
		p.setId(DataBase.proximoParticipanteId++);
		p.setNome(nome);
		p.setEmail(email);

		DataBase.participantes.add(p);
		System.out.println("Participante cadastrado: " + p.getId());
	}

	static void cadastrarProva() {
		var titulo = ComandoDeConsole.pegaInput("Título da prova: ");

		if (titulo == null || titulo.isBlank()) {
			System.out.println("título inválido");
			return;
		}

		var prova = new Prova();
		prova.setId(DataBase.proximaProvaId++);
		prova.setTitulo(titulo);

		DataBase.provas.add(prova);
		System.out.println("Prova criada: " + prova.getId());
	}

	static void cadastrarQuestao() {
		if (DataBase.provas.isEmpty()) {
			System.out.println("não há provas cadastradas");
			return;
		}

		var provaId = escolherProva();
		if (provaId == null)
			return;

		var enunciado = ComandoDeConsole.pegaInput("Enunciado: ");

		var alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			char letra = (char) ('A' + i);
			alternativas[i] = letra + ") " + ComandoDeConsole.pegaInput("Alternativa " + letra + ": ");
		}

		System.out.print("Alternativa correta (A–E): ");
		char correta;
		try {
			correta = QuestaoXadrez.normalizar(ComandoDeConsole.pegaInput("").trim().charAt(0));
		} catch (Exception e) {
			System.out.println("alternativa inválida");
			return;
		}

		var q = new QuestaoXadrez();
		q.setId(DataBase.proximaQuestaoId++);
		q.setProvaId(provaId);
		q.setEnunciado(enunciado);
		q.setAlternativas(alternativas);
		q.setAlternativaCorreta(correta);

        String dica = ComandoDeConsole.pegaInput("Dica da questão (pressione Enter para pular): ");
        if (!dica.isBlank()){
            q.setDica(dica);
        }

		DataBase.questoes.add(q);

		System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
	}


	static void aplicarProva() {
		if (DataBase.participantes.isEmpty()) {
			System.out.println("cadastre participantes primeiro");
			return;
		}
		if (DataBase.provas.isEmpty()) {
			System.out.println("cadastre provas primeiro");
			return;
		}

		var participanteId = escolherParticipante();
		if (participanteId == null)
			return;

		var provaId = escolherProva();
		if (provaId == null)
			return;

		var questoesDaProva = DataBase.questoes.stream().filter(q -> q.getProvaId() == provaId).toList();

		if (questoesDaProva.isEmpty()) {
			System.out.println("esta prova não possui questões cadastradas");
			return;
		}

		var tentativa = new Tentativa();
		tentativa.setId(DataBase.proximaTentativaId++);
		tentativa.setParticipanteId(participanteId);
		tentativa.setProvaId(provaId);

		System.out.println("\n--- Início da Prova ---");

		for (var q : questoesDaProva) {
			System.out.println("\nQuestão #" + q.getId());
			q.exibirParaAluno();

            if (q instanceof ComDica questaoComDica && questaoComDica.obterDica() != null){
                String querDica = ComandoDeConsole.pegaInput("\nPrecisa de uma dica? (S/N): ");
                if (querDica.equalsIgnoreCase("S")){
                    System.out.println("Dica: "+questaoComDica.obterDica()+"\n");
                }
            }

            String entrada = ComandoDeConsole.pegaInput("Sua resposta (A–E): ");
            boolean acertou = q.verificaResposta(entrada);

			var r = new Resposta();
			r.setQuestaoId(q.getId());
			r.setCorreta(acertou);
            if (!entrada.isBlank()){
                r.setRespostaFornecida(entrada);
            }

			tentativa.getRespostas().add(r);
		}

		DataBase.tentativas.add(tentativa);

		int nota = tentativa.calcularNota();
		System.out.println("\n--- Fim da Prova ---");
		System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
	}

	

	static void listarTentativas() {
		System.out.println("\n--- Tentativas ---");
		for (var t : DataBase.tentativas) {
			System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n", t.getId(), t.getParticipanteId(),
					t.getProvaId(), t.calcularNota(), t.getRespostas().size());
		}
	}


	static Long escolherParticipante() {
		System.out.println("\nParticipantes:");
		for (var p : DataBase.participantes) {
			System.out.printf("  %d) %s%n", p.getId(), p.getNome());
		}
		System.out.print("Escolha o id do participante: ");

		try {
			long id = Long.parseLong(ComandoDeConsole.pegaInput(""));
			boolean existe = DataBase.participantes.stream().anyMatch(p -> p.getId() == id);
			if (!existe) {
				System.out.println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			System.out.println("entrada inválida");
			return null;
		}
	}

	static Long escolherProva() {
		System.out.println("\nProvas:");
		for (var p : DataBase.provas) {
			System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
		}
		System.out.print("Escolha o id da prova: ");

		try {
			long id = Long.parseLong(ComandoDeConsole.pegaInput(""));
			boolean existe = DataBase.provas.stream().anyMatch(p -> p.getId() == id);
			if (!existe) {
				System.out.println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			System.out.println("entrada inválida");
			return null;
		}
	}

	static void seed() {

		var prova = new Prova();
		prova.setId(DataBase.proximaProvaId++);
		prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
		DataBase.provas.add(prova);

		var q1 = new QuestaoXadrez();
		q1.setId(DataBase.proximaQuestaoId++);
		q1.setProvaId(prova.getId());

		q1.setEnunciado("""
				Questão 1 — Mate em 1.
				É a vez das brancas.
				Encontre o lance que dá mate imediatamente.
				""");

		q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

		q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });

		q1.setAlternativaCorreta('C');

        q1.setDica("Obeserve a Dama apoiada pelo Bispo.");

		DataBase.questoes.add(q1);
	}
}