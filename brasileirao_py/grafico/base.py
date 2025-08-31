from abc import ABC, abstractmethod

class GraficoBase(ABC):
    
    @abstractmethod
    def criar(self, dados):
        pass