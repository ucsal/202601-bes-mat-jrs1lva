Aplicando o primeiro principio SOLID (Princípio da Responsabilidade Única): <p>
Alterado a localidade do método calcularNota da classe App para a classe Tentativa. <p>
Alterado a localidade do método imprimirTabuleiroFen da classe App para uma classe (Xadrez) em um novo package (View). <p>
Alterado a localidade dos atributos estáticos da classe App para outra classe (DataBase).<p>
Alterado a localidade do menu de opções da classe App para uma nova classe (ComandoDeConsole) através do método exibirMenu().<p>
Scanner para input centralizado na classe ComandoDeConsole.<p><p>
***********<p>
Aplicando o segundo princípio do SOLID (Princípio do Aberto/Fechado):.<p>
Alterado a classe Questao para abstrata.<p>
Criados métodos exibirAluno() e verificaResposta(String entrada).<p>
Criada a classe QuestaoXadrez estendendo a classe Questao encapsulando a lógica do jogo.<p>
Implementado o exibirAluno() para imprimir o enunciado e mostrar o tabuleiro.<p>
Implementado o verificaResposta(String entrada) contendo a lógica de normalização de chars e comparação com a alternativa correta.<p>
Encapsulados os atributos fenInicial e alternativas.<p>
Simplificado o método aplicarProva().<p>
***********<p>
Aplicando o terceiro princípio do SOLID (Princípio da Substituição de Liskov):<p>
Alterado o atributo char da classe Resposta para String.<p>
Resposta passa a suportar a generalização de Questao.<p>
Alterado o método aplicarProva() para ser verdadeiramente universal.<p>
***********<p>
Aplicando o quarto principio do SOLID (Princípio da Segregação de Interfaces).<p>
Criada interface (ComDica) para ser implementada a algumas questões que possam ter dicas.<p>
A classe QuestaoXadrez implementa ComDica.<p>
Adicionada lógica de cadastrar dicas ao cadastrar uma questão.<p>
Adicionada lógica ao aplicar a prova, mostrando a dica da questão que possui uma.<p>
Adicionado set de dica no método Seed().<p>
***********<p>
Aplicando o quinto principio do SOLID (Princípio da Inversão de Dependência).<p>
Criada a interface OlimpiadaRepository com os métodos salvar, buscar e listar das provas, questões e tentativas.<p>
A classe DataBase implementa OlimpiadaRepository e implementa os métodos.<p>
Adicionado e inicializado recenbem um new DataBase o atributo OlimpiadaRepository repository na classe App.<p>
