package com.mygdx.fun2dolabsmario.Sprites.TileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.fun2dolabsmario.Hud;
import com.mygdx.fun2dolabsmario.MyGdxGame;
import com.mygdx.fun2dolabsmario.PlayScreen;
import com.mygdx.fun2dolabsmario.Sprites.Mario;

public abstract class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(mario.isBig()) {
            setCategoryFilter(MyGdxGame.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            MyGdxGame.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        MyGdxGame.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }

}
