package dez.tank.game.entity;

import dez.tank.graphics.Atlas;
import dez.tank.helpers.Time;

abstract public class DynamicEntity extends Entity {

    public DynamicEntity(EntityType entityType, int x, int y, Atlas atlas) {
        super(entityType, x, y, atlas);
    }



}
