from .base import ProcessadorBase
from modelos.partida import Partida
import csv

class LeitorPartidas(ProcessadorBase):
    
    def processar(self, caminho_arquivo):
        return self.ler_partidas(caminho_arquivo)
    
    def ler_partidas(self, caminho_arquivo):
        partidas = []
        try:
            with open(caminho_arquivo, 'r', encoding='utf-8') as arquivo:
                leitor = csv.reader(arquivo)
                next(leitor)
                
                for linha in leitor:
                    if len(linha) >= 14:
                        try:
                            partida = self._criar_partida(linha)
                            partidas.append(partida)
                        except (ValueError, IndexError):
                            continue
            return partidas
        except FileNotFoundError:
            print(f"Arquivo {caminho_arquivo} não encontrado")
            return []
    
    def _criar_partida(self, linha):
        rodada = linha[1].strip('"')
        data = linha[2].strip('"')
        hora = linha[3].strip('"')
        mandante = linha[4].strip('"')
        visitante = linha[5].strip('"')
        vencedor = linha[10].strip('"')
        estadio = linha[11].strip('"')
        gols_mandante = int(linha[12].strip('"'))
        gols_visitante = int(linha[13].strip('"'))
        
        return Partida(rodada, data, hora, mandante, visitante, 
                      gols_mandante, gols_visitante, vencedor, estadio)