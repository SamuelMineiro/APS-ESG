package controller;

import model.DadosDoGrafico;
import view.InterfaceGrafica;
import view.SegundaInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Arrays;

public class Controlador {

    private InterfaceGrafica view; // Referência à interface principal
    private SegundaInterface segundaInterface; // Referência à segunda interface
    private int metaAnual = 8440; // Meta anual em metros cúbicos
    private double metaAnual_dinheiro = 80000; // Meta anual em valor monetário

    // Construtor do controlador
    public Controlador() {

        this.view = new InterfaceGrafica(this); // Inicializa a interface principal

        // Adiciona listeners aos botões da interface principal
        this.view.adicionarListenerVerGraficoMensal(new GeradorDeGraficoListener());
        this.view.adicionarListenerRedefinirMeta(new RedefinirMetaListener());
        this.view.adicionarListenerCompararMeta(new CompararMetaListener());

        // Adiciona listener ao botão para ir para a segunda interface
        this.view.adicionarListenerIrParaSegundaInterface(new IrParaSegundaInterfaceListener());

    }

    // Listener para o botão "Ver Gráfico Mensal"
    private class GeradorDeGraficoListener implements ActionListener {

        // Executado quando o botão é clicado
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer[] num = new Integer[12];// Array para armazenar os valores mensais
            int metaMensal = metaAnual / 12; // Calcula a meta mensal
            try {
                // Obtém os valores mensais digitados pelo usuário
                for (int i = 0; i < 12; i++) {
                    num[i] = Integer.valueOf(view.getValorMes(i));
                }
                // Cria os dados do gráfico com os valores mensais
                DadosDoGrafico dados = new DadosDoGrafico(metaMensal, num);
                // Cria o gráfico
                JFreeChart grafico = criarGrafico(dados);
                // Exibe o gráfico
                exibirGrafico(grafico);
            } catch (NumberFormatException ex) {
                // Exibe mensagem de erro se algum valor não for um número inteiro
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira apenas números Inteiro e NÃO deixe os valores dos meses em branco.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para o botão "Redefinir Meta Anual"
    private class RedefinirMetaListener implements ActionListener {

        // Executado quando o botão é clicado
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtém a nova meta anual digitada pelo usuário
            String input = JOptionPane.showInputDialog(null,
                    "Digite a nova meta anual:",
                    "Redefinir Meta Anual",
                    JOptionPane.PLAIN_MESSAGE);
            try {
                // Atualiza a meta anual
                metaAnual = Integer.parseInt(input);
                // Atualiza a meta anual na interface
                view.setMetaAnual(metaAnual);
            } catch (NumberFormatException ex) {
                // Exibe mensagem de erro se o valor não for um número inteiro
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira apenas números Inteiro\n"
                        + " e não deixe valores dos meses em branco.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para o botão "Comparar Meta Anual"
    private class CompararMetaListener implements ActionListener {
        // Executado quando o botão é clicado
        @Override
        public void actionPerformed(ActionEvent e) {
            // Array para armazenar os valores mensais
            Integer[] num = new Integer[12];
            try {
                // Obtém os valores mensais digitados pelo usuário
                for (int i = 0; i < 12; i++) {
                    num[i] = Integer.valueOf(view.getValorMes(i));
                }
                // Calcula o total do consumo realizado no ano
                int metaRealizada = Arrays.stream(num).mapToInt(Integer::intValue).sum();
                // Cria o dataset para o gráfico
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                // Adiciona os valores ao dataset
                dataset.addValue(metaAnual, "Meta Anual", "Meta Anual");
                dataset.addValue(metaRealizada, "Meta Realizada", "Meta Anual");
                // Cria o gráfico de barras
                JFreeChart grafico = ChartFactory.createBarChart(
                        "Comparação da Meta Anual",
                        "",
                        "M³",
                        dataset
                );
                // Exibe o gráfico
                exibirGrafico(grafico);
            } catch (NumberFormatException ex) {
                // Exibe mensagem de erro se algum valor não for um número inteiro
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira apenas números Inteiro e NÃO deixe os valores dos meses em branco.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para o botão "Ver Gráfico Mensal" da segunda interface
private class GeradorDeGraficoListener_2 implements ActionListener {
    // Executado quando o botão é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        // Array para armazenar os valores mensais
        Double[] num = new Double[12];
        // Calcula a meta mensal em valor monetário
        double metaMensal = metaAnual_dinheiro / 12; 
        try {
            // Obtém os valores mensais digitados pelo usuário
            for (int i = 0; i < 12; i++) {
                num[i] = Double.valueOf(segundaInterface.getValorMes(i));
            }
            // Cria os dados do gráfico com os valores mensais
            DadosDoGrafico dados = new DadosDoGrafico(metaMensal, num);
            // Cria o gráfico
            JFreeChart grafico = criarGrafico_2(dados);
            // Exibe o gráfico
            exibirGrafico(grafico);
        } catch (NumberFormatException ex) {
            // Exibe mensagem de erro se algum valor não for um número
            JOptionPane.showMessageDialog(null,
                    "Por favor, insira apenas números e NÃO deixe os valores dos meses em branco.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Listener para o botão "Redefinir Meta Anual" da segunda interface
private class RedefinirMetaListener_2 implements ActionListener {
    // Executado quando o botão é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtém a nova meta anual em valor monetário digitada pelo usuário
        String input = JOptionPane.showInputDialog(null,
                "Digite a nova meta anual:",
                "Redefinir Meta Anual",
                JOptionPane.PLAIN_MESSAGE);
        try {
            // Atualiza a meta anual em valor monetário
            metaAnual_dinheiro = Double.parseDouble(input);
            // Atualiza a meta anual na segunda interface
            segundaInterface.setMetaAnual(metaAnual_dinheiro);
        } catch (NumberFormatException ex) {
            // Exibe mensagem de erro se o valor não for um número
            JOptionPane.showMessageDialog(null,
                    "Por favor, insira apenas números e divida as casas decimais usando '.'\n "
                    + "ex: 10000.90 ",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Listener para o botão "Comparar Meta Anual" da segunda interface
private class CompararMetaListener_2 implements ActionListener {
    // Executado quando o botão é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        // Array para armazenar os valores mensais em valor monetário
        Double[] num = new Double[12];
        try {
            // Obtém os valores mensais digitados pelo usuário
            for (int i = 0; i < 12; i++) {
                num[i] = Double.valueOf(segundaInterface.getValorMes(i));
            }
            // Calcula o total do consumo realizado no ano em valor monetário
            double metaRealizada = Arrays.stream(num).mapToDouble(Double::doubleValue).sum();
            // Cria o dataset para o gráfico
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // Adiciona os valores ao dataset
            dataset.addValue(metaAnual_dinheiro, "Meta Anual", "Meta Anual");
            dataset.addValue(metaRealizada, "Meta Realizada", "Meta Anual");
            // Cria o gráfico de barras
            JFreeChart grafico = ChartFactory.createBarChart(
                    "Comparação da Meta Anual",
                    "",
                    "valor em Reais",
                    dataset
            );
            // Exibe o gráfico
            exibirGrafico(grafico);
        } catch (NumberFormatException ex) {
            // Exibe mensagem de erro se algum valor não for um número
            JOptionPane.showMessageDialog(null,
                    "Por favor, insira apenas números e NÃO deixe os valores dos meses em branco.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}


    // Método para criar o gráfico com base nos dados fornecidos
    private JFreeChart criarGrafico(DadosDoGrafico dados) {
        DefaultCategoryDataset dataset = dados.criarDataset();
        JFreeChart grafico = ChartFactory.createBarChart(
                "Metas do Ano",
                "Meses",
                "M³",
                dataset
        );
        return grafico;
    }

    // Método para criar o gráfico com base nos dados fornecidos
    private JFreeChart criarGrafico_2(DadosDoGrafico dados) {
        DefaultCategoryDataset dataset = dados.criarDataset_2();
        JFreeChart grafico = ChartFactory.createBarChart(
                "Metas do Ano",
                "Meses",
                "valor em Reais",
                dataset
        );
        return grafico;
    }

    // Método para exibir o gráfico em uma nova janela
    private void exibirGrafico(JFreeChart grafico) {
        JFrame frame = new JFrame("Gráfico de Barras");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        ChartPanel panel = new ChartPanel(grafico);
        panel.setPreferredSize(new java.awt.Dimension(800, 600));
        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }
    
    // Listener para o botão "Ir para Segunda Interface"
    private class IrParaSegundaInterfaceListener implements ActionListener {
        // Executado quando o botão é clicado
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abre a segunda interface
            abrirSegundaInterface();
        }
    }

    // Método para abrir a segunda interface
    private void abrirSegundaInterface() {
        segundaInterface = new SegundaInterface(this);
        // Adiciona listeners aos botões da segunda interface
        this.segundaInterface.adicionarListenerVerGraficoMensal_2(new GeradorDeGraficoListener_2());
        this.segundaInterface.adicionarListenerRedefinirMeta_2(new RedefinirMetaListener_2());
        this.segundaInterface.adicionarListenerCompararMeta_2(new CompararMetaListener_2());
    }

    // Método para obter a meta anual em metros cúbicos
    public int getMetaAnual() {
        return metaAnual;
    }

    // Método para obter a meta anual em valor monetário
    public double getMetaAnual_dinheiro() {
        return metaAnual_dinheiro;
    }

    // Método main
    public static void main(String[] args) {
        // Instancia o controlador
        new Controlador();
    }
}
