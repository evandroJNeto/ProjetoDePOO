from PyQt5.QtCore import Qt, QAbstractTableModel
from PyQt5.QtGui import QColor

class TabelaClassificacao(QAbstractTableModel):
    
    def __init__(self, times, parent=None):
        super().__init__(parent)
        self.times = times
        self.colunas = ["Pos", "Time", "Pts", "J", "V", "E", "D", "GP", "GC", "SG"]
    
    def rowCount(self, parent=None):
        return len(self.times)
    
    def columnCount(self, parent=None):
        return len(self.colunas)
    
    def data(self, index, role=Qt.DisplayRole):
        if not index.isValid():
            return None
            
        linha = index.row()
        coluna = index.column()
        time = self.times[linha]
        
        if role == Qt.DisplayRole:
            return self._obter_dado(time, coluna)

        elif role == Qt.BackgroundRole:
            if coluna == 0:
                if linha < 4:
                    return QColor("#A0C4FF")
                elif linha < 6:
                    return QColor("#FFDDA0")
                elif linha < 12:
                    return QColor("#B5EAD7")
                elif linha >= 16:
                    return QColor("#FFB8B8") 
        
        elif role == Qt.TextAlignmentRole:
            return self._obter_alinhamento(coluna)
            
        return None
    
    def _obter_dado(self, time, coluna):
        dados = {
            0: self.times.index(time) + 1,
            1: time.nome,
            2: time.pontos,
            3: time.partidas_jogadas,
            4: time.vitorias,
            5: time.empates,
            6: time.derrotas,
            7: time.gols_marcados,
            8: time.gols_sofridos,
            9: time.saldo_gols
        }
        return dados.get(coluna)
    
    def _obter_alinhamento(self, coluna):
        if coluna == 1:
            return Qt.AlignLeft | Qt.AlignVCenter
        return Qt.AlignCenter
    
    def headerData(self, section, orientation, role=Qt.DisplayRole):
        if orientation == Qt.Horizontal and role == Qt.DisplayRole:
            return self.colunas[section]
        return None