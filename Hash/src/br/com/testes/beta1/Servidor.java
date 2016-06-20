/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.beta1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Lourenço
 */
public class Servidor extends ArrayList<SocketListerner> implements Runnable{
    
    private ServerSocket servidor;

    public Servidor(int port, SocketListerner... listerners) throws IOException {
        for (SocketListerner listerner : listerners) {
            super.add(listerner);
        }
        servidor = new ServerSocket (port);        
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = servidor.accept();
                for (SocketListerner listerner : this) {
                    listerner.action(socket);
                }
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
