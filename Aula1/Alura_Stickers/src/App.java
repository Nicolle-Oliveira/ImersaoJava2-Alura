import java.net.URI;
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
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        
        URI adress = URI.create(url);
        /*Nota -  Java reconhece que 'var' é do tipo 'HttpClient' */
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> resposta = client.send(request, BodyHandlers.ofString());
        String body = resposta.body();

        /* Para mostrar conteudo do body */
        //System.out.println(body);

        /* Extrair so os dados que interessam (nome, poster, classificação) */

        JsonParser JP = new JsonParser();
        List<Map<String, String>> list_filmes = JP.parse(body);

        /* Para conferir a quantidade de intens na lista e seu primeiro item */
        //System.out.println(list_filmes.size());
        //System.out.println(list_filmes.get(0));

        /* Exibir e manipular os dados */

        System.out.println("\u001b[37m \u001b[44m \n Confira os títulos! \u001b[m \n");
        for (Map<String,String> filme : list_filmes) {
            
            System.out.println("\u001b[1m Título - \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1m Poster - \u001b[m" + filme.get("image"));
            System.out.print("\u001b[1m Classificação - \u001b[m");

            double rating = Double.parseDouble(filme.get("imDbRating"));
            
            Math.round(rating);

            for(int i = 0; i < rating; i++){
                System.out.print("\u001b[44m*\u001b[m");
            }

            System.out.println("_" + filme.get("imDbRating"));
            System.out.println(" \n ");
        }
    }
}
