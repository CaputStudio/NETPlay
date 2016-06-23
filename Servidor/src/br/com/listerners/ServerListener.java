/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.listerners;

import br.com.servidor.Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class ServerListener implements Runnable{
    private Servidor servidor;
    private ServerSocket socket;

    public ServerListener(Servidor servidor, ServerSocket socket) {
        this.servidor = servidor;
        this.socket = socket;
    }    

    @Override
    public void run() {
        while (true) {
            try {
                Socket task = socket.accept();
                servidor.addClient(task);
            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
