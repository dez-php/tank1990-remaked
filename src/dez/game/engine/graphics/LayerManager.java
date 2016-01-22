package dez.game.engine.graphics;

import java.util.*;
import java.util.List;

public class LayerManager {

    protected Map<LayerManager.LayerType, List<Drawable>> layers;

    public enum LayerType {
        LEVEL_TOP(1),
        LEVEL_BOTTOM(2),
        PLAYER(4),
        ENEMY(8),
        BULLET(16);

        private int layerPosition;

        LayerType(int layerPosition) {
            this.layerPosition = layerPosition;
        }

        public int position() {
            return layerPosition;
        }
    }

    public LayerManager() {
        layers = new HashMap<>();
    }

    public Map<LayerType, List<Drawable>> getAllLayers() {
        return layers;
    }

    public List<Drawable> getLayers(LayerManager.LayerType layerType) {
        if(! layers.containsKey(layerType)) {
            layers.put(layerType, new LinkedList<Drawable>());
        }

        return layers.get(layerType);
    }

}
