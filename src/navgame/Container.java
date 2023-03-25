package navgame;

import navgame.Modelo.Fase;

import javax.swing.*;

public class Container extends JFrame {
    public Container(){
        add(new Fase());
        setTitle("Nav Game");
        setSize(1124, 628);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String []args){
        new Container();
    }
}
