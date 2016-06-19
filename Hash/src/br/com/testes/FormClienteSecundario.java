/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author Guilherme Lourenço
 */
public class FormClienteSecundario extends JFrame{
    final private JButton btn1;
    final private JButton btn2;
    final private JButton btn3;
    final private JButton btn4;
    final private JButton btn5;
    final private JButton btn6;
    final private JButton btn7;
    final private JButton btn8;
    final private JButton btn9;
    final private DefaultListModel listaModel;
    final private JList lista;
    final private JButton btnConecta;
    final private JButton btnFecha;
    final private String ladoEscolhido;
    private ClienteTeste c;
    private ArrayList<String> jogo;
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FormClienteSecundario(String lado){
        super("Cliente");
        this.ladoEscolhido = lado;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(800, 400);
        this.btn1 = new JButton();
        this.btn2 = new JButton();
        this.btn3 = new JButton();
        this.btn4 = new JButton();
        this.btn5 = new JButton();
        this.btn6 = new JButton();
        this.btn7 = new JButton();
        this.btn8 = new JButton();
        this.btn9 = new JButton();
        this.btnConecta = new JButton("Conectar Cliente");
        this.btnFecha = new JButton("Fecha");
        this.listaModel = new DefaultListModel();
        this.lista = new JList(listaModel);
        lista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); 
        GridLayout layoutGrade = new GridLayout(3, 3, 10, 10);
        Panel painelGame = new Panel(layoutGrade);
        painelGame.add(btn1);
        painelGame.add(btn2);
        painelGame.add(btn3);
        painelGame.add(btn4);
        painelGame.add(btn5);
        painelGame.add(btn6);
        painelGame.add(btn7);
        painelGame.add(btn8);
        painelGame.add(btn9);
        GridLayout layoutLateral = new GridLayout(3, 1, 20, 20);
        Panel painelLateral = new Panel(layoutLateral);
        painelLateral.add(lista);
        painelLateral.add(btnConecta);
        painelLateral.add(btnFecha);
        GridLayout layoutPrincipal = new GridLayout(1, 2, 10, 10);
        this.setLayout(layoutPrincipal);
        this.getContentPane().add(painelGame);
        this.getContentPane().add(painelLateral);
        c = new ClienteTeste(lado, 12345);
        
        
        btn1.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(0);
        });
        btn2.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(1);
        });
        btn3.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(2);
        });
        btn4.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(3);
        });
        btn5.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(4);
        });
        btn6.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(5);
        });
        btn7.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(6);
        });
        btn8.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(7);
        });
        btn9.addActionListener((java.awt.event.ActionEvent evt) -> {
            guardaPosicaoEscolhida(8);
        });
        this.setVisible(true);
    }
    
    public void guardaPosicaoEscolhida(int posicao){
        GameTeste gc = c.getGameTeste();
        gc.setVez(ladoEscolhido);
        jogo = gc.getJogo();
        jogo.set(posicao, ladoEscolhido);
        
    }
    
}
