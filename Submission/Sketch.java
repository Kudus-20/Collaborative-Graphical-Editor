import java.awt.*;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeMap;

public class Sketch {
    protected TreeMap<Integer, Shape> idsToShape;

    public Sketch(){
        idsToShape=new TreeMap<>();
    }

    public synchronized Shape getById(Integer id){
        if (idsToShape.containsKey(id)){
            return idsToShape.get(id);
        }
        return null;
    }

    public synchronized Shape addById(int id,Shape shape){
        idsToShape.put(id,shape);
        return shape;
    }

    public synchronized Shape deleteById(int id){
        return idsToShape.remove(id);
    }

    public synchronized Shape moveById(int id,int dx, int dy){
        if (idsToShape.containsKey(id)){
            Shape shape=idsToShape.get(id);
            shape.moveBy(dx,dy);
            return shape;
        }
        return null;
    }

    public synchronized Shape recolorById(int id, Color color){
        if (idsToShape.containsKey(id)){
            Shape shape=idsToShape.get(id);
            shape.setColor(color);
            return shape;
        }
        return null;
    }

    public NavigableSet<Integer> getBottomToTopIds(){
        return idsToShape.navigableKeySet();
    }

    public Integer getTopIdAtPoint(int x,int y){
        for (int id:idsToShape.descendingKeySet()){
            if (idsToShape.get(id).contains(x,y)){
                return id;
            }
        }
        return null;
    }

    public synchronized Integer getNextId(){
        NavigableSet<Integer> descendingKeySet= idsToShape.descendingKeySet();
        if (descendingKeySet.isEmpty()){
            return 1;
        }
        return descendingKeySet.first()+1;
    }

}
