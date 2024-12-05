package models.map_elements;

/**
 * Diese Klasse repräsentiert Koordinaten mit zwei Dimensionen (x und y).
 * Sie ermöglicht das Speichern und den Zugriff auf Positionen innerhalb eines
 * zweidimensionalen Raums.
 *
 * @author Warawa Alexander
 */
public class Coordinates {
    private int posX,posY;
    public Coordinates(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
