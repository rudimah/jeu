package universite_paris8.iut.kkr.zelda.modele;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Link extends ActeurEnMouvement{


    private Pane panneauDeJeu;
    private TilePane tilePane;
    private ImageView imageView;
    boolean pied_droite = true;

    public Link(Environnement env, Pane pj, TilePane tilePane) {
        super(50, 50, 5, env, 40, 20);
        this.panneauDeJeu = pj;
        this.tilePane = tilePane;
        this.imageView = new ImageView();
        panneauDeJeu.setFocusTraversable(true);
        panneauDeJeu.setOnKeyPressed(this::gererTouch);
        panneauDeJeu.setOnKeyReleased(this::handleKeyRelease);
        Image image1 = new Image("file:src/main/resources/image/Link/tileset.png");


        imageView.setImage(image1);
            imageView.setViewport(new Rectangle2D( 180,1250,120,160));
        imageView.setFitWidth(20);
        imageView.setFitHeight(30);

        imageView.setTranslateX(getX());
        imageView.setTranslateY(getY());
        panneauDeJeu.getChildren().add(imageView);
    }

    private void gererTouch(KeyEvent event) {
        if (event.getCode() == KeyCode.SHIFT) {
            setVitesse(getVitesse()*2);
        }
        System.out.println("Touche pressé " + event.getCode());
        seDeplacer(event.getCode());
        event.consume();
    }
    private void handleKeyRelease(KeyEvent event) {
        // Reset the direction when shift is released
        if (event.getCode() == KeyCode.SHIFT) {
            setVitesse(getVitesse()/2);
            System.out.println("MAJ relâchée, vitesse normalisée.");
        }
    }

    public void seDeplacer(KeyCode key) {

        int newX = getX(), newY = getY();

        double x = 0, y = 0;

        switch (key) {
            case Z:
                newY = getY()-getVitesse();
                y = 1050;
                if (pied_droite){
                    x = 170;
                    pied_droite= false;
                }
                else {
                    x = 1470;
                    pied_droite = true;
                }

                break;
            case S:
                newY = getY()+getVitesse();
                y = 710;
                if (pied_droite){
                    x = 665;
                    pied_droite= false;
                }
                else {
                    x = 1470;
                    pied_droite = true;
                }


                break;
            case D:
                newX = (getX()+getVitesse());
                y = 1225;
                if (pied_droite){
                    x = 180;
                    pied_droite= false;
                }
                else {
                    x = 1315;
                    pied_droite = true;
                }
                break;
            case Q:
                newX = (getX()-getVitesse());
                y = 895;
                if (pied_droite){
                    x = 180;
                    pied_droite= false;
                }
                else {
                    x = 1315;
                    pied_droite = true;
                }
                break;
            default:
                ;
        }
        System.out.println("Essaye de passer de " + getX() + ", " + getY() );

        if (env.estPositionValide(newX, newY)) {

            setX(newX);
            setY(newY);
            imageView.setViewport(new Rectangle2D(x, y, 120, 160));
            imageView.setTranslateX(getX());
            imageView.setTranslateY(getY());


            System.out.println("s'est déplacé en (" + getX() + ", " + getY() + ")");
        } else {
            System.out.println("Mouvement bloqué par un élément de l'environnement.");
        }

//        System.out.println("Essaye de passer de " + getX() + ", " + getY() + " à " + newX + ", " + newY);
//
//        boolean positionValide = getEnv().estPositionValide(newX, newY);
//
//        // Gestion des effets des tuiles spécifiques
//        int tileId = getEnv().getTileId(newX, newY);
//        if (tileId == 10) { // Lave
//            decrementerPv(5);
//            System.out.println("Link perd 5 PV sur la lave. PV restants: " + getPointsDeVie());
//        }
//
//        if (positionValide) {
//            panneauDeJeu.getChildren().clear();
//            setX(newX);
//            setY(newY);
//            imageView.setTranslateX(getX());
//            imageView.setTranslateY(getY());
//            panneauDeJeu.getChildren().add(imageView);
//
//            System.out.println("s'est déplacé en (" + getX() + ", " + getY() + ")");
//        } else {
//            System.out.println("Mouvement bloqué par un élément de l'environnement.");
//        }
//    }



}
}
