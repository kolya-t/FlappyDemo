package com.eninja.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eninja.game.FlappyDemo;
import com.eninja.game.sprites.Bird;
import com.eninja.game.sprites.Tube;

import java.util.Iterator;

public class PlayState extends State {

    public static final int TUBE_SPACING = 125; // расстояние между появляющимися трубами
    public static final int TUBE_COUNT = 4; // Количество труб в комплекте
    public static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;
    public  Texture ground;
    private Vector2 groundPos1, groundPos2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(camera.position.x - camera.viewportWidth / 2 + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    /** Обработка нажатий */
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    /** Обновляет картинку через определенное время */
    @Override
    public void update(float delta) {
        handleInput();
        updateGround();
        bird.update(delta);
        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - camera.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosBotTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }
        camera.update();
    }

    /** Рисует картинку */
    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, camera.position.x - camera.viewportWidth / 2, 0);
        for (Tube tube : tubes) {
            batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            batch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        batch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        batch.draw(ground, groundPos1.x, groundPos1.y);
        batch.draw(ground, groundPos2.x, groundPos2.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PlayState disposed.");
    }

    public void updateGround() {
        if (camera.position.x - camera.viewportWidth / 2 > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (camera.position.x - camera.viewportWidth / 2 > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
