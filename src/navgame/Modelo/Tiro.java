package navgame.Modelo;

import javax.swing.*;
import java.awt.*;

public class Tiro {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    private static final int LARGURA = 938;
    private static int VELOCIDADE = 5;

    public Tiro(int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load(){
        ImageIcon referencia = new ImageIcon("images\\tiro.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update(){
        this.x += VELOCIDADE;
        if(this.x > LARGURA){
            isVisivel = false;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
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

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public static void setVELOCIDADE(int VELOCIDADE) {
        Tiro.VELOCIDADE = VELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }
}
