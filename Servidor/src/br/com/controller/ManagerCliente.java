/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.listerners.ClienteListerner;
import br.com.models.Cliente;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author joao
 */
public class ManagerCliente {
    private long ids = 1;
    
    private HashMap<Long, Cliente> mapClient;
    private HashMap<Cliente, Thread> mapThreads;

    public ManagerCliente() {
        mapClient = new HashMap<>();
        mapThreads = new HashMap<>();
    }

    public void addClient(Socket socket) {
        Cliente c = new Cliente(socket);
        c.setId(ids++);
        mapClient.put(c.getId(), c);
        c.send(c.getId());
        c.setListerner(new ClienteListerner(this));
        Thread t = new Thread(c);
        t.start();
        mapThreads.put(c, t);
    }
    
    public Cliente getClient(long id){
        return mapClient.get(id);
    }
    
    public Collection<Cliente> getAll(){
        return mapClient.values();
    }

    public void sendList(Object id) {
        Cliente c = mapClient.get((long) id);
        long[] ids = new long[mapClient.size()];
        Set<Long> keys = mapClient.keySet();
        int i = 0;
        for (Long key : keys) {
            ids[i++] = mapClient.get(key).getId();
        }
        c.send(ids);
    }

}
