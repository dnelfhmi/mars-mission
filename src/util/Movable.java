/**
 * Interface for entities that can move on the map.
 *
 * Provides methods to:
 * - Check if a move is valid
 * - Move in each cardinal and ordinal direction
 * - Get and set previous/current x,y position
 * - Get the symbol representing this on the map
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

public interface Movable {
    boolean isMoveValid(int newX, int newY);
    void moveNorth(MarsHabitat habitat, HabitabilityMeter meter);
    void moveSouth(MarsHabitat habitat, HabitabilityMeter meter);
    void moveEast(MarsHabitat habitat, HabitabilityMeter meter);
    void moveWest(MarsHabitat habitat, HabitabilityMeter meter);
    void moveNorthEast(MarsHabitat habitat, HabitabilityMeter meter);
    void moveNorthWest(MarsHabitat habitat, HabitabilityMeter meter);
    void moveSouthEast(MarsHabitat habitat, HabitabilityMeter meter);
    void moveSouthWest(MarsHabitat habitat, HabitabilityMeter meter);
    int getPrevY();
    int getPrevX();
    void setPrevY(int y);
    void setPrevX(int x);
    int getY();
    int getX();
    void setY(int y);
    void setX(int x);
    char getMapSymbol();
}

