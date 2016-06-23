/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class ClienteUtils {

    public static Object read(Socket socket) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClienteUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean send(Socket socket, Object arg) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(arg);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ClienteUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

}
