package projetodepoo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fagundes
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitor {

    public List<Jogo> lerJogos(String caminhoArquivo) {
        List<Jogo> jogos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean cabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (cabecalho) { 
                    cabecalho = false;
                    continue;
                }

                String[] campos = linha.split("\",\"");
                campos[0] = campos[0].replace("\"", "");
                campos[campos.length - 1] = campos[campos.length - 1].replace("\"", "");

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
            System.out.println("Arquivo não encontrado " + e.getMessage());
        }

        return jogos;
    }
}