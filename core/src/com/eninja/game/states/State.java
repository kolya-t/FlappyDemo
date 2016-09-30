package com.eninja.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


/** Определяет состояния игры (игра, пауза) */
public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    /** Опрашивает, были ли нажаты определенные клавиши или тап по экрану */
    protected abstract void handleInput();

    /** Обновляет картинку через определенное время */
    public abstract void update(float delta);

    /** Метод рисует картинку */
    public abstract void render(SpriteBatch batch);

    /** Деструктор */
    public abstract void dispose();

}
