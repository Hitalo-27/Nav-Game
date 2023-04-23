package navgame.Modelo;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

//Classe onde é feita toda a configuração da nave/jogador
public class Player implements ActionListener {
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura, largura;
    private List <Tiro> tiros;
    private boolean isVisivel;
    private boolean isTurbo;
    private boolean activeTurbo;
    private Timer timer, timerDelayTurbo;
    

    public Player(){
        this.x = 100;
        this.y = 100;
        isVisivel = true;
        isTurbo = false;
        activeTurbo = true;

        tiros = new ArrayList<Tiro>();

        timer = new Timer(5000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(isTurbo == true){
            turbo();
            isTurbo = false;
            Inimigo1 boostSpeed = new Inimigo1(0, 0).changeSpeed(2);
            Explosao boostSpeed2 = new Explosao(0, 0).changeSpeed(2);
        }

        if(isTurbo == false){
            //Função que carrega a imagem original (sem turbo)
            load();
            
        }

    }

    public void load(){
        ImageIcon refencia = new ImageIcon("images\\nave.png");
        imagem = refencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update(){
        int vx = x+dx;
        int vy = y+dy;

        if(vx >= 0 && vx <= 1010){
            x += dx;
        }

        if(vy >= 0 && vy <= 541){
            y += dy;
        }
    }

    public void tiroSimples(){
        this.tiros.add(new Tiro(x+largura, y + (altura/2)));
    }


    public void turbo(){

        if(activeTurbo) {
            isTurbo = true;
            ImageIcon refencia = new ImageIcon("images\\naveturbo.png");
            imagem = refencia.getImage();
            activeTurbo = false;
            
            Inimigo1 boostSpeed = new Inimigo1(0, 0).changeSpeed(12);
            Explosao boostSpeed2 = new Explosao(0, 0).changeSpeed(12);
            

            //Impede que o turbo seja usado durante 30 segundos apos o uso
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    activeTurbo = true;
                }
            };
            timerDelayTurbo = new Timer(30000 ,taskPerformer);
            timerDelayTurbo.start();
        }

    }

	public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }

    //Configurando as teclas que serão usadas na fase
    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_SPACE){
            turbo();  
        }

        if(codigo == KeyEvent.VK_A){
            if(isTurbo == false){
                tiroSimples();
            }
        }
        
        if(codigo == KeyEvent.VK_R){
        	
        	
        }

        if(codigo == KeyEvent.VK_UP){
            dy=-3;
        }

        if(codigo == KeyEvent.VK_DOWN){
            dy=3;
        }

        if(codigo == KeyEvent.VK_LEFT){
            dx=-3;
        }

        if(codigo == KeyEvent.VK_RIGHT){
            dx=3;
        }
    }


    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP){
            dy=0;
        }

        if(codigo == KeyEvent.VK_DOWN){
            dy=0;
        }

        if(codigo == KeyEvent.VK_LEFT){
            dx=0;
        }

        if(codigo == KeyEvent.VK_RIGHT){
            dx=0;
        }
        
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImagem(){
        return imagem;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public boolean getVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public boolean isTurbo() {
        return isTurbo;
    }
}


