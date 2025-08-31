from PyQt5.QtWidgets import (QMainWindow, QTableView, QVBoxLayout, QWidget, QHeaderView, QPushButton, QMessageBox, QHBoxLayout)
from processamento.leitor import LeitorPartidas
from processamento.campeonato import GerenciadorCampeonato
from interface.tabela import TabelaClassificacao
from grafico.criar import CriarGrafico

class JanelaPrincipal(QMainWindow):
    
    def __init__(self):
        super().__init__()
        self.campeonato = None
        self.janela_grafico = None
        self.configurar_interface()
        self.carregar_dados()
    
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
        
        botao_desempenho = QPushButton("Ver Desempenho")
        botao_desempenho.clicked.connect(self.mostrar_desempenho)
        
        botao_evolucao = QPushButton("Ver Evolução")
        botao_evolucao.clicked.connect(self.mostrar_evolucao)
        
        layout_botoes.addWidget(botao_desempenho)
        layout_botoes.addWidget(botao_evolucao)
        layout_botoes.addStretch()
        
        layout.addWidget(self.tabela)
        layout.addLayout(layout_botoes)
        
        widget_central.setLayout(layout)
    
    def carregar_dados(self):
        leitor = LeitorPartidas()
        partidas = leitor.processar("campeonato-brasileiro.csv")
        
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