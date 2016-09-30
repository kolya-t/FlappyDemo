package com.eninja.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    /** Помещет элемент в вершину стека */
    public void push(State state) {
        states.push(state);
    }

    /** Извлекает верхний элемент, удаляя его из стека */
    public void pop() {
        states.pop().dispose();
    }

    /** Удаляет верхний элемент стека и помещает вместо него новый */
    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    /** Через малый промежуток времени обновляет верхнее (текущее) состояние */
    public void update(float delta) {
        states.peek().update(delta);
    }

    /** Отрисовывает текущее состояние */
    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }
}
