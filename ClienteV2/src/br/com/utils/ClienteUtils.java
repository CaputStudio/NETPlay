/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class ClienteUtils {

    private static ObjectInputStream ois = null;
    private static ObjectOutputStream oos = null;

    public static Object read(Socket socket) {
        try {
            ObjectInputStream ois = createOIS(socket);
            return ois.readObject();
        } catch (Exception e){
            return null;
        }
    }

    public static boolean send(Socket socket, Object arg) {
        ObjectOutputStream oos = null;
        try {
            oos = createOOS(socket);
            oos.writeObject(arg);
            System.out.println("Enviei");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObjectOutputStream createOOS(Socket socket) throws IOException {
        if (oos != null) {
            return oos;
        } else {
            oos = new ObjectOutputStream(socket.getOutputStream());
            return oos;
        }
    }

    public static ObjectInputStream createOIS(Socket socket) throws IOException {
        if (ois != null) {
            return ois;
        } else {
            ois = new ObjectInputStream(socket.getInputStream());
            return ois;
        }
    }

}
