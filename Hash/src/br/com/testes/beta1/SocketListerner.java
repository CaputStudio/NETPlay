/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.beta1;

import java.net.Socket;

/**
 *
 * @author Guilherme Lourenço
 */
public interface SocketListerner {
    public void action(Socket socket);
}
