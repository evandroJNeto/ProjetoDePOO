1.Nome dos integrantes:
Evandro José dos Santos Neto
Kelvin Fagundes Gomes de Souza

2. Link do Repósitorio: https://github.com/evandroJNeto/ProjetoDePOO/

3. Descrição do tema do trabalho:
O tema  pedia para que fizessemos um programa em que abrisse um arquivo csv com o resultado dos jogos em um arquivo CSV do campeonato brasileiro, e mostrassemos
uma tabela do brasileirão, assim como diversos gráficos, no caso escolhemos um PieChart, ou popular gráfico de pizza,um gráfico de barras, e um gráfico de evolução do time.

4. Discussão da solução:
Nosso grupo ficou com o tema da tabela do brasileirão, começamos fazendo a tabela,em que a classe Leitor faz o trabalho de ler o arquivo csv
usando uma List do tipo abstrato Jogo, em que é focado principalmente em abstrair os dados do arquivo csv para implementa-los na classe Leitor,e na classe Campeonato,
então fizemos um sistema que ao inserir o nome do time, ele mostrava o gráfico em barras do time, logo mais a gente mudou esse algoritmo para um em que apenas clicando na tabela(usando a ferramenta .getSelectedItem())
no nome do time e no gerar grafico, ele mostra o gráfico desejado(no caso gráfico de pizza,ou gráfico em barras),que fizemos utilizando o BarChart(),e o PieChart(), duas ferramentas do javafx.scene.
Além disso fizemos um gráfico de evolução de pontos ao longo do brasileirão, utilizando o XYChart, assim como o algoritmo que possibilita que ao clicar no botão exiba esse gráfico, E no final implementamos um sistema de herança de classes na classe Grafico, assim como um botão para selecionar o arquivo CSV que preferir,usando o FileReader.

5.Discussão da OO na segunda linguagem adotada:
Para segunda linguagem, escolhemos python por ser uma linguagem que estamos um pouco mais familiriarizados
