import java.util.Objects;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Boolean is_admin;

    public Usuario(String nome, String email, String senha, Boolean is_admin) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return getNome().equals(usuario.getNome()) && getEmail().equals(usuario.getEmail()) && getSenha().equals(usuario.getSenha()) && getIs_admin().equals(usuario.getIs_admin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getEmail(), getSenha(), getIs_admin());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
}
