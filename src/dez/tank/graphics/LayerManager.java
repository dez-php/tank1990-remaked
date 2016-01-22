package dez.tank.graphics;

import java.util.*;
import java.util.List;

public class LayerManager {

    protected Map<LayerManager.LayerType, List<Drawable>> layers;

    public enum LayerType {
        LEVEL_TOP(1),
        PLAYER(2),
        ENEMY(3),
        BULLET(4),
        LEVEL_BOTTOM(5);

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
        if (!layers.containsKey(layerType)) {
            layers.put(layerType, new LinkedList<Drawable>());
        }

        return layers.get(layerType);
    }

    public void addDrawable(LayerType layerType, Drawable drawable) {
        this.getLayers(layerType).add(drawable);
    }

}
