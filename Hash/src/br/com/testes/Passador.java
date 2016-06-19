/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import java.io.Serializable;

/**
 *
 * @author Guilherme Lourenço
 */
public class Passador implements Serializable{
    private Jogo jogo;
    private int id;
    
    public Passador(Jogo jogo, int id){
        this.jogo = jogo;
        this.id = id;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
