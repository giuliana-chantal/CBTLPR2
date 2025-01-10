// BIANCA FONSECA DANTAS RIBEIRO - CB3025683 | VINÍCIUS DO NASCIMENTO AYRES - CB3025675

package com.academia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.google.gson.Gson;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CadastroAluno {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Aluno - Academia");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel idadeLabel = new JLabel("Idade:");
        JTextField idadeField = new JTextField();

        JLabel pesoLabel = new JLabel("Peso (kg):");
        JTextField pesoField = new JTextField();

        JLabel alturaLabel = new JLabel("Altura (m):");
        JTextField alturaField = new JTextField();

        JLabel objetivoLabel = new JLabel("Objetivo:");
        JTextField objetivoField = new JTextField();

        JButton incluirButton = new JButton("Incluir");
        JButton limparButton = new JButton("Limpar");
        JButton apresentarButton = new JButton("Apresentar Dados");
        JButton sairButton = new JButton("Sair");

        frame.add(nomeLabel);
        frame.add(nomeField);
        frame.add(idadeLabel);
        frame.add(idadeField);
        frame.add(pesoLabel);
        frame.add(pesoField);
        frame.add(alturaLabel);
        frame.add(alturaField);
        frame.add(objetivoLabel);
        frame.add(objetivoField);
        frame.add(incluirButton);
        frame.add(limparButton);
        frame.add(apresentarButton);
        frame.add(sairButton);

        incluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int idade;
                float peso, altura;
                String objetivo = objetivoField.getText();

                try {
                    idade = Integer.parseInt(idadeField.getText());
                    peso = Float.parseFloat(pesoField.getText());
                    altura = Float.parseFloat(alturaField.getText());
                    incluirNoBanco(nome, idade, peso, altura, objetivo);
                    JOptionPane.showMessageDialog(frame, "Aluno incluído com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao inserir no banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeField.setText("");
                idadeField.setText("");
                pesoField.setText("");
                alturaField.setText("");
                objetivoField.setText("");
            }
        });

        apresentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Aluno> alunos = obterRegistrosDoBanco();
                    Gson gson = new Gson();
                    String json = gson.toJson(alunos);
                    JOptionPane.showMessageDialog(frame, "Dados em JSON:\n" + json);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao consultar o banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static void incluirNoBanco(String nome, int idade, float peso, float altura, String objetivo) throws SQLException {
        String url = "jdbc:mysql://localhost:3307/tp_final";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO academia (nome, idade, peso, altura, objetivo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, idade);
            preparedStatement.setFloat(3, peso);
            preparedStatement.setFloat(4, altura);
            preparedStatement.setString(5, objetivo);

            preparedStatement.executeUpdate();
        }
    }

    private static List<Aluno> obterRegistrosDoBanco() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/tp_final";
        String user = "root";
        String password = "";

        String sql = "SELECT * FROM academia";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                float peso = resultSet.getFloat("peso");
                float altura = resultSet.getFloat("altura");
                String objetivo = resultSet.getString("objetivo");

                Aluno aluno = new Aluno(nome, idade, peso, altura, objetivo);
                alunos.add(aluno);
            }
        }

        return alunos;
    }

    static class Aluno {
        private String nome;
        private int idade;
        private float peso;
        private float altura;
        private String objetivo;

        public Aluno(String nome, int idade, float peso, float altura, String objetivo) {
            this.nome = nome;
            this.idade = idade;
            this.peso = peso;
            this.altura = altura;
            this.objetivo = objetivo;
        }
    }
}
