from .base import ModeloBase

class Time(ModeloBase):
    
    def __init__(self, nome):
        self.nome = nome
        self.pontos = 0
        self.vitorias = 0
        self.empates = 0
        self.derrotas = 0
        self.gols_marcados = 0
        self.gols_sofridos = 0
        self.partidas_jogadas = 0
        self.historico_pontos = [0]
    
    def registrar_partida(self, gols_feitos, gols_sofridos):
        self.partidas_jogadas += 1
        self.gols_marcados += gols_feitos
        self.gols_sofridos += gols_sofridos
        
        if gols_feitos > gols_sofridos:
            self.vitorias += 1
            self.pontos += 3
        elif gols_feitos == gols_sofridos:
            self.empates += 1
            self.pontos += 1
        else:
            self.derrotas += 1
            
        self.historico_pontos.append(self.pontos)
    
    @property
    def saldo_gols(self):
        return self.gols_marcados - self.gols_sofridos
    
    @property
    def aproveitamento(self):
        if self.partidas_jogadas == 0:
            return 0.0
        return (self.pontos / (self.partidas_jogadas * 3)) * 100
    
    def para_dict(self):
        return {
            'nome': self.nome,
            'pontos': self.pontos,
            'vitorias': self.vitorias,
            'empates': self.empates,
            'derrotas': self.derrotas,
            'gols_marcados': self.gols_marcados,
            'gols_sofridos': self.gols_sofridos,
            'partidas_jogadas': self.partidas_jogadas,
            'saldo_gols': self.saldo_gols,
            'aproveitamento': self.aproveitamento
        }