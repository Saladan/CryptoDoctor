/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cryptodoctor.graphic;

import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.ImageIcon;

/**
 *
 * @author david
 */
public class GraphicLoader {

    private static final Logger LOG = getLogger(GraphicLoader.class.getName());

    /**
     * Creates an Icon based on the given location. The global Location
     * fortextures is /assets/textures/. Here is added the folder and the name
     * of the texture file. The ".png" is automatically added.
     *
     * @param location The Location of texture in form "folder:name"
     * @return the Image at this location
     */
    public static ImageIcon createIcon(String location) {
        String[] loc = location.split(":");
        location = "assets/textures";
        for (String s : loc) {
            location += "/" + s;
        }
        location += ".png";
        return new ImageIcon(GraphicLoader.class.getClassLoader().getResource(location));
    }

    private GraphicLoader() {
    }

}
