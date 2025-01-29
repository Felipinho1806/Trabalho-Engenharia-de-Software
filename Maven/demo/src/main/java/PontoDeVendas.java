public class PontoDeVendas {
    public String nome;
    public String endereco;
    public String telefone;

    public PontoDeVendas() {

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone != null && telefone.length() > 15) {
            throw new IllegalArgumentException("O telefone não pode ter mais que 15 caracteres.");
        }
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
