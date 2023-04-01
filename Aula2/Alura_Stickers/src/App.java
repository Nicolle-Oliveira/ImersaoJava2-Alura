import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        

        /* Fazer uma conexao HTTP e buscar os top 250 filmes */
        
        /* Nota - Basicamente fizemos o trabalho de um navegador */
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        URI adress = URI.create(url);
        /*Nota -  Java reconhece que 'var' é do tipo 'HttpClient' */
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> resposta = client.send(request, BodyHandlers.ofString());
        String body = resposta.body();

        /* Extrair so os dados que interessam (nome, poster, classificação) */

        JsonParser JP = new JsonParser();
        List<Map<String, String>> list_filmes = JP.parse(body);

        /* Exibir e manipular os dados */
        int i = 0;
        GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
        for (Map<String,String> filme : list_filmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.CriaStikers(inputStream, nomeArquivo, "KSKSKS"+i);
            i++;

            System.out.println(titulo + "\n\n");

        }
    }
}
