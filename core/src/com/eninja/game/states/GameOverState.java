package com.eninja.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eninja.game.FlappyDemo;

public class GameOverState extends State {

    private Texture background;
    private Texture gameOver;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background, 0, 0);
        batch.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
        System.out.println("GameOverState disposed.");
    }
}
