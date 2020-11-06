package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.entity.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private Image image;
    private Blockade blockade;

    public Tree(int x, int y) {
        teleport(x, y);
        load();
        blockade = new Blockade();
        blockade.setDimension(30, 16);
        blockade.teleport(x + 16, y + 64);
        blockadeFromTop();
    }

    public void blockadeFromTop() {
        blockade.teleport(x + 16, y + 64);
    }

    public void blockadeFromBottom() {
        blockade.teleport(x + 16, y + 48);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
        blockade.draw(buffer);
    }

    private void load() {
        try {
            image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
