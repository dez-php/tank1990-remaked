package dez.game.engine.helpers;

public class Time {

    public static final long ONE_NANO_SECOND    = 1000000000l;

    public static long nano() {
        return System.nanoTime();
    }

}
