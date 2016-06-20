/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.beta1;

import br.com.testes.Jogo;
import java.net.Socket;

/**
 *
 * @author Guilherme Lourenço
 */
public class GameManager implements SocketListerner {

    private static int ids = 0;
    public static int MAX = 3;
    public Jogo[] jogos;

    public GameManager() {
        jogos = new Jogo[MAX];
        for (int i = 0; i < MAX; i++) {
            jogos[i] = new Jogo(ids++);
        }
    }

    @Override
    public void action(Socket socket) {
    }

}
