package dez.tank.game;

import dez.tank.game.entity.EntityBullet;
import dez.tank.game.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class BulletManager {

    protected List<EntityBullet> entityBullets;
    protected EntityType         entityType;

    public BulletManager(EntityType entityType) {
        this.entityBullets = new ArrayList<>();
        this.entityType = entityType;
    }

    public void add(EntityBullet entityBullet) {
        this.entityBullets.add(entityBullet);
    }

    public List<EntityBullet> all() {
        return this.entityBullets;
    }

    public void update() {
        for(EntityBullet entityBullet : this.entityBullets) {
            entityBullet.update();
        }
    }

    public List<EntityBullet> getEntityBullets() {
        return entityBullets;
    }
}
