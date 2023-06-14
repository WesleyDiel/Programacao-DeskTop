package entidade;


public class Estado {
    private int id;
    private String uf;
    private String descricao;

    public Estado(int id, String uf, String descricao) {
        this.id = id;
        this.uf = uf;
        this.descricao = descricao;
    }

    public Estado(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
