import java.util.Date;
import java.util.Objects;

public class Registro {
    private Biologo biologo;
    private Especie especie;
    private Date data_registro;
    private String localizacao;
    private MetodoColeta metodo_de_coleta;

    public Registro(Biologo biologo, Especie especie, Date data_registro, String localizacao, MetodoColeta metodo_de_coleta) {
        this.biologo = biologo;
        this.especie = especie;
        this.data_registro = data_registro;
        this.localizacao = localizacao;
        this.metodo_de_coleta = metodo_de_coleta;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "biologo=" + biologo +
                ", especie=" + especie +
                ", data_registro=" + data_registro +
                ", localizacao='" + localizacao + '\'' +
                ", metodo_de_coleta=" + metodo_de_coleta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registro registro)) return false;
        return getBiologo().equals(registro.getBiologo()) && getEspecie().equals(registro.getEspecie()) && getData_registro().equals(registro.getData_registro()) && getLocalizacao().equals(registro.getLocalizacao()) && getMetodo_de_coleta() == registro.getMetodo_de_coleta();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBiologo(), getEspecie(), getData_registro(), getLocalizacao(), getMetodo_de_coleta());
    }

    public Biologo getBiologo() {
        return biologo;
    }

    public void setBiologo(Biologo biologo) {
        this.biologo = biologo;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public MetodoColeta getMetodo_de_coleta() {
        return metodo_de_coleta;
    }

    public void setMetodo_de_coleta(MetodoColeta metodo_de_coleta) {
        this.metodo_de_coleta = metodo_de_coleta;
    }
}
