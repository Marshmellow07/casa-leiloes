import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VendasVIEW extends JFrame {

    private JList<String> listaProdutosVendidos;
    private DefaultListModel<String> listaModel;

    public VendasVIEW() {
        super("Produtos Vendidos");
        initComponents();
        carregarProdutosVendidos();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void initComponents() {
        listaModel = new DefaultListModel<>();
        listaProdutosVendidos = new JList<>(listaModel);
        listaProdutosVendidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaProdutosVendidos);

        // Usar BorderLayout simples para expandir o JScrollPane
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarProdutosVendidos() {
        ProdutosDAO dao = new ProdutosDAO();
        try {
            List<ProdutosDTO> produtosVendidos = dao.listarProdutosVendidos();

            listaModel.clear();
            for (ProdutosDTO produto : produtosVendidos) {
                String item = String.format("%s - R$ %.2f", produto.getNome(), produto.getValor());
                listaModel.addElement(item);
            }

            if (produtosVendidos.isEmpty()) {
                listaModel.addElement("Nenhum produto vendido encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar produtos vendidos: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
