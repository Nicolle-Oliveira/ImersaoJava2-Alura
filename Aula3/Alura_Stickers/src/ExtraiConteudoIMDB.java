import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtraiConteudoIMDB implements ExtraiConteudo{
    
    public List<Conteudo> extraiCont(String json){
        /* Extrair so os dados que interessam (nome, poster, classificação) */
        JsonParser JP = new JsonParser();
        List<Map<String, String>> list_atributos = JP.parse(json);

        List<Conteudo> list_conteudos = new ArrayList<>();

        /* Populando a lista de conteudos */
        for (Map<String, String> atributos : list_atributos) {
            
            String urlImagem = atributos.get("image");
            String titulo = atributos.get("title");
            
            Conteudo conteudo = new Conteudo(titulo, urlImagem);

            list_conteudos.add(conteudo);
        }

        return list_conteudos;
    }
}