package model;

import org.jfree.data.category.DefaultCategoryDataset;

public class DadosDoGrafico {
    private int metaMensal; // Meta mensal em metros cúbicos
    private double metaMensal_dinheiro; // Meta mensal em valor monetário
    private Double[] dados_dinheiro; // Dados mensais em valor monetário
    private Integer[] dados; // Dados mensais em metros cúbicos

    // Construtor para meta e dados em metros cúbicos
    public DadosDoGrafico(int metaMensal, Integer[] dados) {
        this.metaMensal = metaMensal;
        this.dados = dados;
    }
    
    // Construtor para meta e dados em valor monetário
    public DadosDoGrafico(double metaMensal, Double[] dados) {
        this.metaMensal_dinheiro = metaMensal;
        this.dados_dinheiro = dados;
    }
    
    // Cria um dataset para o gráfico com meta e dados em valor monetário
    public DefaultCategoryDataset criarDataset_2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < 12; i++) {
            dataset.addValue(metaMensal_dinheiro, "Meta Ideal", obterMes(i));
            dataset.addValue(dados_dinheiro[i], "Meta Realizada", obterMes(i));
        }
        return dataset;
    }

    // Cria um dataset para o gráfico com meta e dados em metros cúbicos
    public DefaultCategoryDataset criarDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < 12; i++) {
            dataset.addValue(metaMensal, "Meta Ideal", obterMes(i));
            dataset.addValue(dados[i], "Meta Realizada", obterMes(i));
        }
        return dataset;
    }

    // Retorna o nome do mês com base no índice
    private String obterMes(int indice) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return meses[indice];
    }
}
