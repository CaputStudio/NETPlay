/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.models;

import br.com.listerners.ClienteListerner;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class Cliente implements Runnable {

    private Socket socket;
    private long id;
    private ClienteListerner listerner;

    public ClienteListerner getListerner() {
        return listerner;
    }

    public void setListerner(ClienteListerner listerner) {
        this.listerner = listerner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente(Socket socket) {
        this.socket = socket;
    }

    public void send(Object arg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(arg);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object receive() {
        if (!socket.isInputShutdown()) {
            try {
                InputStream is = socket.getInputStream();
                if (is != null) {
                    ObjectInputStream ois = new ObjectInputStream(is);
                    return ois.readObject();
                } else {
                    return null;
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    socket.shutdownInput();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            listerner.action(this.receive());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
