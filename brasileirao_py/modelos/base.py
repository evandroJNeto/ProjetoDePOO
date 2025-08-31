from abc import ABC, abstractmethod

class ModeloBase(ABC):
    
    @abstractmethod
    def para_dict(self):
        pass