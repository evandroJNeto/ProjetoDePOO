from .base import GraficoBase
from PyQt5.QtChart import QChart, QPieSeries, QPieSlice
from PyQt5.QtGui import QColor, QFont
from .base import GraficoBase
from PyQt5.QtCore import Qt

class GraficoPizza(GraficoBase):
    
    def criar(self, time):
        series = QPieSeries()
        series.append("Vitórias", time.vitorias).setColor(QColor("green"))
        series.append("Empates", time.empates).setColor(QColor("yellow"))
        series.append("Derrotas", time.derrotas).setColor(QColor("red"))

        for slice in series.slices():
            slice.setLabelVisible(True)
            slice.setLabel(f"{slice.label()} ({int(slice.value())})")
            slice.setLabelFont(QFont("Arial", 10, QFont.Bold))
            slice.setLabelColor(QColor("white"))

        chart = QChart()
        chart.addSeries(series)
        chart.setTitle(f"Desempenho de {time.nome}")
        chart.setAnimationOptions(QChart.SeriesAnimations)
        chart.legend().setAlignment(Qt.AlignBottom)
        
        return chart