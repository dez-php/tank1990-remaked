package dez.tank.game.entity;

public enum TankType {

    SLOWPOKE(1, 5, 2, 5, 1, 100, new int[]{0}),
    WEAK(2, 5, 1, 10, 1, 100, new int[]{1}),
    AGGRESSOR(2, 5, 4, 30, .75f, 100, new int[]{2}),
    DEFENDER(1, 5, 5, 50, .75f, 100, new int[]{3}),
    DESTROYER(2, 6, 10, 100, .75f, 100, new int[]{4}),
    FLASH(4, 8, 2, 50, 2, 100, new int[]{5}),
    PREDATOR(3, 7, 10, 70, .75f, 100, new int[]{6}),
    TERMINATOR(3, 7, 15, 200, .5f, 100, new int[]{7});

    public int   speed;
    public int   damage;
    public int   armor;
    public int   health;
    public int   bulletSpeed;
    public float recharge;

    public int[] spriteCoordinates;

    TankType(int speed, int bulletSpeed, int damage, int armor, float recharge, int health, int[] spriteCoordinates) {
        this.speed = speed;
        this.damage = damage;
        this.armor = armor;
        this.health = health;
        this.bulletSpeed = bulletSpeed;
        this.recharge = recharge;

        this.spriteCoordinates = spriteCoordinates;
    }
}
