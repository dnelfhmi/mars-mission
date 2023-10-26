/**
 * Interface for entities that can collide with other entities.
 *
 * Provides methods to:
 *
 * - Handle collision logic when colliding with another entity
 *   @param other The entity that was collided with
 *   @param habitat The MarsHabitat environment
 *   @param meter The HabitabilityMeter
 *
 * - Check if there is a collision at a given x,y position
 *   @param x The x position to check
 *   @param y The y position to check
 *   @param habitat The MarsHabitat environment
 *   @param meter The HabitabilityMeter
 *
 * - Get and set whether this recently collided with something
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */


package util;

import entities.Entity;

public interface Collidable {
    void onCollide(Entity other, MarsHabitat habitat,HabitabilityMeter meter);
    boolean isCollisionAtPosition(int x, int y, MarsHabitat habitat, HabitabilityMeter meter);
    public boolean isRecentlyCollided();
    public void setRecentlyCollided(boolean recentlyCollided);
}
