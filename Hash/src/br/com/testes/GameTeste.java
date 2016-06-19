/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guilherme Lourenço
 */
public class GameTeste  implements Serializable{
    //String X ou O que indica quem deve jogar
    private String vez;
    //Esse array deve ter 9 posicoes com uma string X ou O
    private ArrayList<String> jogo;
    
    public GameTeste(String vez){
        this.vez = vez;
        this.jogo = new ArrayList<>();
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        this.jogo.add("");
        
    }
    
    public ArrayList<String> getJogo(){
        return this.jogo;
    }
    
    public void setJogo(ArrayList<String> jogo){
        this.jogo = jogo;
    }
    
    //Retorna X se X ganhou ou O se O ganhou ou N se Ninguem Ganhou
    public String verificaSeAlguemGanhou(){
        String[] linha1 = {jogo.get(0), jogo.get(1), jogo.get(2)};
        String[] linha2 = {jogo.get(3), jogo.get(4), jogo.get(5)};
        String[] linha3 = {jogo.get(6), jogo.get(7), jogo.get(8)};
        
        String[] coluna1 = {jogo.get(0), jogo.get(3), jogo.get(6)};
        String[] coluna2 = {jogo.get(1), jogo.get(4), jogo.get(7)};
        String[] coluna3 = {jogo.get(2), jogo.get(5), jogo.get(8)};
        
        String[] diagonal1 = {jogo.get(0), jogo.get(4), jogo.get(8)};
        String[] diagonal2 = {jogo.get(2), jogo.get(4), jogo.get(6)};
        
        if(linha1[0].equals(linha1[1]) && linha1[1].equals(linha1[2])){
            return linha1[0];
        }else if(linha2[0].equals(linha2[1]) && linha2[1].equals(linha2[2])){
            return linha2[0];
        }else if(linha3[0].equals(linha3[1]) && linha3[1].equals(linha3[2])){
            return linha3[0];
        }else if(coluna1[0].equals(coluna1[1]) && coluna1[1].equals(coluna1[2])){
            return coluna1[0];
        }else if(coluna2[0].equals(coluna2[1]) && coluna2[1].equals(coluna2[2])){
            return coluna2[0];
        }else if(coluna3[0].equals(coluna3[1]) && coluna3[1].equals(coluna3[2])){
            return coluna3[0];
        }else if(diagonal1[0].equals(diagonal1[1]) && diagonal1[1].equals(diagonal1[2])){
            return diagonal1[0];
        }else if(diagonal2[0].equals(diagonal2[1]) && diagonal2[1].equals(diagonal2[2])){
            return diagonal2[0];
        }else{
            return "N";
        }
        
    }
    
    public String getVez(){
        return this.vez;
    }
    public void setVez(String vez){
        this.vez = vez;
    }
}
