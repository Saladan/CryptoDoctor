package de.graphicloader;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.ImageIcon;

/**
 *
 * @author Saladan
 */
public class GraphicLoader {

    private static final Logger LOG = getLogger(GraphicLoader.class.getName());
    private static final Map<String, ImageIcon> ICONS = new HashMap<>();

    /**
     * Returns the texture at the given location as {@link ImageIcon}. If the
     * icon was already loaded, this icon is returned, without creating a new
     * one. The global location for textures is "/assets/textures/". Here is
     * added the folder and the name of the texture file. The ".png" is
     * automatically added.
     *
     * @param location the location of texture in form "folder:name"
     * @return the {@link ImageIcon} at this location
     * @see ImageIcon
     * @see createIcon(String, boolean)
     */
    public static ImageIcon createIcon(String location) {
        return createIcon(location, false);
    }

    /**
     * Returns the texture at the given location as {@link ImageIcon}. If
     * {@code forced == false} and if the icon was already loaded, this icon is
     * returned, without creating a new one, if {@code forced == true}, the
     * routine creates a new icon, no matter if it already exists. If the icon
     * was not already loaded {@code forced == true} then two icons are created.
     * The global location for textures is "/assets/textures/". Here is added
     * the folder and the name of the texture file. The ".png" is automatically
     * added.
     *
     * @param location the location of texture in form "folder:name"
     * @param force force the creation of an {@link ImageIcon}
     * @return the {@link ImageIcon} at this location
     * @see ImageIcon
     * @see createIcon(String)
     */
    public static ImageIcon createIcon(String location, boolean force) {
        if (!ICONS.containsKey(location) || force) {
            String[] loc = location.split(":");
            String path = "assets/textures";
            for (String s : loc) {
                path += "/" + s;
            }
            if (path.endsWith(".png")) {
                path += ".png";
            }
            if (!ICONS.containsKey(location)) {
                ICONS.put(location, new ImageIcon(GraphicLoader.class.getClassLoader().getResource(path)));
            }
            if (force) {
                return new ImageIcon(GraphicLoader.class.getClassLoader().getResource(path));
            }
        }
        return ICONS.get(location);
    }

    private GraphicLoader() {
    }

}
