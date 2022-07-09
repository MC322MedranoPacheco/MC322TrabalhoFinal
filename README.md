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

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

![image](https://user-images.githubusercontent.com/102121798/176239765-5a40f038-95b4-4631-be4a-3e8c2f87f133.png)

*obs: imagens do jogo retiradas do: (https://www.kenney.nl/assets)
## Detalhamento das Interfaces

### Interface `IPress`

A interface serve para receber os movimentos do leitor e realizar as ações necessárias.

~~~
<public interface IPress{
	public boolean acao(String comando);
	public void addKeyListener(KeyListener key);
}
>
~~~

Método | Objetivo
-------| --------
`acao` | `Realiza uma ação com base em uma tecla.`
`AddKeyListener` | `Adiciona um observador do teclado.'`

### Interface IRCommand

Interface que conecta o gameControl com um ator para que possam ser realizadas ações.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o controle com um ator para realizar ações.`

### Interface IRFazerNivel

Interface que conecta o gameControl com um montador para a montagem de salas.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o controle com um ator para realizar ações.`
### Interface ISolicitarMovimento

Interface que permite que o movimentos sejam solicitados ao controle.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`acao` | `Solicitar que o controle realize um comando para determinado ator.`

### Interface IStart

Interface que começa um determinado nivel.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`acao` | `Comeca um determinado nivel.`

### Interface IRPress

Interface que permite que o leitor se comunique com o gameControl e mande os comandos do teclado para ele.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o leitor com o GameControl para enviar os movimentos.`

### Interface IRLocked

Interface que permite que o leitor se comunique com o NivelView para que ele saiba quando uma animção terminou e ele pode ler outro movimento do teclado.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o leitor com o NivelView para que o leitor possa saber quando liberar o movimento.`

### Interface IFazerNivel

Interface que possui o metodo responsavel pelo montador criar niveis.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`constroiNivel` | `Cria um nível a partir de um arquivo .csv.`

### Interface ICommand

Interface que permite a realização de acoes por parte do ator. Também permite acessar determinados atributos do ator que são necessários para determinar a validade de um movimento

~~~
<Interface em Java.>
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
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`connect` | `Conecta o autor com o Nível para que o autor possa se mover.`

### Interface IVivo

Interface responsável pelo controle da variavel vivo do ator, permitindo obte-la e modificala.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`setVivo` | `Muda a variável vivo do ator.`
`getVivo` | `Retorna a variável vivo do ator.`

### Interface ActorSubjectView

Interface padrão de observer que é responsavel por avisar as imagens para elas se moverem na tela.

~~~
<Interface em Java.>
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
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`setPosicao` | `Muda a variavel posicao do ator.`

### Interface IAction

Interface reponsavel por controlar os movimentos do ator na sala e auxiliar o view.

~~~
<Interface em Java.>
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
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`connect` | `Connecta o nivel com o GameControl para que o nivel possa solicitar movimentos.`

### Interface IMainView

Interface reponsavel pelo o que o usuario vai observar.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`setContentePane` | `Muda o contentePane do jFrame do MainView para o necessario.`

### Interview IMenuView

Interface reponsavel pelos menus do jogo.

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`getJFrameNextLevel` | `Gera o jFrame que representa o menu de proxema fase.`
`getJFramePerdeu` | `Gera o jFrame que representa o menu de perdeu.`
`getJFrameFimDeJogo` | `Gera o jFrame que representa o menu de fim de jogo.`

### Interview INivelView

Interface reponsavel pelas imagens do nivel.

~~~
<Interface em Java.>
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
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`update` | `Retorna o contentPane atual.`
`setSubject` | `Atualiza o subject.`
`setBounds` | `Atualiza as posições do jLabel.`

### Interview ILocked

Interface responsavel pela variavel locked

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`getLocked` | `Retorna a variável locked.`
`setLocked` | `Atualiza a variável locked.`
