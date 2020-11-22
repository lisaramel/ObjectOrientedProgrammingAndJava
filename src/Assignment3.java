import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-22
 * Project: femtonSpel
 * Copyright: MIT
 */
class FemtonSpel extends JFrame {

    //test test

    private JPanel panel = new JPanel();
    private List<JButton> buttonsOrdered = new ArrayList<>();
    private List<JButton> buttonsGame = new ArrayList<>();

    private JButton b1 = new JButton("1");
    private JButton b2 = new JButton("2");
    private JButton b3 = new JButton("3");
    private JButton b4 = new JButton("4");
    private JButton b5 = new JButton("5");
    private JButton b6 = new JButton("6");
    private JButton b7 = new JButton("7");
    private JButton b8 = new JButton("8");
    private JButton b9 = new JButton("9");
    private JButton b10 = new JButton("10");
    private JButton b11 = new JButton("11");
    private JButton b12 = new JButton("12");
    private JButton b13 = new JButton("13");
    private JButton b14 = new JButton("14");
    private JButton b15 = new JButton("15");
    private JButton bNull = new JButton(" ");
    private JButton newGame = new JButton("Spela igen!");

    // testcomment

    public FemtonSpel(){
        buttonsGame.add(b1); buttonsGame.add(b2); buttonsGame.add(b3); buttonsGame.add(b4); buttonsGame.add(b5);
        buttonsGame.add(b6); buttonsGame.add(b7); buttonsGame.add(b8); buttonsGame.add(b9); buttonsGame.add(b10);
        buttonsGame.add(b11); buttonsGame.add(b12); buttonsGame.add(b13); buttonsGame.add(b14); buttonsGame.add(b15);
        buttonsGame.add(bNull);

        add(newGame, BorderLayout.SOUTH);

        add(panel);

        panel.setLayout(new GridLayout(4,4));

        shuffleButtons();

        addButtonListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



    }

    public void shuffleButtons(){
        Collections.shuffle(buttonsGame);
        panel.removeAll();
        for (JButton button : buttonsGame) {
            panel.add(button);
        }
        panel.revalidate();
        panel.repaint();
    }

    public void addButtonListeners(){
        ActionListener l = new MoveButtonListener();
        ActionListener l2 = new NewGameListener();

        for(JButton b : buttonsGame){
            b.addActionListener(l);
        }

        newGame.addActionListener(l2);

    }

    class MoveButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int indexCounter = 0;
            int index = -1;
            for(JButton button: buttonsGame){
                if(actionEvent.getSource() == button){
                    index = indexCounter;
                }
                indexCounter ++;
            }
            indexCounter = 0;

            System.out.println("index " + index);

            //här kolla om knappen på index är bredvid tomma platsen
            // anropa nån checkNeighbours-metod?
            /* int indexForNull = index - besideEmpty(index);
            System.out.println("index knapp: " + index);
            System.out.println("index null: " + indexForNull); */


            if(besideEmpty(index)!= 0){
                int indexForNull = index + besideEmpty(index);
                System.out.println("index knapp: " + index);
                System.out.println("index null: " + indexForNull);

                switchPlace(index, indexForNull);
            }

            System.out.println("besideEmpty: " + besideEmpty(index));

        }
    }

    class NewGameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){

            if (actionEvent.getSource() == newGame){
                shuffleButtons();
            }
        }
    }

    public void switchPlace(int index, int indexForNull){
        buttonsGame.get(indexForNull).setText(buttonsGame.get(index).getText());
        buttonsGame.get(index).setText(" ");

        panel.revalidate();
        panel.repaint();
    }

    public int besideEmpty(int index){

        if(index%4 != 0){
            // vi kollar index-1
            if(buttonsGame.get(index-1).getText().equals(" ")){
                return -1;
            }
        }

        if (index%4 != 3){
            // vi kollar index+1
            if(buttonsGame.get(index+1).getText().equals(" ")){
                return +1;
            }
        }

        if(index > 3){
            // vi kollar index-4
            if(buttonsGame.get(index-4).getText().equals(" ")){
                return -4;
            }
        }

        if(index < 12){
            // vi kollar index+4
            if(buttonsGame.get(index+4).getText().equals(" ")){
                return +4;
            }
        }

        return 0;
    }



    public static void main(String[] args) {
        FemtonSpel fs = new FemtonSpel();
    }

}

