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
<Interface em Java.>
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

