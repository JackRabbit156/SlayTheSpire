package helper;

/**
 * The type Path assistent, benutzung mit .toPath()
 *
 * @author OF Daniel Willig
 */
public class PathAssistent {
    /**
     * Beispiel:
     * setImagePath(new PathAssistent(this).toPath());
     *
     * Voraussetzung:
     * der Resource Ordner MUSS die gleiche Ordnerstruktur wie der src Ordner haben
     *
     * @param object die Karte, der Gegner, alles was ein Bild im Resources Ordner hat
     * @return einen String welcher den Pfad im Resource Ordner entspricht
     */
    public String toPath(Object object) {
        String path = "/" + object.getClass().getPackage().getName() + "." + object.getClass().getSimpleName();
        path = path.replace(".", "/");
        path = path.replaceFirst("models", "images");
        path += ".png";

        return path;
    }
    public String toAltPath(Object object, int number) {
        String path = "/" + object.getClass().getPackage().getName() + "." + object.getClass().getSimpleName();
        path = path.replace(".", "/");
        path = path.replaceFirst("models", "images");
        path += "Alt" + number + ".png";

        return path;
    }
}
