package dez.game.engine.graphics;

public enum LayerType {

    LEVEL(1), PLAYER(2), ENEMY(3), BULLET(4);

    private int layerPosition;

    LayerType(int layerPosition) {
        this.layerPosition = layerPosition;
    }

    public int position() {
        return layerPosition;
    }

}
