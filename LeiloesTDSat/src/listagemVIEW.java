import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class listagemVIEW extends JFrame {

    private JTable listaProdutos;
    private JTextField id_produto_venda;
    private JButton btnVender;
    private JButton btnVendas;
    private JButton btnVoltar;

    public listagemVIEW() {
        initComponents();
        listarProdutos();
    }

    private void initComponents() {
        setTitle("Listagem de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabela com modelo padrão
        listaProdutos = new JTable(new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Nome", "Valor", "Status" }) {
            public boolean isCellEditable(int row, int column) {
                return false; // tabela só leitura
            }
        });
        JScrollPane scrollPane = new JScrollPane(listaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com campo e botões
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout());

        painelInferior.add(new JLabel("ID do Produto para Vender:"));
        id_produto_venda = new JTextField(10);
        painelInferior.add(id_produto_venda);

        btnVender = new JButton("Vender");
        btnVender.addActionListener(e -> btnVenderActionPerformed());
        painelInferior.add(btnVender);

        btnVendas = new JButton("Produtos Vendidos");
        btnVendas.addActionListener(e -> btnVendasActionPerformed());
        painelInferior.add(btnVendas);

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> btnVoltarActionPerformed());
        painelInferior.add(btnVoltar);

        add(painelInferior, BorderLayout.SOUTH);
    }

    private void listarProdutos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) listaProdutos.getModel();
            model.setRowCount(0); // limpa tabela

            var listagem = produtosdao.listarProdutos();

            for (ProdutosDTO produto : listagem) {
                model.addRow(new Object[] {
                        produto.getId(),
                        produto.getNome(),
                        produto.getValor(),
                        produto.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao listar produtos: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnVenderActionPerformed() {
        String idText = id_produto_venda.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira o ID do produto para vender.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            ProdutosDAO produtosdao = new ProdutosDAO();
            produtosdao.venderProduto(id);
            listarProdutos(); // atualiza lista após venda
            JOptionPane.showMessageDialog(this,
                    "Produto vendido com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "ID inválido! Digite apenas números.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao vender o produto: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnVendasActionPerformed() {
        VendasVIEW vendas = new VendasVIEW();
        vendas.setVisible(true);
    }

    private void btnVoltarActionPerformed() {
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new listagemVIEW().setVisible(true);
        });
    }
}
