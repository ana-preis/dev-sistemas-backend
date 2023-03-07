import java.util.Objects;

public class Estudante extends Usuario{

    private String universidade;

    public Estudante(String nome, String email, String senha, Boolean is_admin, String universidade) {
        super(nome, email, senha, is_admin);
        this.universidade = universidade;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "universidade='" + universidade + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudante estudante)) return false;
        if (!super.equals(o)) return false;
        return getUniversidade().equals(estudante.getUniversidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUniversidade());
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }
}
