package navgame.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

//Classe onde é feita toda a logica da fase do jogo
public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Inimigo1> inimigo1;
    private List<Stars> stars;
    private boolean emJogo;

    public Fase(){
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("images\\background.png");
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();

        inicializaInimigos();
        inicializaStars();
        emJogo = true;
    }

    public void inicializaInimigos(){
        int cordenadas[] = new int [40];
        inimigo1 = new ArrayList<Inimigo1>();

        for (int i = 0; i < cordenadas.length; i++) {
            int x = (int) (Math.random() * 8000 + 1000);
            int y = (int) (Math.random() * 600 + 20);
            inimigo1.add(new Inimigo1(x, y));
        }
    }

    public void inicializaStars(){
        int cordenadas[] = new int [300];
        stars = new ArrayList<Stars>();

        for (int i = 0; i < cordenadas.length; i++) {
            int x = (int) (Math.random() * 1124 + 0);
            int y = (int) (Math.random() * 700 + 0);
            stars.add(new Stars(x, y));
        }
    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        if(emJogo == true){
            graficos.drawImage(fundo, 0, 0, null);

            for(int p = 0; p < stars.size(); p++){
                Stars q = stars.get(p);
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            List<Tiro> tiros = player.getTiros();
            for(int i = 0; i < tiros.size(); i++){
                Tiro m = tiros.get(i);
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int o = 0; o < inimigo1.size(); o++){
                Inimigo1 in = inimigo1.get(o);
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
        }else{
            ImageIcon fimJogo = new ImageIcon("images\\gameover.png");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        player.update();

        if(player.isTurbo()){
            timer.setDelay(1);
        }

        if(player.isTurbo() == false){
            timer.setDelay(5);
        }

        for(int p = 0; p < stars.size(); p++){
            Stars on = stars.get(p);
            if(on.isVisivel()){
                on.update();
            }else{
                stars.remove(p);
            }
        }

        List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++){
            Tiro m = tiros.get(i);
            if(m.isVisivel()){
                m.update();
                if(player.isTurbo()){
                    tiros.get(i).setVELOCIDADE(-1);
                }

                if(player.isTurbo() == false){
                    tiros.get(i).setVELOCIDADE(2);
                }

            } else {
                tiros.remove(i);
            }
        }

        for (int o = 0; o < inimigo1.size(); o++){
            Inimigo1 in = inimigo1.get(o);
            if(in.isVisivel()){
                in.update();
            }else {
                inimigo1.remove(o);
            }
        }

        checarColisoes();
        repaint();
    }

    public void checarColisoes(){
        Rectangle formaNave = player.getBounds();
        Rectangle formaInimigo1;
        Rectangle formaTiro;

        for(int i = 0; i < inimigo1.size(); i++){
            Inimigo1 tempInimigo1 = inimigo1.get(i);
            formaInimigo1 = tempInimigo1.getBounds();
            if(formaNave.intersects(formaInimigo1)){
                if(player.isTurbo()){
                    tempInimigo1.setVisivel(false);
                }else{
                    player.setVisivel(false);
                    tempInimigo1.setVisivel(false);
                    emJogo = false;
                }
            }
        }

        List<Tiro> tiros = player.getTiros();
        for (int j = 0; j < tiros.size(); j++){
            Tiro tempTiro = tiros.get(j);
            formaTiro = tempTiro.getBounds();
            for (int o = 0; o < inimigo1.size(); o++){
                Inimigo1 tempInimigo1 = inimigo1.get(j);
                formaInimigo1 = tempInimigo1.getBounds();
                if(formaTiro.intersects(formaInimigo1)){
                    tempInimigo1.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
            }
        }
    }

    private class TecladoAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            player.keyRelease(e);
        }
    }

}
