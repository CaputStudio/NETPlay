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
        startListerner(c);
    }
    
    public Cliente getClient(long id){
        return mapClient.get(id);
    }
    
    public Collection<Cliente> getAll(){
        return mapClient.values();
    }

    public void sendList(Object id) {        
        System.out.println("recebi pedido pela lista de players");
        Cliente c = mapClient.get((long) id);
        long[] ids = new long[mapClient.size()];
        Set<Long> keys = mapClient.keySet();
        int i = 0;
        for (Long key : keys) {
            ids[i++] = mapClient.get(key).getId();
        }
        c.send(ids);
        System.out.println("enviei para o cliente "+c.getId());
    }

    public void interrupt() {
        Set<Cliente> keys = mapThreads.keySet();
        for (Cliente key : keys) {
            mapThreads.get(key).interrupt();
        }
    }
    
    private void startListerner(Cliente c){
        Thread t = new Thread(c);
        t.start();
        mapThreads.put(c, t);
    }
    
    public void delCliente(Cliente c){
        mapClient.remove(c.getId());
        Thread t = mapThreads.remove(c);
        t.interrupt();
    }

    public void sendQuestion(Object foe, Object id) {
        System.out.println("recebi uma questão do cliente "+id+" para o cliente "+foe);
        Cliente c = mapClient.get((long) foe);        
        c.send(makeIntent(ClienteListerner.QUESTION, (long) foe, ClienteListerner.FOE, id));
        System.out.println("enviei para o cliente "+c.getId());
    }
    
    private HashMap<String, Object> makeIntent(int intent, long id, Object... args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(ClienteListerner.INTENT, intent);
        map.put(ClienteListerner.CLIENT, id);
        int i = 0;
        if (args != null) {
            while (i < args.length) {
                map.put(args[i++].toString(), args[i++]);
            }
        }
        return map;
    }

    public void sendAnswer(Object foe, Object anwser) {
        System.out.println("recebi uma resposta para o cliente "+foe);
        Cliente c = mapClient.get(Long.parseLong(foe.toString()));
        c.send(makeIntent(ClienteListerner.ANSWER, c.getId(), ClienteListerner.CONTENT, anwser));
        System.out.println("enviei para o cliente "+c.getId());
    }

    public void sendMove(Object idO, Object content) {
        System.out.println("Enviando movimento");
        long id = (long)idO;
        Object intent = makeIntent(ClienteListerner.JOGADA, id, ClienteListerner.CONTENT, content);
        Cliente c = mapClient.get(id);
        c.send(intent);
        System.out.println("Enviado movimento");
    }
    
    
}
