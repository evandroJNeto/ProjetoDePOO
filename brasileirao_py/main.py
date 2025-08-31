import sys
from PyQt5.QtWidgets import QApplication
from interface.principal import JanelaPrincipal

class Aplicacao:
    def __init__(self):
        self.app = QApplication(sys.argv)
        self.janela = JanelaPrincipal()
    
    def executar(self):
        self.janela.show()
        return self.app.exec_()

if __name__ == "__main__":
    app = Aplicacao()
    sys.exit(app.executar())