public class ProdutosDTO {
    private Integer id;
    private String nome;
    private Integer valor;
    private String status;

    // Construtor vazio
    public ProdutosDTO() {
    }

    // Construtor com parâmetros
    public ProdutosDTO(Integer id, String nome, Integer valor, String status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString para facilitar debug e logs
    @Override
    public String toString() {
        return "ProdutosDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                '}';
    }
}
