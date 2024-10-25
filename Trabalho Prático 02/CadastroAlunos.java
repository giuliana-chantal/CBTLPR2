package com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroAlunos extends JFrame {
    private List<Aluno> alunos;
    private JTextField nomeField;

    public CadastroAlunos() {
        alunos = new ArrayList<>();
        setTitle("Cadastro de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton mostrarButton = new JButton("Mostrar");
        JButton limparButton = new JButton("Limpar");
        JButton sairButton = new JButton("Sair");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                if (!nome.isEmpty()) {
                    alunos.add(new Aluno(nome));
                    nomeField.setText("");
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome.");
                }
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensagem = new StringBuilder();
                for (Aluno aluno : alunos) {
                    mensagem.append(aluno.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, mensagem.toString(), "Lista de Alunos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeField.setText("");
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(nomeLabel);
        add(nomeField);
        add(cadastrarButton);
        add(mostrarButton);
        add(limparButton);
        add(sairButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroAlunos();
    }
}
