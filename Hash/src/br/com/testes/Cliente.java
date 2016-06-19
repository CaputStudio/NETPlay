/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Guilherme Lourenço
 */
public class Cliente implements Runnable{

    private Socket cliente;

    public Cliente(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(String args[]) throws UnknownHostException, IOException {

        // para se conectar ao servidor, cria-se objeto Socket.
        // O primeiro parâmetro é o IP ou endereço da máquina que
        // se quer conectar e o segundo é a porta da aplicação.
        // Neste caso, usa-se o IP da máquina local (127.0.0.1)
        // e a porta da aplicação ServidorDeEco (12345).
        Socket socket = new Socket("127.0.0.1", 12345);

        /*Cria um novo objeto Cliente com a conexão socket para que seja executado em um novo processo.
        Permitindo assim a conexão de vário clientes com o servidor.*/
        Cliente c = new Cliente(socket);
        Thread t = new Thread(c);
        t.start();
    }


    public void run() {
        try {
            ObjectOutputStream oos;
            System.out.println("O cliente conectou ao servidor");

            
            //Cria  objeto para enviar a mensagem ao servidor
            oos = new ObjectOutputStream(this.cliente.getOutputStream());
            boolean flag = true;
            //Envia mensagem ao servidor
            while(flag){
                
                Passador p = new Passador(new Jogo("X"), 1);
                oos.writeObject(p);
                flag = false;
            }

            oos.close();
            this.cliente.close();
            System.out.println("Fim do cliente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
