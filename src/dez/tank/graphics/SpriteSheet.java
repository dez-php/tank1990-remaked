package dez.tank.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    protected BufferedImage sheet;
    protected int spriteSize;
    protected int spriteCount;
    protected int spritesInWidth;

    public SpriteSheet(BufferedImage sheet, int spriteSize) {
        this.sheet              = sheet;
        this.spriteSize         = spriteSize;
        this.spritesInWidth     = sheet.getWidth() / spriteSize;
        this.spriteCount        = this.spritesInWidth * (sheet.getHeight() / spriteSize);
    }

    public BufferedImage getSprite(int index) {
        index   = index % this.spriteCount;

        int x   = index % this.spritesInWidth * this.spriteSize;
        int y   = index / this.spritesInWidth * this.spriteSize;

        return this.sheet.getSubimage(x, y, this.spriteSize, this.spriteSize);
    }

    public int size() {
        return this.spriteSize;
    }

}
