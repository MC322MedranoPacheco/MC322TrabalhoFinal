# Projeto `Gem Collectors`

# Descrição Resumida Jogo

O jogo consiste em 1 garoto que coleciona gemas. Ele deve percorrer diferentes niveis em busca de novas gemas para sua coleção e, para isso, ele devera interagir com o ambiente e com objetos para resolver quebra cabeças e alcançar os seus objetivos

# Equipe
* `<Gabriel Medrano Silva>` - `<247113>`
* `<Henrique Pacheco Manoel>` - `<247165>`

# Arquivo Executável do Jogo

* Link para executável: [Gem Collectors](data/GemCollectors.jar)

# Slides do Projeto

## Slides da Prévia
* Link para prévia: [prévia](data/Trabalho-mc322.pptx)

## Slides da Apresentação Final
* Link para slides: [slides](data/Gem-Collectors-MC322.pptx)

# Relatório de Evolução
* 	No começo do projeto primeiramente pensamos em desenvolver um jogo de puzzles em salas com o avanço de níveis ao longo do jogo e implementando uma interface gráfica, nosso primeiro pensamento em relação ao código foi construir uma arquitetura modelo MVC simples. O próximo passo foi pensar na interface gráfica já que já tínhamos uma boa ideia de como o modelo e o controle iriam funcionar inicialmente, nosso primeiro pensamento foi a libGDX porém a falta de conhecimento sobre a mesma e o conteúdo disponibilizado sobre java Swing pelo professor nos incentivou a usar tal recurso do java. 
	<p> Começamos então o projeto criando as interfaces que pensamos anteriormente e implementando um nível parecido com o presente no wumpus começando pela criação do montador e adaptação de um toolkit próprio para o nosso projeto. Inicialmente o plano era o jogador poder andar por diversas salas e somente na última sala trocar de nível, então todas as salas de um nível seriam criadas dentro do mesmo, infelizmente não pudemos implementar tal mecânica uma vez que focamos primeiro em ter um jogo completo para a entrega do projeto e o não conhecimento do java swing fez com que tivéssemos muita dificuldade de aplicar uma animação que funcionasse junto dos inputs do teclado e que ficasse em sincronia com as posições do modelo.
	 <p> Ao longo do projeto separamos onde cada um trabalharia assim podiamos otimizar o tempo permitindo-nos focar mais em construir certa parte do programa como modelo e interface gráfica sem necessitar conversar e se informar a cada mudança feita no código assim poderíamos apenas nos atualizar de tempos em tempos e auxiliar um ao outro em caso de dúvidas uma vez que já sabíamos como o programa funcionaria no geral.
         <p> Foi nesse período que a maior parte do código surgiu bem como os maiores problemas, o funcionamento de certos atores ou lasers que precisava da aplicação de observers custou muito tempo até entendermos como aplicá-lo de maneira eficaz bem como problemas na adição e retirada de componentes do JFrame parecia muita confusa. Nessa parte percebemos que muitas interfaces que pensamos  no começo do programa na verdade não se encaixavam no contexto atual bem como métodos e até mesmo certos atores deveriam ser esquecidos para termos um jogo em mãos no final do período. Assim priorizamos refinar e resolver problemas com o que já tínhamos avançado que já estava em um ponto interessante do que limpar o código ou adicionar tudo que queríamos
	<p> Chegando no final do prazo de entrega tentamos implementar um pequeno tratamento de exceções no montador e terminamos de colocar em funcionamento a troca de níveis e felizmente conseguimos construir níveis que mostrassem as mecânicas que colocamos no código de forma eficaz.

# Destaques de Código
 
### 

# Destaques de Orientação a Objetos

