/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.net;

import br.com.form.FormJogo;
import br.com.form.FormPrincipal;
import br.com.utils.ClienteUtils;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class ManagerInput {

    public static final int LIST = 0, INVITE = 1, QUESTION = 2, ANSWER = 3, JOGADA = 4;
    public static final String INTENT = "INTENT", CLIENT = "CLIENT", FOE = "FOE", CONTENT = "CONTENT";

    private Thread listerner;
    private ListernerCliente lc;
    private FormPrincipal main;
    private long id;
    private Socket socket;
    private FormJogo formJogo;

    public ManagerInput(FormPrincipal main, Socket socket, long id) {
        this.main = main;
        this.id = id;
        this.socket = socket;
        lc = new ListernerCliente(socket, this);
        Thread t = new Thread(lc);
        t.start();
        lc.setListen(true);
    }

    public FormJogo getFormJogo() {
        return formJogo;
    }

    public void setFormJogo(FormJogo formJogo) {
        this.formJogo = formJogo;
    }

    private Object content;

    public void doTask(Object arg) {
        if (arg != null) {
            try {
                HashMap<String, Object> map = (HashMap<String, Object>) arg;

                int intent = (int) map.get(INTENT);

                switch (intent) {
                    case QUESTION: {
                        showQuestion(map);
                        break;
                    }
                    case ANSWER: {
                        showAnswer(map.get(CONTENT));
                        break;
                    }
                    case JOGADA: {
                        receiveMove(map);
                        break;
                    }
                    default: {
                        System.out.println("default="+intent);
                    }
                }
            } catch (Exception e) {
                content = arg;
            }
        }
    }

    public long[] getPlayers() {
        ClienteUtils.send(socket, makeIntent(LIST, id));
        while (content == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManagerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long[] players = (long[]) content;
        return players;
    }

    public void invitePlayer(long id, long foe) {
        ClienteUtils.send(socket, makeIntent(INVITE, id, FOE, foe));
    }

    private HashMap<String, Object> makeIntent(int intent, long id, Object... args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(INTENT, intent);
        map.put(CLIENT, id);
        int i = 0;
        if (args != null) {
            while (i < args.length) {
                map.put(args[i++].toString(), args[i++]);
            }
        }
        return map;
    }

    private void showQuestion(HashMap<String, Object> map) {
        String foe = map.get(FOE).toString();
        String msg = "O jogador " + foe + " esta chamando você para jogar uma partida.";
        int anwser = main.getStatus() == 0 ? JOptionPane.showConfirmDialog(main, msg) : JOptionPane.CANCEL_OPTION;
        if (anwser == JOptionPane.OK_OPTION) {
            main.openPartida(foe);
        }
        ClienteUtils.send(socket, makeIntent(ANSWER, id, FOE, foe, CONTENT, anwser));
    }

    private void showAnswer(Object arg) {
        int answer = (int) arg;
        if (answer == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(main, "Convite Aceito");
            main.openPartida();
        } else {
            JOptionPane.showMessageDialog(main, "Convite Negado");
        }
    }

    public void sendMove(int x, int y, long id, long foe) {
        Object intent = makeIntent(JOGADA, id, FOE, foe, CONTENT, new Integer[]{x, y});
        ClienteUtils.send(socket, intent);
    }

    public void receiveMove(HashMap<String, Object> map) {
        System.out.println("Jogada chegando");
        Integer[] pos = (Integer[]) map.get(CONTENT);
        formJogo.postJogada(pos[0], pos[1]);
        System.out.println("Postei jogada");
    }

}
