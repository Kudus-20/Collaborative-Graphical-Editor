import java.awt.*;
import java.util.ArrayList;

public class Message {

    //todo change this back to private
    public String message;

    public Message(String message){
        this.message = message;
    }

    public synchronized boolean editLocalSketch(Sketch sketch){
        String[] messageSplit= message.split(" ");
        
        if (messageSplit[0].equals("draw")){
            int id= Integer.parseInt(messageSplit[1]);
            
            if (messageSplit[2].equals("ellipse")){
                sketch.addById(id, new Ellipse(Integer.parseInt(messageSplit[3]),Integer.parseInt(messageSplit[4]),Integer.parseInt(messageSplit[5]),Integer.parseInt(messageSplit[6]),new Color(Integer.parseInt(messageSplit[7]))));
            } else if (messageSplit[2].equals("rectangle")) {
                sketch.addById(id,new Rectangle(Integer.parseInt(messageSplit[3]),Integer.parseInt(messageSplit[4]),Integer.parseInt(messageSplit[5]),Integer.parseInt(messageSplit[6]),new Color(Integer.parseInt(messageSplit[7]))));
            } else if (messageSplit[2].equals("polyline")) {
                ArrayList<Point> points= new ArrayList<>();
                for (int i=3; i< messageSplit.length-1 ; i+=2){
                    points.add(new Point(Integer.parseInt(messageSplit[i]),Integer.parseInt(messageSplit[i+1])));
                }
                Color color= new Color(Integer.parseInt(messageSplit[messageSplit.length-1]));
                sketch.addById(id, new Polyline(points,color));
            } else if (messageSplit[2].equals("segment")) {
                sketch.addById(id, new Segment(Integer.parseInt(messageSplit[3]),Integer.parseInt(messageSplit[4]),Integer.parseInt(messageSplit[5]),Integer.parseInt(messageSplit[6]),new Color(Integer.parseInt(messageSplit[7]))));
            }
            else {
                return false;
            }
        } else if (messageSplit[0].equals("recolor")) {
            sketch.recolorById(Integer.parseInt(messageSplit[1]),new Color(Integer.parseInt(messageSplit[2])));
        } else if (messageSplit[0].equals("move")) {
            sketch.moveById(Integer.parseInt(messageSplit[1]),Integer.parseInt(messageSplit[2]),Integer.parseInt(messageSplit[3]));
        } else if (messageSplit[0].equals("delete")) {
            sketch.deleteById(Integer.parseInt(messageSplit[1]));
        }else {
            return false;
        }

        return true;
    }

    public synchronized String editMasterSketch(Sketch sketch){
        String[] messageSplit= message.split(" ");

        if (messageSplit[0].equals("draw")){
            int id= sketch.getNextId();

            if (messageSplit[2].equals("ellipse")){
                sketch.addById(id, new Ellipse(Integer.parseInt(messageSplit[3]),Integer.parseInt(messageSplit[4]),
                        Integer.parseInt(messageSplit[5]),Integer.parseInt(messageSplit[6]),new Color(Integer.parseInt(messageSplit[7]))));

            } else if (messageSplit[2].equals("rectangle")) {
                sketch.addById(id,new Rectangle(Integer.parseInt(messageSplit[3]),Integer.parseInt(messageSplit[4]),
                        Integer.parseInt(messageSplit[5]),Integer.parseInt(messageSplit[6]),new Color(Integer.parseInt(messageSplit[7]))));
            }else if (messageSplit[2].equals("polyline")) {
                ArrayList<Point> points= new ArrayList<>();
                for (int i = 3; i < messageSplit.length-1 ; i += 2){
                    points.add(new Point(Integer.parseInt(messageSplit[i]),Integer.parseInt(messageSplit[i+1])));
                }
                Color color= new Color(Integer.parseInt(messageSplit[messageSplit.length-1]));
                sketch.addById(id, new Polyline(points,color));
            } else if (messageSplit[2].equals("segment")) {
                sketch.addById(id, new Segment(Integer.parseInt(messageSplit[3]),Integer.parseInt(messageSplit[4]),
                        Integer.parseInt(messageSplit[5]),Integer.parseInt(messageSplit[6]),new Color(Integer.parseInt(messageSplit[7]))));
            }
            else {
                return "";
            }

            String newMessage = messageSplit[0] + " " + id;
            for (int i=2; i< messageSplit.length; i++){
                newMessage += " "+ messageSplit[i];
            }

            return newMessage;

        } else if (messageSplit[0].equals("recolor")) {
            sketch.recolorById(Integer.parseInt(messageSplit[1]),new Color(Integer.parseInt(messageSplit[2])));
        } else if (messageSplit[0].equals("move")) {
            sketch.moveById(Integer.parseInt(messageSplit[1]),Integer.parseInt(messageSplit[2]),Integer.parseInt(messageSplit[3]));

        } else if (messageSplit[0].equals("delete")) {
            sketch.deleteById(Integer.parseInt(messageSplit[1]));
        }else {
            return "";
        }
        return message;

    }

}
