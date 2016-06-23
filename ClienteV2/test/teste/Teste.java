/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.com.utils.ClienteUtils;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class Teste {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            long id = (long) ClienteUtils.read(socket);
            System.out.println(id);
            System.out.print("Enviando--> ");
            ClienteUtils.send(socket, "Hello");
            System.out.print("ok");
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
