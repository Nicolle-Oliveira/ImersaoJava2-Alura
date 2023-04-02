public class Conteudo {
    
    /* Nota - "final" não permite a variável mudar seu valor depois de criada */
    private final String titulo;
    private final String urlImagem;

    public Conteudo(String titulo_t, String urlImagem_t) {
        this.titulo = titulo_t;
        this.urlImagem = urlImagem_t;
    }

    /* Getters */
    public String getTitulo() {
        return titulo;
    }
    public String getUrlImagem() {
        return urlImagem;
    }
    
}
