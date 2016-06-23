/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.listerners;

import br.com.controller.ManagerCliente;
import br.com.models.Cliente;
import java.util.HashMap;

/**
 *
 * @author joao
 */
public class ClienteListerner {
    public static final int LIST = 0;
    public static final String INTENT = "INTENT", CLIENT = "CLIENT";
    
    private ManagerCliente managerCliente;

    public ClienteListerner(ManagerCliente managerCliente) {
        this.managerCliente = managerCliente;
    }
    
    public void action(Object arg){
        if(arg != null){
            HashMap<String, Object> map = (HashMap<String, Object>) arg;
            
            int intent = (int) map.get(INTENT);
            
            switch(intent){
                case LIST:{
                    managerCliente.sendList(map.get(CLIENT));
                    break;
                }
            }
        }
    }

}
