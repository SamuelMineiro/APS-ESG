package view;

import controller.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class SegundaInterface extends JFrame {

    private JTextField[] valorMeses; // Campos de texto para os valores mensais
    private JButton btnVerGraficoMensal_2; // Botão para ver o gráfico mensal
    private JButton btnRedefinirMeta_2; // Botão para redefinir a meta anual
    private JButton btnCompararMeta_2; // Botão para comparar a meta anual
    private JLabel metaAnualLabel; // Rótulo para exibir a meta anual
    private JLabel metaMensalLabel; // Rótulo para exibir a meta mensal
    private Controlador controlador; // Referência ao controlador
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));// variavel para formatar os numeros com padrão br

    // Construtor da segunda interface
    public SegundaInterface(Controlador controlador) {
        this.controlador = controlador; // Inicializa o controlador
        setTitle("VALORES MENSAIS DA CONTA DE AGUA");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Botão para redefinir a meta anual
        btnRedefinirMeta_2 = new JButton("Redefinir Meta Anual");
        btnRedefinirMeta_2.setBounds(770, 550, 190, 80);
        btnRedefinirMeta_2.setFont(new Font("Arial", Font.BOLD, 14));
        btnRedefinirMeta_2.setForeground(new Color(237, 241, 238));
        btnRedefinirMeta_2.setBackground(new Color(9, 10, 9));
        add(btnRedefinirMeta_2);

        // Botão para ver o gráfico Mensal
        btnVerGraficoMensal_2 = new JButton("Ver Gráfico Mensal");
        btnVerGraficoMensal_2.setBounds(770, 450, 190, 80);
        btnVerGraficoMensal_2.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerGraficoMensal_2.setForeground(new Color(237, 241, 238));
        btnVerGraficoMensal_2.setBackground(new Color(9, 10, 9));
        add(btnVerGraficoMensal_2);

        // Botão para ver o gráfico Anual
        btnCompararMeta_2 = new JButton("Comparar Meta Anual");
        btnCompararMeta_2.setBounds(770, 350, 190, 80);
        btnCompararMeta_2.setFont(new Font("Arial", Font.BOLD, 14));
        btnCompararMeta_2.setForeground(new Color(237, 241, 238));
        btnCompararMeta_2.setBackground(new Color(9, 10, 9));
        add(btnCompararMeta_2);

        int localizacao = 200;
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        //caixa de entrade de valores mensais
        valorMeses = new JTextField[12];
        for (int i = 0; i < 12; i++) {
            valorMeses[i] = new JTextField();
            valorMeses[i].setBounds(i < 6 ? 170 : 470, (localizacao + (50 * (i % 6))), 120, 40);
            valorMeses[i].setFont(new Font("Arial", Font.BOLD, 20));
            add(valorMeses[i]);
        }

        // Labels para os nomes dos meses
        for (int i = 0; i < 12; i++) {
            JLabel label = new JLabel("Valor de " + meses[i] + ":");
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setBounds(i < 6 ? 10 : 310, (localizacao + (50 * (i % 6))), 150, 40);
            add(label);
        }

        // Label para exibir a meta Anual
        metaAnualLabel = new JLabel("Meta Anual: " + formatter.format(controlador.getMetaAnual_dinheiro()));
        metaAnualLabel.setFont(new Font("Arial", Font.BOLD, 30));
        metaAnualLabel.setBounds(300, 40, 800, 40);
        add(metaAnualLabel);

        // Label para exibir a meta Mensal
        metaMensalLabel = new JLabel("Meta Mensal: " + formatter.format(controlador.getMetaAnual_dinheiro() / 12));
        metaMensalLabel.setFont(new Font("Arial", Font.BOLD, 30));
        metaMensalLabel.setBounds(300, 80, 800, 40);
        add(metaMensalLabel);

        // Torna a interface visível
        setVisible(true);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Ver Gráfico Mensal" da segunda
     * interface. Quando o botão é clicado, o método associado ao ouvinte será
     * executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerVerGraficoMensal_2(ActionListener listener) {
        btnVerGraficoMensal_2.addActionListener(listener);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Redefinir Meta Anual" da segunda
     * interface. Quando o botão é clicado, o método associado ao ouvinte será
     * executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerRedefinirMeta_2(ActionListener listener) {
        btnRedefinirMeta_2.addActionListener(listener);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Comparar Meta Anual" da segunda
     * interface. Quando o botão é clicado, o método associado ao ouvinte será
     * executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerCompararMeta_2(ActionListener listener) {
        btnCompararMeta_2.addActionListener(listener);
    }

    /**
     * Obtém o valor digitado pelo usuário para um determinado mês na segunda
     * interface.
     *
     * @param index O índice do mês (de 0 a 11).
     * @return O valor do mês como uma String.
     */
    public String getValorMes(int index) {   
        return valorMeses[index].getText();
    }

    /**
     * Define a meta anual exibida na segunda interface.
     *
     * @param metaAnual A nova meta anual em metros cúbicos. Essa informação
     * será atualizada na interface.
     */
    public void setMetaAnual(double metaAnual) {
        // Atualiza a exibição da meta anual na segunda interface
        metaAnualLabel.setText("Meta Anual: " + formatter.format(metaAnual));
        // Calcula e exibe a meta mensal com base na meta anual na segunda interface
        metaMensalLabel.setText("Meta Mensal: " + formatter.format(metaAnual / 12));
    }
}
