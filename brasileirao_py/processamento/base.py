from abc import ABC, abstractmethod

class ProcessadorBase(ABC):
    
    @abstractmethod
    def processar(self, dados):
        pass