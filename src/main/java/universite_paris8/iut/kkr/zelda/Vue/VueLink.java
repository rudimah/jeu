package universite_paris8.iut.kkr.zelda.Vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import universite_paris8.iut.kkr.zelda.modele.ActeurEnMouvement;
import universite_paris8.iut.kkr.zelda.modele.Environnement;
import universite_paris8.iut.kkr.zelda.modele.Link;
import universite_paris8.iut.kkr.zelda.utils.Constantes;

public class VueLink {

    boolean pied_droite = true;

    Environnement environnement;
    Link link;
    private Pane panneauDeJeu;
    private ImageView imageView;

    public VueLink(Environnement env, Link link,  Pane panneauDeJeu){
        environnement = env;
        this.link = link;
        this.panneauDeJeu = panneauDeJeu;
        this.imageView = new ImageView();
        Image image1 = new Image("file:src/main/resources/image/Link/tileset.png");

        imageView.setImage(image1);
        imageView.setViewport(new Rectangle2D(20, 13, 120, 160));
        imageView.setFitWidth(20);
        imageView.setFitHeight(30);
        imageView.setTranslateX(link.getX());
        imageView.setTranslateY(link.getY());
        panneauDeJeu.getChildren().add(imageView);
    }

    public void mettreAJourImageView(int direction, int tileId) {
        System.out.println("tileid" + tileId);
        imageView.setTranslateX(link.getX());
        imageView.setTranslateY(link.getY());
        int position_image_x = 20, position_image_y = 13, position_image_eau = 0;

        switch (direction) {
            case Constantes.Haut:
                position_image_y = 1060;
                position_image_eau = 340;
                break;
            case Constantes.Bas:
                position_image_y = 710;
                position_image_eau = 825;
                break;
            case Constantes.Droite:
                position_image_y = 1245;
                position_image_eau = 340;
                break;
            case Constantes.Gauche:
                position_image_y = 902;
                position_image_eau = 980;
                break;


        }

        if (tileId == 0 || tileId == 3) {
            imageView.setViewport(new Rectangle2D(position_image_eau, position_image_y, 120, 160));
        } else {
            if (pied_droite) {
                position_image_x = switch (direction) {
                    case Constantes.Haut -> 170;
                    case Constantes.Bas -> 665;
                    case Constantes.Droite -> 180;
                    case Constantes.Gauche -> 180;
                    default -> position_image_x;
                };
                pied_droite = false;
            } else {
                position_image_x = switch (direction) {
                    case Constantes.Haut -> 1470;
                    case Constantes.Bas -> 1470;
                    case Constantes.Droite -> 1315;
                    case Constantes.Gauche -> 1320;
                    default -> position_image_x;
                };
                pied_droite = true;
            }
            imageView.setViewport(new Rectangle2D(position_image_x, position_image_y, 120, 160));
        }
    }
}