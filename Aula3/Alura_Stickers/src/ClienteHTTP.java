import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHTTP {
    
    public String buscaDados(String url){

        try{

            URI adress = URI.create(url);
            /*Nota -  Java reconhece que 'var' é do tipo 'HttpClient' */
            var client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
            HttpResponse<String> resposta = client.send(request, BodyHandlers.ofString());
            String body = resposta.body();
            
            return body;

        }catch(IOException | InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
