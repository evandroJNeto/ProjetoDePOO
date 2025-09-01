from PyQt5.QtWidgets import (QMainWindow, QTableView, QVBoxLayout, QWidget, QHeaderView, QPushButton, QMessageBox, QHBoxLayout)
from processamento.leitor import LeitorPartidas
from processamento.campeonato import GerenciadorCampeonato
from interface.tabela import TabelaClassificacao
from grafico.criar import CriarGrafico
from PyQt5.QtWidgets import QFileDialog 

class JanelaPrincipal(QMainWindow):
    
    def __init__(self):
        super().__init__()
        self.campeonato = GerenciadorCampeonato()
        self.janela_grafico = None
        self.configurar_interface()
        self.atualizar_tabela()

    def abrir_arquivo_csv(self):
        caminho, _ = QFileDialog.getOpenFileName(
            self,
            "Selecionar Arquivo CSV do Campeonato", "", "Arquivos CSV (*.csv);;Todos os Arquivos (*)"
        )

        if caminho:
            self.carregar_dados(caminho)

    def configurar_interface(self):
        self.setWindowTitle("Brasileirão - Tabela do Campeonato")
        self.setGeometry(100, 100, 1000, 600)
        
        widget_central = QWidget()
        self.setCentralWidget(widget_central)
        
        layout = QVBoxLayout()
        
        self.tabela = QTableView()
        self.tabela.setSelectionBehavior(QTableView.SelectRows)
        self.tabela.setSelectionMode(QTableView.MultiSelection)
        
        layout_botoes = QHBoxLayout()
        
        botao_desempenho = QPushButton("Gráfico de Desempenho (Barras)")
        botao_desempenho.clicked.connect(self.mostrar_desempenho)
        
        botao_evolucao = QPushButton("Evolução dos Pontos")
        botao_evolucao.clicked.connect(self.mostrar_evolucao)

        botao_pizza = QPushButton("Gráfico de Desempenho (Pizza)")
        botao_pizza.clicked.connect(self.mostrar_pizza)

        botao_abrir_arquivo = QPushButton("Abrir Arquivo CSV")
        botao_abrir_arquivo.clicked.connect(self.abrir_arquivo_csv)

        layout_botoes.addWidget(botao_desempenho)
        layout_botoes.addWidget(botao_evolucao)
        layout_botoes.addWidget(botao_pizza)
        layout_botoes.addStretch()
        layout_botoes.addWidget(botao_abrir_arquivo)
        
        layout.addWidget(self.tabela)
        layout.addLayout(layout_botoes)
        
        widget_central.setLayout(layout)
    
    def carregar_dados(self, caminho_arquivo):
        leitor = LeitorPartidas()
        partidas = leitor.processar(caminho_arquivo)
        
        if not partidas:
            self.mostrar_aviso("Não foi possível carregar os dados do arquivo selecionado.")
            return

        self.campeonato = GerenciadorCampeonato()
        self.campeonato.processar(partidas)
        
        self.atualizar_tabela()
    
    def atualizar_tabela(self):
        classificacao = self.campeonato.obter_classificacao()
        self.modelo = TabelaClassificacao(classificacao)
        self.tabela.setModel(self.modelo)
        
        cabecalho = self.tabela.horizontalHeader()
        for i in range(self.modelo.columnCount()):
            cabecalho.setSectionResizeMode(i, QHeaderView.ResizeToContents)
        cabecalho.setSectionResizeMode(1, QHeaderView.Stretch)
    
    def mostrar_desempenho(self):
        selecionados = self.tabela.selectionModel().selectedRows()
        if not selecionados:
            self.mostrar_aviso("Selecione um time na tabela.")
            return
            
        time = self.modelo.times[selecionados[0].row()]
        grafico = CriarGrafico.criar("desempenho", time)
        self._abrir_janela(grafico, f"Desempenho - {time.nome}")
    
    def mostrar_evolucao(self):
        selecionados = self.tabela.selectionModel().selectedRows()
        if not selecionados:
            self.mostrar_aviso("Selecione pelo menos um time.")
            return
            
        if len(selecionados) > 5:
            self.mostrar_aviso("Selecione no máximo 5 times.")
            return
            
        times = [self.modelo.times[indice.row()] for indice in selecionados]
        grafico = CriarGrafico.criar("evolucao", times)
        self._abrir_janela(grafico, "Evolução dos Pontos")

    def mostrar_pizza(self):
        selecionados = self.tabela.selectionModel().selectedRows()
        if not selecionados:
            self.mostrar_aviso("Selecione um time na tabela.")
            return
    
        time = self.modelo.times[selecionados[0].row()]
        grafico = CriarGrafico.criar("pizza", time)
        self._abrir_janela(grafico, f"Desempenho (Pizza) - {time.nome}")

    def _abrir_janela(self, visualizacao, titulo):
        from PyQt5.QtWidgets import QMainWindow
    
        if self.janela_grafico:
            self.janela_grafico.close()
    
        self.janela_grafico = QMainWindow()
        self.janela_grafico.setWindowTitle(titulo)
        self.janela_grafico.setCentralWidget(visualizacao)
        self.janela_grafico.resize(800, 600)
        self.janela_grafico.show()
    
    def mostrar_aviso(self, mensagem):
        aviso = QMessageBox()
        aviso.setIcon(QMessageBox.Warning)
        aviso.setWindowTitle("Aviso")
        aviso.setText(mensagem)
        aviso.exec_()