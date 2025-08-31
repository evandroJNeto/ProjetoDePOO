from .desempenho import GraficoDesempenho
from .evolucao import GraficoEvolucao
from PyQt5.QtChart import QChartView
from PyQt5.QtGui import QPainter 

class CriarGrafico:
    
    @staticmethod
    def criar(tipo, dados):
        if tipo == "desempenho":
            chart = GraficoDesempenho().criar(dados)
        elif tipo == "evolucao":
            chart = GraficoEvolucao().criar(dados)
        else:
            raise ValueError("Tipo de visualização não suportado")
        
        visualizacao = QChartView(chart)
        visualizacao.setRenderHint(QPainter.Antialiasing)
        return visualizacao