### ICommand
 Interface utilizada para passar comandos a um ator nela se encontra especificamente o acao, que é usada para solicitar um comando a um ator genericamente que por sua vez podem ter respostas diferentes, um classico exemplo de polimorfismo presente em nosso código.
	![Screenshot 2022-07-08 221603](https://user-images.githubusercontent.com/102121798/178086215-f0d29645-d935-49ce-a8ef-c603c9ba1403.png)

### Ações de um ator
 Ao tentar executr uma ação em um nivel este verifica se ha a necessidade de interagir com outro ator e nesse caso o nivel solicita para o gameContrl a
		execução da acao do ator alvo fazendo assim uma cadeia de ações onde cada ação pode realizar coisas diferentes, segue abaixo o momento do nivel que a cadeia de ações ocorre e alguns exemplos de diferentes ações.
	*	
	![mover](https://user-images.githubusercontent.com/102121798/178086516-58949ceb-b179-4d26-862e-a77669bf9430.png)
	*<p>
	ação de uma caixa:
	*<p>
	![caixa](https://user-images.githubusercontent.com/102121798/178086574-1fbab7c8-cb79-4d46-aa1e-9104bdef3429.png)
	*<p>
	ação de um feixo de laser:
	*<p>
	![laseracao](https://user-images.githubusercontent.com/102121798/178086609-c35caccd-74e7-4f07-bcb6-9ad4847ab972.png)

### Portas e Hierarquia

*Nosso código também utiliza a hierarquia para evitar repetição de código e facilitar futuras atualizações como por exemplo a Porta que possui diferentes tipos completamente diferentes, 
		uma aplicando observer outra verificando inventários. Porém ambas possuem uma classe mãe que contêm elementos 
		indispensáveis em ambos como um toString semelhante e uma ação igual como na imagem abaixo:
		
		
![Porta mae](https://user-images.githubusercontent.com/102121798/178086803-b091b6d8-43e3-4e3b-8ac0-4182429a11a0.png)
*<p>
	Já os filhos chamam funções presentes na classe mãe, por exemplo: 
*<p>
![porta1](https://user-images.githubusercontent.com/102121798/178086851-b87a23b5-9b5e-41d6-a97b-49ce1c0426b0.png)
	
# Diagramas

## Diagrama Geral da Arquitetura do Jogo

(![ARQUITETURA](https://user-images.githubusercontent.com/102121798/178085542-006f2a4e-9fe7-49e8-884f-6f1f2126d622.png)

*obs: imagens do jogo retiradas do: (https://www.kenney.nl/assets)

## Detalhamento das Interfaces

### Interface `IPress`

A interface serve para receber os movimentos do leitor e realizar as ações necessárias.

~~~
public interface IPress{
	public boolean acao(String comando);
	public void addKeyListener(KeyListener key);
}
~~~

Método | Objetivo
-------| --------
`acao` | `Realiza uma ação com base em uma tecla.`
`AddKeyListener` | `Adiciona um observador do teclado.'`

### Interface IRCommand

Interface que conecta o gameControl com um ator para que possam ser realizadas ações.

~~~
public interface IRComand {
	public void connect(ICommand iCommand);
}
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o controle com um ator para realizar ações.`

### Interface IRFazerNivel

Interface que conecta o gameControl com um montador para a montagem de salas.

~~~
public interface IRFazerNivel {
	public void connect(IFazerNivel iFazerNivel);
}
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o controle com um ator para realizar ações.`
### Interface ISolicitarMovimento

Interface que permite que o movimentos sejam solicitados ao controle.

~~~
public interface ISolicitarMovimento{

	public boolean acao(ICommand actor, Posicao direcao, ICommand actorMaker);
}
~~~

Método | Objetivo
-------| --------
`acao` | `Solicitar que o controle realize um comando para determinado ator.`

### Interface IStart

Interface que começa um determinado nivel.

~~~
public interface IStart {
	public void start();
}
~~~

Método | Objetivo
-------| --------
`acao` | `Comeca um determinado nivel.`

### Interface IRPress

Interface que permite que o leitor se comunique com o gameControl e mande os comandos do teclado para ele.

~~~
public interface IRPress {
	public void connect(IPress ipress);
}
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o leitor com o GameControl para enviar os movimentos.`

### Interface IRLocked

Interface que permite que o leitor se comunique com o NivelView para que ele saiba quando uma animção terminou e ele pode ler outro movimento do teclado.

~~~
public interface IRLocked {
	public void connect(ILocked iLocked);
}
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o leitor com o NivelView para que o leitor possa saber quando liberar o movimento.`

### Interface IFazerNivel

Interface que possui o metodo responsavel pelo montador criar niveis.

~~~
public interface IFazerNivel {
	public Nivel constroiNivel(String path, String arquivo, SalaChanger changer);
}
~~~

Método | Objetivo
-------| --------
`constroiNivel` | `Cria um nível a partir de um arquivo .csv.`

### Interface ICommand

Interface que permite a realização de acoes por parte do ator. Também permite acessar determinados atributos do ator que são necessários para determinar a validade de um movimento

~~~
public interface ICommand extends IVivo{
	
	public boolean acao(String comando, IVivo vivo);
	public boolean acao(Posicao destino, ICommand receiver, ICommand maker);
	public int getForca();
	public int getResistencia();
	public ArrayList<Item> getInventario();
	public Posicao getPosicao();
	public Posicao getPosicaoAnterior();
	boolean interact(ArrayList<Item> inventario);
}
~~~

Método | Objetivo
-------| --------
`acao` | `Realiza uma ação com base em um comando.`
`getForca` | `retorna a forca do ator.`
`getResistencia` | `retorna a resistência do ator.`
`getInventario` | `retorna o inventário do ator.`
`getPosicao` | `retorna a posição do ator.`
`getPosicao` | `retorna a posição anterior do ator.`
`interact` | `interage com um dos objetos.`

### Interface IRAction

Interface que conecta o ator com o nivel para ele se mover no nivel.

~~~
public interface IRAction {
	public void connect(IAction iaction);
}
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o autor com o Nível para que o autor possa se mover.`

### Interface IVivo

Interface responsável pelo controle da variavel vivo do ator, permitindo obte-la e modificala.

~~~
public interface IVivo {
	public void setVivo(boolean vivo);
	public boolean getVivo();
}
~~~

Método | Objetivo
-------| --------
`setVivo` | `Muda a variável vivo do ator.`
`getVivo` | `Retorna a variável vivo do ator.`

### Interface ActorSubjectView

Interface padrão de observer que é responsavel por avisar as imagens para elas se moverem na tela.

~~~
public interface ActorSubjectView {
	public void registrarView(ObserverActor obj);
	public void excluirRegistroView(ObserverActor obj);
	public void notificarObservadoresView(String string);
	public void setChanged(boolean changed);
}
~~~

Método | Objetivo
-------| --------
`registrarView` | `Registra um observador do ator.`
`excluirRegistroView` | `Exclui um observador do ator.`
`setChanged` | `muda a variavel Changed.`
`notificarObservadoresView` | `Notifica os observadores do ator`

### Interface IActor

Interface que junta as outras interfaces do actor e possui um metodo para setar a posicao do ator.

~~~
public interface IActor extends ICommand, IRAction, IRVisualActor, IVivo, ActorSubjectView {
	public void setPosicao(Posicao posicao);
}
~~~

Método | Objetivo
-------| --------
`setPosicao` | `Muda a variavel posicao do ator.`

### Interface IAction

Interface reponsavel por controlar os movimentos do ator na sala e auxiliar o view.

~~~
public interface IAction {

	public boolean mover(int sala, Posicao posicaoAtual, Posicao destino, int forca);

	public Celula getCelula(Posicao posicao, int sala);

	public void pegar(int sala, Posicao posicaoAtual, ArrayList<Item> inventario);
	
	void addImage(int sala, Posicao pos);
	
	void removerItem(int sala, Posicao pos);

	public boolean interact(int sala, Posicao posicaoAtual, ArrayList<Item> inventario);
	
}
~~~

Método | Objetivo
-------| --------
`getCelula` | `Retorna determinada celula.`
`pegar` | `Pega o item da celula.`
`addImage` | `Pede para adicionar uma imagem no View.`
`removerItem` | `Remove o item da posicao de uma sala.`
`mover` | `Tenta movimentar o ator fazendo as verificações necessárias`
`interact` | `Interage com um objeto do nivel.`

### Interface IRSolicitarMovimento

Interface reponsavel por conectar o nivel com o GameControl.

~~~
public interface IRSolicitarMovimento {
	public void connect(ISolicitarMovimento connect);
}
~~~

Método | Objetivo
-------| --------
`connect` | `Connecta o nivel com o GameControl para que o nivel possa solicitar movimentos.`

### Interface IMainView

Interface reponsavel pelo o que o usuario vai observar.

~~~
public interface IMainView extends IRNivelShow{
	public void setContentPane(Container contentPane, KeyListener key);
}

~~~

Método | Objetivo
-------| --------
`setContentePane` | `Muda o contentePane do jFrame do MainView para o necessario.`

### Interview IMenuView

Interface reponsavel pelos menus do jogo.

~~~
public interface IMenuView extends IRStart{
	public JFrame getJFrameNextLevel(String string);
	public JFrame getJFramePerdeu();
	public JFrame getJFrameFimDeJogo();
}
~~~

Método | Objetivo
-------| --------
`getJFrameNextLevel` | `Gera o jFrame que representa o menu de proxema fase.`
`getJFramePerdeu` | `Gera o jFrame que representa o menu de perdeu.`
`getJFrameFimDeJogo` | `Gera o jFrame que representa o menu de fim de jogo.`

### Interview INivelView

Interface reponsavel pelas imagens do nivel.

~~~
public interface INivelView {
	public Container getContentPane();
	public JFrame getJFrame();
	public JFrame geraJFrame(int x, int y, Sala sala, KeyListener key);
	public void addImage(Sala sala, Posicao pos);
	public void removeItem(Sala sala, Posicao pos);
	public JLabelAnima getPersonagem();
}
~~~

Método | Objetivo
-------| --------
`getContentPane` | `Retorna o contentPane atual.`
`getJFrame` | `Retorna o JFrame atual.`
`addImage` | `Adiciona uma imagem na posição desejada.`
`removeItem` | `Remove a imagem de um item da matriz de itens.`
`getPersonagem` | `Retorna o JLabelAnima do personagem principal.`

### Interview ObserverActor

Interface implementada por quem observa os atores.

~~~
public interface ObserverActor {
	public void update(String direcao);
	public void setSubject(ActorSubjectView sub);
	public void setBounds(int i, int j, int k, int l);
}
~~~

Método | Objetivo
-------| --------
`update` | `Retorna o contentPane atual.`
`setSubject` | `Atualiza o subject.`
`setBounds` | `Atualiza as posições do jLabel.`

### Interview ILocked

Interface responsavel pela variavel locked

~~~
public interface ILocked {
	public boolean getLocked();
	public void setLocked(boolean locked);
}
~~~

Método | Objetivo
-------| --------
`getLocked` | `Retorna a variável locked.`
`setLocked` | `Atualiza a variável locked.`

# Conclusões e trabalhos futuros:
* Após o término do nosso jogo, concluímos que algumas decisões poderiam ter sido tomadas para melhorar e facilitar o desenvolvimento do jogo. Primeiramente, devido a grande dificuldade que tivemos com o uso do java Swing para a geração e controle das imagens, entendemos que teria sido melhor o uso de uma biblioteca especializada em desenvolvimento de jogos, como a LibGDX. Poderíamos também utilizar o design pattern de factory para criarmos os objetos na sala de maneira mais padronizada. Assim como implementar atores que não pudemos como o Computador, mecânica de Observer para desligar/ligar o laser por interação, interação por input no teclado e mecânicas de entrar/sair de diferentes salas em um mesmo nível. Gostaríamos também de limpar resquícios de código no nosso programa e implementar um plano de exceções mais complexo.
