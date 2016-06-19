/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Guilherme Lourenço
 */
public class Servidor implements Runnable{
    public Socket cliente;

    public Servidor(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(String[] args)  throws IOException{     

        //Cria um socket na porta 12345
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println("Porta 12345 aberta!");

        // Aguarda alguém se conectar. A execução do servidor
        // fica bloqueada na chamada do método accept da classe
        // ServerSocket. Quando alguém se conectar ao servidor, o
        // método desbloqueia e retorna com um objeto da classe
        // Socket, que é uma porta da comunicação.
        System.out.println("Aguardando conexão do cliente...");   

        while (true) {
          Socket cliente = servidor.accept();
          // Cria uma thread do servidor para tratar a conexão
          Servidor tratamento = new Servidor(cliente);
          Thread t = new Thread(tratamento);
          // Inicia a thread para o cliente conectado
          t.start();
        }
    }

    /* A classe Thread, que foi instancia no servidor, implementa Runnable.
       Então você terá que implementar sua lógica de troca de mensagens dentro deste método 'run'.
    */
    public void run(){
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getCanonicalHostName());
        System.out.println("Porta: " + this.cliente.getPort());

        try {
            ObjectInputStream ois = new ObjectInputStream(this.cliente.getInputStream());
            boolean flag = true;
            while(flag){
                GameTeste gc = (GameTeste) ois.readObject();
                System.out.println(gc.getVez());
            }
            ois.close();
            this.cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
