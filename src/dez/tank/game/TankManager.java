package dez.tank.game;

import dez.tank.game.entity.EntityType;
import dez.tank.game.entity.EntityTank;

import java.util.ArrayList;
import java.util.List;

public class TankManager {

    protected List<EntityTank> entities;
    protected EntityType       entityType;

    public TankManager(EntityType entityType) {
        this.entities = new ArrayList<>();
        this.entityType = entityType;
    }

    public void add(EntityTank entity) {
        this.entities.add(entity);
    }

    public List<EntityTank> all() {
        return this.entities;
    }

    public void update() {
        for(EntityTank entityTank : this.entities) {
            entityTank.update();
        }
    }

}
