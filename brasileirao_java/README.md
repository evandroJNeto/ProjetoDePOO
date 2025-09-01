1.Nome dos integrantes:
Evandro José dos Santos Neto
Kelvin Fagundes Gomes de Souza


2. Link do Repósitorio: https://github.com/evandroJNeto/ProjetoDePOO/
   
3. Descrição do tema do trabalho:

   O trabalho tem como objetivo a visualização de dados do campeonato brasileiro a partir de gráficos. Onde foi desenvolvido um sistema que processa dados de partidas e calcula estatísticas dos times, gerando visualizações gráficas.
   A aplicação permite visualizar a evolução dos times ao longo do campeonato, comparar desempenhos e analisar gráficos.
   
4. Discussão da solução:

   Nosso grupo desenvolveu um sistema para análise do Campeonato Brasileiro. Implementamos a abstração de classes, como Time e Jogo, onde elas foram modeladas como objetos reais. As classes Time e Jogo usam o encapsulamento onde seus dados com atributos private disponibilizam métodos de acesso controlado, como getPontos(), protegendo a integridade dos dados.
   Criamos uma hierarquia de gráficos onde usamos a classe abstrata Grafico que serve como modelo para todos os tipos de gráficos, onde eles herdam os comportamentos da classe abstrata e implementam seus métodos de se desenhar na tela. Foi usado também o conceito de polimorfismo, quando chamamos o método criar() em um gráfico, onde não precisamos saber se é de pizza ou de barras. Cada um se comporta de uma forma diferente, mas a interface é a mesma tornando assim o código mais limpo.
   A classe Leitor faz o trabalho de ler o arquivo csv usando uma List do tipo abstrato Jogo, em que é focado principalmente em abstrair os dados do arquivo csv para implementa-los na classe Leitor, e na classe Campeonato. Então fizemos um sistema que que apenas clicando na tabela(usando a ferramenta .getSelectedItem()) no nome do time e no gerar grafico escolhido, ele mostra o gráfico desejado, que fizemos utilizando o BarChart(), e o PieChart(), duas ferramentas do javafx.scene.
   Além disso fizemos um gráfico de evolução de pontos ao longo do brasileirão, utilizando o XYChart, assim como o algoritmo que possibilita que ao clicar no botão exiba esse gráfico.
   Por fim, com o intuito de melhorar o desing do projeto, implementamos cores na posição de cada time, onde mostra quais times avançam para a Libertadores, Pré-Libertadores, Sul-Americana e quais times são rebaixados para a Série B do campeonato, seguindo a lógica real do campeonato.
   
5.Discussão da OO na segunda linguagem adotada:
   
   Para segunda linguagem, adotamos o Python por ser mais flexível. Durante o desenvolvimento do projeto observamos na prática como a linguagem usa de maneira eficiente os pilares da OO.
   Usamos abstração através de classes bases que definem o que deve ser feito, encapsulamento protegendo os dados internos dos objetos, herança compartilhando os comportamentos entre as classes e o polimorfismo foi usado de forma que permita que objetos diferentes se comportem de forma parecida.
   Com python, nós conseguimos criar funcionalidades mais complexas usando menos código, com mais liberdade e um desenvolvimento mais intuitivo.


6. Observações: 
   Os arquivos .csv estão incluídos na pasta resource.
   Para executar o projeto na versão Java, é necessário ter a biblioteca JavaFX24 e, na versão Py, é necessário ter a biblioteca Pyqt5.
       
