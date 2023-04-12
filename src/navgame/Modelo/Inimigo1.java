package navgame.Modelo;

import javax.swing.*;
import java.awt.*;

//Classe onde é feita toda a configuração dos inimigos
public class Inimigo1 {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    private static int VELOCIDADE = 2;

    public Inimigo1(int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load(){
        ImageIcon referencia = new ImageIcon("images\\inimigo.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }
    
   public Inimigo1 changeSpeed(int num) {
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
        Inimigo1.VELOCIDADE = VELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }
}
