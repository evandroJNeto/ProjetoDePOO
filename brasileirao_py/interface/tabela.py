from PyQt5.QtCore import Qt, QAbstractTableModel

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
            
        time = self.times[index.row()]
        coluna = index.column()
        
        if role == Qt.DisplayRole:
            return self._obter_dado(time, coluna)
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
        return Qt.AlignLeft if coluna == 1 else Qt.AlignCenter
    
    def headerData(self, section, orientation, role=Qt.DisplayRole):
        if orientation == Qt.Horizontal and role == Qt.DisplayRole:
            return self.colunas[section]
        return None