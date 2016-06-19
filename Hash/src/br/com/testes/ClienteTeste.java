/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme Lourenço
 */
public class ClienteTeste extends Thread{
    private Jogo gc;
    
    public ClienteTeste(String lado, int porta){
        
          try {
                Socket cliente = new Socket("127.0.0.1",porta);
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                gc = (Jogo)entrada.readObject();
                entrada.close();
                System.out.println("Conexão encerrada");
            }
            catch(Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
    }
    
    public Jogo getGameTeste(){
        return this.gc;
    }
}
