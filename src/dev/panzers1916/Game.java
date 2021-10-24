package dev.panzers1916;

import dev.panzers1916.bullet.PionerBullet;
import dev.panzers1916.bullet.SpenserBullet;
import dev.panzers1916.display.Display;
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

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;

	private State gameState;
	private State menuState;
	private OptionState optionState;
	private State gameOver;
	private State creditsState;

	private KeyManager keyManager;
	private Handler handler;
	private SoundLoader menuSound,gameSound, changeOption, switchState;
	private boolean sounds = true, music = true;
	private boolean isActiveMusic = false, isActiveSounds = false;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		handler = new Handler(this);
		keyManager = new KeyManager(this);
	}

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
		menuSound.play();
	}

	private void tick() {
		keyManager.tick();

		if(State.getState() != gameState && sounds == true){
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
				changeOption.play();
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
				switchState.play();
		}

		if(menuState.getStateOption() == 0){
			State.setState(menuState);
		}
		else if(menuState.getStateOption() == 1) {
			State.setState(gameState);
			if(PionerBullet.winner == 1){
				gameOver = new GameOverState(handler,true);
				State.setState(gameOver);
				gameSound.stop();
				if(gameOver.getStateOption() == 1){
					display.getFrame().dispatchEvent(new WindowEvent(display.getFrame(), WindowEvent.WINDOW_CLOSING));
				}
			} else if (SpenserBullet.winner == 2){
				gameOver = new GameOverState(handler,false);
				State.setState(gameOver);
				gameSound.stop();
			}
			menuSound.stop();
			if(music) gameSound.play();
			music = false;
		} else if(menuState.getStateOption() == 2) {
			State.setState(optionState);
			if (optionState.getStateOption() == 4) {
				menuState.setStateOption((byte)0);
				optionState.setStateOption((byte) 0);
			}
		} else if(menuState.getStateOption() == 3) {
			State.setState(creditsState);
			if(creditsState.getStateOption() == 1){
				menuState.setStateOption((byte) 0);
				creditsState.setStateOption((byte) 0);
			}
		} else if(menuState.getStateOption() == 4) {
			display.getFrame().dispatchEvent(new WindowEvent(display.getFrame(), WindowEvent.WINDOW_CLOSING));
		}

		if (State.getState() != null) State.getState().tick();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		if (State.getState() == menuState) {
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
					menuSound.play();
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

	public void run() {
		init();
		final int FPS = 60;
		final double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

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

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getMenuState() {
		return menuState;
	}
	public State getGameOverState(){return gameOver;}
	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	public void setIsActiveMusic(boolean isActiveMusic){
		this.isActiveMusic = isActiveMusic;
	}
	public void setIsActiveSounds(boolean isActiveSounds){
		this.isActiveSounds = isActiveSounds;
	}
}