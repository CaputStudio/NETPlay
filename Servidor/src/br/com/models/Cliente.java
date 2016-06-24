/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.models;

import br.com.listerners.ClienteListerner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
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
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;

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
        ObjectOutputStream oos = null;
        try {
            oos = createOOS(socket);
            oos.writeObject(arg);
            System.out.println("Enviei");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object receive() {
        if(!socket.isInputShutdown() && socket.isConnected()){
            try {
                ObjectInputStream ois = createOIS(socket);                
                return ois.readObject();
            } catch (SocketException s){
                listerner.getManagerCliente().delCliente(this);
                System.out.println("Cliente "+this.getId()+" desconectou.");
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Ouvindo o cliente "+this.getId());
            listerner.action(this.receive());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public ObjectOutputStream createOOS(Socket socket) throws IOException{
        if(oos != null){
            return oos;
        }else{
            oos = new ObjectOutputStream(socket.getOutputStream());
            return oos;
        }
    }
    
    public ObjectInputStream createOIS(Socket socket) throws IOException{
        if(ois != null){
            return ois;
        }else{
            ois = new ObjectInputStream(socket.getInputStream());
            return ois;
        }
    }

}
