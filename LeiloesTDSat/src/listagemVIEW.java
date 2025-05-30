import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

public class listagemVIEW extends javax.swing.JFrame {

    public listagemVIEW() {
        initComponents();
        listarProdutos();
    }

    // Método para listar produtos na tabela
    private void listarProdutos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) listaProdutos.getModel();
            model.setRowCount(0); // limpa tabela

            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutos();

            for (ProdutosDTO produto : listagem) {
                model.addRow(new Object[] {
                        produto.getId(),
                        produto.getNome(),
                        produto.getValor(),
                        produto.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar produtos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {
        String idText = id_produto_venda.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o ID do produto para vender.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            ProdutosDAO produtosdao = new ProdutosDAO();
            produtosdao.venderProduto(id);
            listarProdutos(); // atualiza lista após venda
            JOptionPane.showMessageDialog(this, "Produto vendido com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido! Digite apenas números.",
                    "Erro de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao vender o produto: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {
        VendasVIEW vendas = new VendasVIEW();
        vendas.setVisible(true);
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new listagemVIEW().setVisible(true);
        });
    }

    // ... initComponents() e variáveis geradas pelo NetBeans seguem iguais
}
