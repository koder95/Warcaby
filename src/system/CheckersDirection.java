/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author Kamil
 */
public final class CheckersDirection {
    
    private volatile int hashCode = 0;
    private final int direction;
    private final String name;
    private final int x, y;
    
    private CheckersDirection(int direction, String name, int x, int y){
        this.direction = direction;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public CheckersDirection getNext() {
        try {
            return CHECKERS_DIRECTIONS[direction+1];
        } catch (ArrayIndexOutOfBoundsException ex){
            return null;
        }
    }

    public CheckersDirection getPrevious() {
        try {
            return CHECKERS_DIRECTIONS[direction-1];
        } catch (ArrayIndexOutOfBoundsException ex){
            return null;
        }
    }

    public int getTranslateY() {
        return y;
    }

    public int getTranslateX() {
        return x;
    }

    @Override
    public String toString() {
        return getClass().getName() + "(direction=" + direction + ", name=" + name + ", transX=" + x + ", transY=" + y + ")";
    }

    @Override
    public int hashCode() {
        if(hashCode == 0){
            int resault = 17;
            resault = 37 * resault + x;
            resault = 37 * resault + y;
            hashCode = resault;
        }
        return hashCode;
    }
    
    public static final CheckersDirection[] CHECKERS_DIRECTIONS = {
        new CheckersDirection(0, "lewy górny", -1, 1),
        new CheckersDirection(1, "prawy górny", 1, 1),
        new CheckersDirection(2, "lewy dolny", -1, -1),
        new CheckersDirection(3, "prawy dolny", 1, -1)
    };
    
    public static CheckersDirection getFirst(){
        return CHECKERS_DIRECTIONS[0];
    }
    
    public static CheckersDirection getLast(){
        return CHECKERS_DIRECTIONS[CHECKERS_DIRECTIONS.length-1];
    }
    
    public static final CheckersDirection LEFT_TOP = CHECKERS_DIRECTIONS[0];
    public static final CheckersDirection RIGHT_TOP = CHECKERS_DIRECTIONS[1];
    public static final CheckersDirection LEFT_BOTTOM = CHECKERS_DIRECTIONS[2];
    public static final CheckersDirection RIGHT_BOTTOM = CHECKERS_DIRECTIONS[3];
}
