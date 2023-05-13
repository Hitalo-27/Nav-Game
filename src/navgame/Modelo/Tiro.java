package navgame.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;


//Classe onde é feita toda a configuração dos tiros
public class Tiro {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    private static final int LARGURA = 1124;
    private static int VELOCIDADE = 8;
    

    public Tiro(int x, int y){
    	this.x = x;
        this.y = y;
        isVisivel = true;
        
        // Chama a função que emite o som
        ShootSound();
            
    }
    
    void ShootSound() {
    	try {
    		//Instãncia o audio
    		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\shoot.wav").getAbsoluteFile());
    		Clip clip = AudioSystem.getClip();
    		clip.open(audioInputStream);
    		//Dá play no som
    		clip.start();
    		clip.loop(0);
    	} catch(Exception ex) {
    		System.out.println("Erro ao executar o arquvio de som!");
    		ex.printStackTrace();
    	}
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
