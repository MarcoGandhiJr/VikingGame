package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.Game;
import xavierdufour.engine.RenderingEngine;
import xavierdufour.engine.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;
    private Tree tree;
    private int soundCooldown;

    public VikingGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(200, 200);
        world = new World();
        tree = new Tree(300, 350);

    }

    @Override
    public void update() {
        player.update();
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
        soundCooldown--;
        if (soundCooldown < 0) {
            soundCooldown = 0;
        }
        if (gamePad.isFirePressed() && soundCooldown == 0) {
            soundCooldown = 40;
            Sound.play("sounds/best.wav");
        }
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        if (player.getY() < tree.getY() + 52) {
            player.draw(buffer);
            tree.draw(buffer);
        } else {
            tree.draw(buffer);
            player.draw(buffer);
        }
    }



    @Override
    public void conclude() {

    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().hideCursor();
        RenderingEngine.getInstance().getScreen().fullScreen();

        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("musics/map.wav")
            );
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
