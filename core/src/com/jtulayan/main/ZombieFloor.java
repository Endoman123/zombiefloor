package com.jtulayan.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jtulayan.entity.GameObjects;
import com.jtulayan.main.screen.MenuScreen;

/**
 * @author Jared Tulayan
 */
public class ZombieFloor extends Game {
    private SpriteBatch batch;
    private Viewport viewport;
    private Assets assets;

    public void create() {
        batch = new SpriteBatch(5460);

        viewport = new ScreenViewport();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        assets = new Assets();
        assets.MANAGER.load(Assets.UI.ATLAS);
        assets.MANAGER.load(Assets.UI.SKIN);
        assets.MANAGER.load(Assets.HUD.ATLAS);
        assets.MANAGER.load(Assets.GameObjects.ATLAS);

        GameObjects.initGame(this);

        while (!assets.MANAGER.update()) {
            Gdx.app.log("ASSETS", "Loading: " + (assets.MANAGER.getProgress() * 100));
        }

        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (getScreen() != null)
            getScreen().render(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
    }

    @Override
    public void dispose() {
        super.dispose();
        assets.dispose();
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Assets getAssets() {
        return assets;
    }
}
