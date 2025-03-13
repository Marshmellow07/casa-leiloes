public class VendasVIEW extends JFrame {
    public VendasVIEW() {
        setTitle("Produtos Vendidos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ProdutosDAO dao = new ProdutosDAO();
        List<Produto> produtosVendidos = dao.listarProdutosVendidos();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Produto produto : produtosVendidos) {
            model.addElement(produto.getNome() + " - R$ " + produto.getPreco());
        }

        JList<String> lista = new JList<>(model);
        add(new JScrollPane(lista));

        setVisible(true);
    }
}
