package me.bristermitten.tribal.data.player;

/**
 * A TribalPlayers implementation that only stores the data in memory. It shouldn't be directly instantiated
 */
public class MemoryTribalPlayers extends TribalPlayers {

    @Override
    public void save() {
        //do nothing if only storing in memory
    }
}
