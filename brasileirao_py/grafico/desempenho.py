from .base import GraficoBase
from PyQt5.QtChart import QChart, QBarSeries, QBarSet
from PyQt5.QtGui import QColor

class GraficoDesempenho(GraficoBase):
    
    def criar(self, time):
        chart = QChart()
        chart.setTitle(f"Desempenho do {time.nome}")
        
        series = QBarSeries()
        
        categorias = [
            ("Vitórias", time.vitorias, QColor("#38FD49")),
            ("Empates", time.empates, QColor("yellow")),
            ("Derrotas", time.derrotas, QColor("#EE0101"))
        ]
        
        for nome, valor, cor in categorias:
            conjunto = QBarSet(nome)
            conjunto.append(valor)
            conjunto.setColor(cor)
            series.append(conjunto)
        
        chart.addSeries(series)
        chart.setAnimationOptions(QChart.SeriesAnimations)
        chart.createDefaultAxes()
        
        return chart