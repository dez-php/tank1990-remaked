package dez.tank.game;

import dez.tank.game.entity.*;
import dez.tank.graphics.Atlas;
import dez.tank.graphics.Drawable;
import dez.tank.graphics.GameWindow;
import dez.tank.graphics.LayerManager;
import dez.tank.helpers.ResourceImageLoader;

import java.io.IOException;
import java.util.List;

public class GameEngine extends Engine {

    private Atlas  atlas;
    private Player player;

    public static TankManager   tanksManager;
    public static BulletManager bulletManager;

    public GameEngine() throws IOException {
        super();

        tanksManager = new TankManager(EntityType.ENEMY);
        bulletManager = new BulletManager(EntityType.BULLET);

        layerManager.getAllLayers().clear();
        this.atlas = new Atlas(new ResourceImageLoader("resource/data/atlas.png").loadImage());
        this.player = new Player(150, 150, this.atlas, this.input);

        tanksManager.add(new Enemy(TankType.SLOWPOKE, 0, 0, this.atlas));

        tanksManager.add(new Enemy(TankType.TERMINATOR, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.SLOWPOKE, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.WEAK, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.AGGRESSOR, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.AGGRESSOR, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.DEFENDER, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.DESTROYER, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.FLASH, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.PREDATOR, 0, 0, this.atlas));
        tanksManager.add(new Enemy(TankType.TERMINATOR, 0, 0, this.atlas));

        layerManager.addDrawable(LayerManager.LayerType.PLAYER, this.player);

        for (EntityTank enemy : tanksManager.all()) {
            layerManager.addDrawable(LayerManager.LayerType.ENEMY, enemy);
        }
    }

    @Override
    protected void update() {

        List<EntityTank> entityTanks = tanksManager.all();
        List<EntityBullet> entityBullets = bulletManager.all();

        for (int i = 0; i < entityBullets.size(); i++) {
            if(entityBullets.get(i).isDead()) {
                entityBullets.remove(i);
            }
        }

        for (int i = 0; i < entityTanks.size(); i++) {
            if(entityTanks.get(i).isDead()) {
                entityTanks.remove(i);
            }
        }

        for (EntityTank enemy : tanksManager.all()) {
            if (this.player.rectangle().intersects(enemy.rectangle())) {
                player.collision(enemy);
                enemy.collision(player);
            }
        }

        this.player.update();
        tanksManager.update();
        bulletManager.update();

    }

    @Override
    protected void render() {
        for (LayerManager.LayerType layerType : LayerManager.LayerType.values()) {
            for (Drawable entity : layerManager.getLayers(layerType)) {
                entity.draw(graphics2D);
            }
        }
    }

}
