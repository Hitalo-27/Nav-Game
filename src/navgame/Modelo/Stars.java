package navgame.Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

//Classe onde é feita toda a configuração das estrelas
public class Stars {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    //private static final int LARGURA = 938;
    private static int VELOCIDADE = 2;

    public Stars(int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load(){
        ImageIcon referencia = new ImageIcon("images\\star.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update(){
        if(this.x < 0){
            this.x = largura;
            Random a = new Random();
            int m = a.nextInt(500);
            this.x = m + 1124;
            Random r = new Random();
            int n = r.nextInt(768);
        }else{
            this.x -= VELOCIDADE;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public Image getImagem() {
        return imagem;
    }
}
