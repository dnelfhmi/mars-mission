/**
 * Interface for entities that can be attacked/damaged.
 *
 * Provides methods to:
 *
 * - Get the current health
 *
 * - Decrease the health by a given amount
 *   @param amount The amount to decrease health by
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */


package util;

public interface Attackable {
    int getHealth();
    void decreaseHealth(int amount);

}
