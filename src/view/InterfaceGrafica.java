package view;

import controller.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfaceGrafica extends JFrame {

    private JTextField[] valorMeses; // Campos de texto para os valores mensais
    private JButton btnVerGraficoMensal; // Botão para ver o gráfico mensal
    private JButton btnRedefinirMeta; // Botão para redefinir a meta anual
    private JButton btnCompararMeta; // Botão para comparar a meta anual
    private JLabel metaAnualLabel; // Rótulo para exibir a meta anual
    private JLabel metaMensalLabel; // Rótulo para exibir a meta mensal
    private JButton btnIrParaSegundaInterface; // Botão para ir para a segunda interface
    private Controlador controlador; // Referência ao controlador

    // Construtor da interface gráfica
    public InterfaceGrafica(Controlador controlador) {
        this.controlador = controlador; // Inicializa o controlador
        setTitle("VALORES DO CONSUMO DE AGUA MENSAIS");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Botão para redefinir a meta anual
        btnRedefinirMeta = new JButton("Redefinir Meta Anual");
        btnRedefinirMeta.setBounds(770, 550, 190, 80);
        btnRedefinirMeta.setFont(new Font("Arial", Font.BOLD, 14));
        btnRedefinirMeta.setForeground(new Color(237, 241, 238));
        btnRedefinirMeta.setBackground(new Color(9, 10, 9));
        add(btnRedefinirMeta);

        // Botão para ver o gráfico Mensal
        btnVerGraficoMensal = new JButton("Ver Gráfico Mensal");
        btnVerGraficoMensal.setBounds(770, 450, 190, 80);
        btnVerGraficoMensal.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerGraficoMensal.setForeground(new Color(237, 241, 238));
        btnVerGraficoMensal.setBackground(new Color(9, 10, 9));
        add(btnVerGraficoMensal);

        // Botão para ver o gráfico Anual
        btnCompararMeta = new JButton("Comparar Meta Anual");
        btnCompararMeta.setBounds(770, 350, 190, 80);
        btnCompararMeta.setFont(new Font("Arial", Font.BOLD, 14));
        btnCompararMeta.setForeground(new Color(237, 241, 238));
        btnCompararMeta.setBackground(new Color(9, 10, 9));
        add(btnCompararMeta);

        // Botão para ir para a segunda interface
        btnIrParaSegundaInterface = new JButton("Segunda Interface ->");
        btnIrParaSegundaInterface.setBounds(770, 250, 190, 80);
        btnIrParaSegundaInterface.setFont(new Font("Arial", Font.BOLD, 14));
        btnIrParaSegundaInterface.setForeground(new Color(237, 241, 238));
        btnIrParaSegundaInterface.setBackground(new Color(9, 10, 9));
        add(btnIrParaSegundaInterface);

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
        metaAnualLabel = new JLabel("Meta Anual: " + controlador.getMetaAnual() + "m³");
        metaAnualLabel.setFont(new Font("Arial", Font.BOLD, 30));
        metaAnualLabel.setBounds(350, 40, 400, 40);
        add(metaAnualLabel);

        // Label para exibir a meta Mensal
        metaMensalLabel = new JLabel("Meta Mensal: " + controlador.getMetaAnual() / 12 + "m³");
        metaMensalLabel.setFont(new Font("Arial", Font.BOLD, 30));
        metaMensalLabel.setBounds(350, 80, 400, 40);
        add(metaMensalLabel);

        // Torna a interface visível
        setVisible(true);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Ver Gráfico Mensal". Quando o
     * botão é clicado, o método associado ao ouvinte será executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerVerGraficoMensal(ActionListener listener) {
        btnVerGraficoMensal.addActionListener(listener);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Redefinir Meta Anual". Quando o
     * botão é clicado, o método associado ao ouvinte será executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerRedefinirMeta(ActionListener listener) {
        btnRedefinirMeta.addActionListener(listener);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Comparar Meta Anual". Quando o
     * botão é clicado, o método associado ao ouvinte será executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerCompararMeta(ActionListener listener) {
        btnCompararMeta.addActionListener(listener);
    }

    /**
     * Adiciona um ouvinte de eventos ao botão "Ir Para Segunda Interface".
     * Quando o botão é clicado, o método associado ao ouvinte será executado.
     *
     * @param listener O ouvinte de eventos a ser adicionado.
     */
    public void adicionarListenerIrParaSegundaInterface(ActionListener listener) {
        btnIrParaSegundaInterface.addActionListener(listener);
    }

    /**
     * Obtém o valor digitado pelo usuário para um determinado mês.
     *
     * @param index O índice do mês (de 0 a 11).
     * @return O valor do mês como uma String.
     */
    public String getValorMes(int index) {
        return valorMeses[index].getText();
    }

    /**
     * Define a meta anual exibida na interface.
     *
     * @param metaAnual A meta anual em metros cúbicos. Essa informação será
     * atualizada na interface.
     */
    public void setMetaAnual(int metaAnual) {
        // Atualiza a exibição da meta anual na interface
        metaAnualLabel.setText("Meta Anual: " + metaAnual + "m³");
        // Calcula e exibe a meta mensal com base na meta anual
        metaMensalLabel.setText("Meta Mensal: " + (metaAnual / 12 + "m³"));
    }
}
