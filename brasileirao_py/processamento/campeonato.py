from .base import ProcessadorBase
from modelos.time import Time

class GerenciadorCampeonato(ProcessadorBase):
    """Gerencia todo o campeonato e suas estatísticas"""
    
    def __init__(self):
        self.partidas = []
        self.times = {}
    
    def processar(self, partidas):
        """Processa todas as partidas do campeonato"""
        self.partidas = partidas
        self._preparar_times()
        
        for partida in self.partidas:
            time_mandante = self.times[partida.mandante]
            time_visitante = self.times[partida.visitante]
            
            time_mandante.registrar_partida(partida.gols_mandante, partida.gols_visitante)
            time_visitante.registrar_partida(partida.gols_visitante, partida.gols_mandante)
    
    def _preparar_times(self):
        for partida in self.partidas:
            if partida.mandante not in self.times:
                self.times[partida.mandante] = Time(partida.mandante)
            if partida.visitante not in self.times:
                self.times[partida.visitante] = Time(partida.visitante)
    
    def obter_classificacao(self):
        times = list(self.times.values())
        times.sort(key=lambda time: (
            -time.pontos, 
            -time.vitorias, 
            -time.saldo_gols, 
            -time.gols_marcados,
            time.nome
        ))
        return times
    
    def buscar_time(self, nome_time):
        return self.times.get(nome_time)