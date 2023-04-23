package navgame.Modelo;

import javax.swing.*;
import java.awt.*;

//Classe onde é feita toda a configuração dos inimigos
public class Explosao {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    private static int VELOCIDADE = 2;

    public Explosao(int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = false;
    }

    public void load(){
        ImageIcon referencia = new ImageIcon("images\\explosao.gif");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }


    public Explosao changeSpeed(int num) {
        setVELOCIDADE(num);
        return null;
    }

    public void update(){
        this.x -= VELOCIDADE;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public static void setVELOCIDADE(int VELOCIDADE) {
        Explosao.VELOCIDADE = VELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }
}
