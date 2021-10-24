package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.ImageLoader;
import dev.panzers1916.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameState extends State{

    private World world;
    private BufferedImage logoInGame;
    private byte stateOption;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public byte getStateOption(){
        return stateOption;
    }
    @Override
    public void setStateOption(byte stateOption) { this.stateOption = stateOption; }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        logoInGame = ImageLoader.loadImage("/img/logo_in_game_handler.png");
        logoInGame.getSubimage(0,0,50,50);
        g.drawImage(logoInGame,633,685,null);
    }

}
