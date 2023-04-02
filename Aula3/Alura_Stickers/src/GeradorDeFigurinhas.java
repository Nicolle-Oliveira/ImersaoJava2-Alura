import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas{

    /* Nota - lançar exception não é boa prática */
    public void CriaStikers(InputStream inputStream, String nomeArquivo, String frase) throws Exception{

        /* Leitura da imagem */
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg")); 
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        /* Cria nova imagem em memória com transparência e com tamanho novo */
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        /* Altura anterior + 200 pixels */
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        /* Copiar a imagem original pra nova imagem (em memória) */
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        /* Configurar a fonte */
        //Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        Font fonte = new Font("Comic Sans MS", Font.BOLD, 64);
        graphics.setColor(Color.BLUE);
        graphics.setFont(fonte);

        /* Escrever uma frase centralizada na nova imagem */
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(frase, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int textoX = (largura - larguraTexto)/2;

        graphics.drawString(frase, textoX, novaAltura - 100);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout =  new TextLayout(frase, fonte, fontRenderContext);
        
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(textoX, novaAltura - 100);
        graphics.setTransform(transform);

        BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        /* Escrever a imagem nova em um arquivo */
        /* Ao testar o código, coloque na variável "caminho" o caminho completo até sua pasta saida */
        String caminho = " ... \\Imersao_Java-Alura\\Aula3\\Alura_Stickers\\saida";
        if(!new File(caminho).exists()){
            new File(caminho).mkdirs();
        }

        ImageIO.write(novaImagem, "png", new File(caminho + "\\" + nomeArquivo));
        
    }

    /*public static void main(String[] args) throws Exception {
        GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
        geradora.CriaStikers();
    }*/

}