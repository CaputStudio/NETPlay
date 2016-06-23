/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.com.utils.ClienteUtils;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class TesteA {
    public static final int LIST = 0;
    public static final String INTENT = "INTENT", CLIENT = "CLIENT";
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            long id = (long) ClienteUtils.read(socket);            
            HashMap<String, Object> map = new HashMap<>();
            map.put(CLIENT, id);
            map.put(INTENT, LIST);
            
            ClienteUtils.send(socket, map);
            
            long[] ids = (long[]) ClienteUtils.read(socket);
            for (long j : ids) {
                System.out.println(j);
            }
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
