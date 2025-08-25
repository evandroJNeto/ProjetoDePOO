/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Neto
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Jogo {
    String rodada;
    String data;
    String hora;
    String mandante;
    String visitante;
    int mandantePlacar;
    int visitantePlacar;
    String vencedor;
    String arena;

    public Jogo(String rodada, String data, String hora, String mandante, String visitante,
                int mandantePlacar, int visitantePlacar, String vencedor, String arena) {
        this.rodada = rodada;
        this.data = data;
        this.hora = hora;
        this.mandante = mandante;
        this.visitante = visitante;
        this.mandantePlacar = mandantePlacar;
        this.visitantePlacar = visitantePlacar;
        this.vencedor = vencedor;
        this.arena = arena;
    }
}

public class Tabela {
    public static void main(String[] args) {
        String caminhoArquivo = "brasileirao.csv"; // coloque o caminho do arquivo
        List<Jogo> jogos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean cabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (cabecalho) { // pula a primeira linha (cabeçalho)
                    cabecalho = false;
                    continue;
                }

                // Divide a linha em colunas respeitando aspas
                String[] campos = linha.split("\",\"");

                // Remove aspas extras do primeiro e último campo
                campos[0] = campos[0].replace("\"", "");
                campos[campos.length - 1] = campos[campos.length - 1].replace("\"", "");

                // Agora dá pra acessar os índices
                String rodada = campos[1];
                String data = campos[2];
                String hora = campos[3];
                String mandante = campos[4];
                String visitante = campos[5];
                String vencedor = campos[10];
                String arena = campos[11];
                int mandantePlacar = Integer.parseInt(campos[12]);
                int visitantePlacar = Integer.parseInt(campos[13]);

                jogos.add(new Jogo(rodada, data, hora, mandante, visitante,
                        mandantePlacar, visitantePlacar, vencedor, arena));
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }

        // Imprime em formato de tabela
        System.out.printf("%-8s %-12s %-8s %-20s %-20s %-5s %-5s %-10s %-20s%n",
                "Rodada", "Data", "Hora", "Mandante", "Visitante", "M", "V", "Venc.", "Arena");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (Jogo j : jogos) {
            System.out.printf("%-8s %-12s %-8s %-20s %-20s %-5d %-5d %-10s %-20s%n",
                    j.rodada, j.data, j.hora, j.mandante, j.visitante,
                    j.mandantePlacar, j.visitantePlacar, j.vencedor, j.arena);
        }
    }
}