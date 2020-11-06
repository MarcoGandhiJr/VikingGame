package xavierdufour;

import xavierdufour.engine.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "images/demo.png";
    private Image background;
    private ArrayList<Blockade> worldBorders;

    public World() {
        loadBackground();
        worldBorders = new ArrayList<>();
        Blockade topBorder = new Blockade();
        topBorder.setDimension(400, 48);
        topBorder.teleport(0, 0);
        Blockade topBorder2 = new Blockade();
        topBorder2.setDimension(400, 48);
        topBorder2.teleport(464, 0);
        worldBorders.add(topBorder);
        worldBorders.add(topBorder2);
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, -64);
        for (Blockade blockade : worldBorders) {
            blockade.draw(buffer);
        }
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
