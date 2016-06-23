/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.listerners.ServerListener;
import br.com.controller.ManagerCliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author joao
 */
public class Servidor{
    private JTextArea out;
    private int port;
    private Thread listener;
    private ServerSocket socket;
    private ManagerCliente managerCliente;

    public Servidor(JTextArea out, int port) {
        this.out = out;
        this.port = port;
        managerCliente = new ManagerCliente();
    }
    
    public void start(){
        try {
            socket = new ServerSocket(port);
            out.append("servidor iniciado na porta: "+port+"\n");
            listener = new Thread(new ServerListener(this, socket));
            listener.start();            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addClient(Socket task){
        out.append("Cliente se conectou\n");        
        managerCliente.addClient(task);
    }
    
    public void interrupt(){
        listener.interrupt();
    }

    public JTextArea getOut() {
        return out;
    }

    public void setOut(JTextArea out) {
        this.out = out;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
