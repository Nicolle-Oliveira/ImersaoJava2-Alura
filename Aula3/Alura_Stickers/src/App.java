import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
    
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2021-06-10&end_date=2021-06-12";
        ExtraiConteudo extrator = new ExtraiConteudoNASA();

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtraiConteudo extrator = new ExtraiConteudoIMDB();

        ClienteHTTP http =  new ClienteHTTP();
        String json = http.buscaDados(url);  

        /* Exibir e manipular os dados */
        List<Conteudo> list_conteudos = extrator.extraiCont(json);

        GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) {
            
            Conteudo conteudo = list_conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";
            geradora.CriaStikers(inputStream, nomeArquivo, "KSKSKS"+i);

            System.out.println(conteudo.getTitulo() + "\n\n");

        }
    }
}
