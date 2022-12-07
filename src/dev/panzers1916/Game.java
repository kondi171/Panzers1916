package dev.panzers1916;

import dev.panzers1916.bullet.PionerBullet;
import dev.panzers1916.bullet.SpenserBullet;
import dev.panzers1916.display.Display;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;
import dev.panzers1916.input.KeyManager;
import dev.panzers1916.sounds.SoundLoader;
import dev.panzers1916.states.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.File;

/** Represents a main Game class
 * @author Konrad Nowak */

public class Game implements Runnable {
	/** storing a display for frame */
	private Display display;
	/** storing a width and height for frame */
	private int width, height;
	/** storing a title for frame */
	public String title;
	/** declaring a flags for switching statements in options and game loop */
	private boolean running, music, isActiveMusic, isActiveSounds;
	public static boolean sounds;
	/** main game thread */
	private Thread thread;
	/** storing a information about states */
	private State gameState,menuState, creditsState;
	/** storing a information about optionStates */
	private OptionState optionState;
	/** storing a keymanager instance */
	private KeyManager keyManager;
	/** storing a handler instance */
	private Handler handler;
	/** variables for storing sounds */
	private SoundLoader menuSound,gameSound, changeOption, switchState;

	/**	Constructor set the width, height and title for frame
	 * <br> instance of Handler and KeyManager
	 * <br> set the starting values to flags
 	 * @param title set title frame
	 * @param width set width frame
	 * @param height set height frame */
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		handler = new Handler(this);
		keyManager = new KeyManager(this);
		running = false;
		sounds = true;
		music = true;
		isActiveMusic = false;
		isActiveSounds = false;
	}

	/** method initialize a necessary elements of game like:
	 * <ol>
	 *     <li>states</li>
	 *     <li>frame</li>
	 *     <li>assets</li>
	 *     <li>sounds</li>
	 * </ol> */
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		optionState = new OptionState(handler);
		creditsState = new CreditsState(handler);

		menuSound = new SoundLoader(new File("res/sounds/music/menuMusicTheme.wav"),true);
		gameSound = new SoundLoader(new File("res/sounds/music/gameMusicTheme.wav"),true);
		changeOption = new SoundLoader(new File("res/sounds/runnable/changeOption.wav"),false);
		switchState = new SoundLoader(new File("res/sounds/runnable/switchState.wav"),false);
		menuSound.play(0f);
	}
	/** refreshing states */
	private void tick() {
		keyManager.tick();
		if(State.getState() != gameState && sounds){
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
				changeOption.play(0f);
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
				switchState.play(0f);
		}

		if(menuState.getStateOption() == 0) State.setState(menuState);
		else if(menuState.getStateOption() == 1) {
			State.setState(gameState);
			State gameOver;
			if(PionerBullet.winner == 1){
				gameOver = new GameOverState(handler);
				State.setState(gameOver);
				if(sounds) gameSound.stop();
				if(gameOver.getStateOption() == 1) display.getFrame().dispatchEvent(new WindowEvent(display.getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if (SpenserBullet.winner == 2){
				gameOver = new GameOverState(handler);
				State.setState(gameOver);
				if(sounds) gameSound.stop();
			}
			menuSound.stop();
			if(music) gameSound.play(0f);
			music = false;
		} else if(menuState.getStateOption() == 2) {
			State.setState(optionState);
			if(optionState.getStateOption() == 4) {
				menuState.setStateOption((byte) 0);
				optionState.setStateOption((byte) 0);
			}
		} else if(menuState.getStateOption() == 3) {
			State.setState(creditsState);
			if(creditsState.getStateOption() == 1){
				menuState.setStateOption((byte) 0);
				creditsState.setStateOption((byte) 0);
			}
		} else if(menuState.getStateOption() == 4) display.getFrame().dispatchEvent(new WindowEvent(display.getFrame(), WindowEvent.WINDOW_CLOSING));
		if(State.getState() != null) State.getState().tick();
	}

	/** rendering a main content of game */
	private void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		if(State.getState() == menuState) {
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			Text.drawString(g,"Start",width/2,height/2 - 100,true,Color.darkGray,Assets.secondFont56);
			Text.drawString(g,"Options",width/2,height/2 ,true,Color.darkGray,Assets.secondFont56);
			Text.drawString(g,"Credits",width/2,height/2 + 100,true,Color.darkGray,Assets.secondFont56);
			Text.drawString(g,"Exit",width/2,height/2 + 200,true,Color.darkGray,Assets.secondFont56);
		} else if (State.getState() == gameState) {
			g.clearRect(0, 0, width, height);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.fillRect(0, 0, 33, height);
			g.fillRect(width - 33, 0, width, height);
			g.fillRect(0, 0, width, 33);
			g.fillRect(0, height - 85, width, height);
		} else if (State.getState() == optionState) {
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			if(!optionState.isFullscreen()) {
				Text.drawString(g, "FullScreen: OFF", width / 2, height / 2 - 100, true, Color.darkGray, Assets.secondFont56);
				display.setFullscreen(false);
			} else {
				Text.drawString(g,"FullScreen: ON",width/2,height/2 - 100,true,Color.darkGray, Assets.secondFont56);
				display.setFullscreen(true);
			}
			if(!optionState.isMusic()){
				Text.drawString(g,"Music: OFF",width/2,height/2 ,true,Color.darkGray,Assets.secondFont56);
				if(isActiveMusic){
					menuSound.stop();
					music = false;
					isActiveMusic = false;
				}
			} else {
				Text.drawString(g,"Music: ON",width/2,height/2 ,true,Color.darkGray,Assets.secondFont56);
				if(isActiveMusic){
					menuSound.play(0f);
					music = true;
					isActiveMusic = false;
				}
			}
			if(!optionState.isSounds()) {
				Text.drawString(g,"Sounds: OFF",width/2,height/2 + 100,true,Color.darkGray,Assets.secondFont56);
				if(isActiveSounds){
					sounds = false;
					isActiveSounds = false;
				}
			} else {
				Text.drawString(g,"Sounds: ON",width/2,height/2 + 100,true,Color.darkGray,Assets.secondFont56);
				if(isActiveSounds){
					sounds = true;
					isActiveSounds = false;
				}
			}
			Text.drawString(g,"Back",width/2,height/2 + 200,true,Color.darkGray,Assets.secondFont56);
		} else if(State.getState() == creditsState){
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
		}
		if (State.getState() != null)
			State.getState().render(g);
		bs.show();
		g.dispose();
	}
	/** main game loop with timer in 60 FPS */
	public void run() {
		init();
		final double timePerTick = 16666666;
		double delta = 0;
		long lastTime = System.nanoTime();
		long timer = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			if(timer >= 1000000000) {
				timer = 0;
			}
		}
		stop();
	}
	/** start game */
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/** stop game */
	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/** getter for <b>width</b>
	 * @return width */
	public int getWidth() {
		return width;
	}
	/** setter for <b>width</b>
	 * @param width set the width */
	public void setWidth(int width) {
		this.width = width;
	}
	/** getter for <b>height</b>
	 * @return height */
	public int getHeight() {
		return height;
	}
	/** setter for <b>height</b>
	 * @param height set the height */
	public void setHeight(int height) {
		this.height = height;
	}
	/** getter for <b>gameState</b>
	 * @return gameState */
	public State getGameState() {
		return gameState;
	}
	/** getter for <b>menuState</b>
	 * @return menuState */
	public State getMenuState() { return menuState; }
	/** getter for <b>keyManager</b>
	 * @return keyManager */
	public KeyManager getKeyManager() {
		return keyManager;
	}
	/** setter for <b>isActiveMusic</b>
	 * @param isActiveMusic set isActiveMusic */
	public void setIsActiveMusic(boolean isActiveMusic){
		this.isActiveMusic = isActiveMusic;
	}
	/** setter for <b>isActiveSounds</b>
	 * @param isActiveSounds set isActiveSounds */
	public void setIsActiveSounds(boolean isActiveSounds){
		this.isActiveSounds = isActiveSounds;
	}
}