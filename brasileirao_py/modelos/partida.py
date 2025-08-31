from .base import ModeloBase

class Partida(ModeloBase):    
    def __init__(self, rodada, data, hora, mandante, visitante, gols_mandante, gols_visitante, vencedor, estadio):
        self.rodada = rodada
        self.data = data
        self.hora = hora
        self.mandante = mandante
        self.visitante = visitante
        self.gols_mandante = gols_mandante
        self.gols_visitante = gols_visitante
        self.vencedor = vencedor
        self.estadio = estadio
    
    def para_dict(self):
        return {
            'rodada': self.rodada,
            'data': self.data,
            'hora': self.hora,
            'mandante': self.mandante,
            'visitante': self.visitante,
            'gols_mandante': self.gols_mandante,
            'gols_visitante': self.gols_visitante,
            'vencedor': self.vencedor,
            'estadio': self.estadio
        }