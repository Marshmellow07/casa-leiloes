import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cadastroVIEW extends JFrame {

    private JTextField txtNome;
    private JTextField txtValor;
    private JButton btnCadastrar;
    private JButton btnConsultarProdutos;

    public cadastroVIEW() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Leilões");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Sistema de Leilões");
        lblTitulo.setFont(new java.awt.Font("Lucida Fax", 0, 24));
        lblTitulo.setBounds(180, 20, 300, 30);
        add(lblTitulo);

        JLabel lblSubTitulo = new JLabel("Cadastre um novo produto");
        lblSubTitulo.setBounds(200, 70, 200, 20);
        add(lblSubTitulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(80, 120, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(130, 120, 320, 25);
        add(txtNome);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(80, 160, 80, 25);
        add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(130, 160, 320, 25);
        add(txtValor);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(220, 210, 100, 30);
        btnCadastrar.setBackground(new java.awt.Color(153, 255, 255));
        add(btnCadastrar);

        btnConsultarProdutos = new JButton("Consultar Produtos");
        btnConsultarProdutos.setBounds(190, 270, 160, 30);
        add(btnConsultarProdutos);

        // Eventos
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });

        btnConsultarProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirListagemProdutos();
            }
        });
    }

    private void cadastrarProduto() {
        String nome = txtNome.getText().trim();
        String valorStr = txtValor.getText().trim();

        if (nome.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int valor;
        try {
            valor = Integer.parseInt(valorStr);
            if (valor < 0) {
                JOptionPane.showMessageDialog(this, "Valor deve ser um número positivo.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite um número inteiro.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProdutosDTO produto = new ProdutosDTO();
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setStatus("A Venda");

        ProdutosDAO produtodao = new ProdutosDAO();
        boolean sucesso = produtodao.cadastrarProduto(produto);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtValor.setText("");
    }

    private void abrirListagemProdutos() {
        listagemVIEW listagem = new listagemVIEW();
        listagem.setVisible(true);
    }

    public static void main(String[] args) {
        // Define o Look and Feel Nimbus, se disponível
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            new cadastroVIEW().setVisible(true);
        });
    }
}
