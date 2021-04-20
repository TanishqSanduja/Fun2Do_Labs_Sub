package com.mygdx.fun2dolabsmario.Sprites.TileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.fun2dolabsmario.Hud;
import com.mygdx.fun2dolabsmario.MyGdxGame;
import com.mygdx.fun2dolabsmario.PlayScreen;
import com.mygdx.fun2dolabsmario.Sprites.Items.ItemDef;
import com.mygdx.fun2dolabsmario.Sprites.Items.Mushroom;
import com.mygdx.fun2dolabsmario.Sprites.Mario;

public abstract class Coin extends InteractiveTileObject {
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(PlayScreen screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.COIN_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(getCell().getTile().getId() == BLANK_COIN)
            MyGdxGame.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else {
            if(object.getProperties().containsKey("mushroom")) {
                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MyGdxGame.PPM),
                        Mushroom.class));
                MyGdxGame.manager.get("audio/sounds/powerup_spawn.wav", Sound.class).play();
            }
            else
                MyGdxGame.manager.get("audio/sounds/coin.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(BLANK_COIN));
            Hud.addScore(100);
        }
    }
}
