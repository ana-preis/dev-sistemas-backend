import java.util.Objects;

public class Biologo extends Usuario{
    private String universidade;
    private String companhia;
    private Integer codigo;

    public Biologo(String nome, String email, String senha, Boolean is_admin, String universidade, String companhia, Integer codigo) {
        super(nome, email, senha, is_admin);
        this.universidade = universidade;
        this.companhia = companhia;
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biologo biologo)) return false;
        if (!super.equals(o)) return false;
        return getUniversidade().equals(biologo.getUniversidade()) && getCompanhia().equals(biologo.getCompanhia()) && getCodigo().equals(biologo.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUniversidade(), getCompanhia(), getCodigo());
    }

    @Override
    public String toString() {
        return "Biologo{" +
                "universidade='" + universidade + '\'' +
                ", companhia='" + companhia + '\'' +
                ", codigo=" + codigo +
                '}';
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
