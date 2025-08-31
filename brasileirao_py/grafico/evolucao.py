from .base import GraficoBase
from PyQt5.QtChart import QChart, QLineSeries, QValueAxis
from PyQt5.QtGui import QColor
from PyQt5.QtCore import Qt

class GraficoEvolucao(GraficoBase):
    
    def criar(self, times):
        chart = QChart()
        chart.setTitle("Evolução dos Pontos no Campeonato")
        
        cores = [QColor("#E60101"), QColor('#4ECDC4'), QColor("#B8D415"), QColor("#FF00DD"), QColor("#0400FF")]
        
        max_rodadas = 0
        max_pontos = 0
        
        for indice, time in enumerate(times):
            serie = QLineSeries()
            serie.setName(time.nome)
            serie.setColor(cores[indice % len(cores)])
            
            for rodada, pontos in enumerate(time.historico_pontos):
                serie.append(rodada, pontos)
                max_rodadas = max(max_rodadas, rodada)
                max_pontos = max(max_pontos, pontos)
            
            chart.addSeries(serie)
        
        chart.setAnimationOptions(QChart.SeriesAnimations)
        
        eixo_x = QValueAxis()
        eixo_x.setTitleText("Rodadas")
        eixo_x.setRange(0, max_rodadas)
        eixo_x.setTickCount(max_rodadas + 1)
        
        eixo_y = QValueAxis()
        eixo_y.setTitleText("Pontos")
        eixo_y.setRange(0, max_pontos + 5)
        eixo_y.setTickCount(max_pontos + 6)
        
        chart.addAxis(eixo_x, Qt.AlignBottom)
        chart.addAxis(eixo_y, Qt.AlignLeft)
        
        for serie in chart.series():
            serie.attachAxis(eixo_x)
            serie.attachAxis(eixo_y)
        
        return chart