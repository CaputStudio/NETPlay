/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.net;

import br.com.utils.ClienteUtils;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class ListernerCliente implements Runnable {

    private Socket socket;
    private ManagerInput managerInput;
    private boolean listen;

    public ListernerCliente(Socket socket, ManagerInput managerInput) {
        this.socket = socket;
        this.managerInput = managerInput;
    }

    public boolean isListen() {
        return listen;
    }

    public void setListen(boolean listen) {
        this.listen = listen;        
    }

    @Override
    public void run() {
        while (true) {
            if (listen) {
                managerInput.doTask(ClienteUtils.read(socket));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListernerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
