import java.util.ArrayList;

public class Piece {

    private ArrayList<Item> items;

    public Piece() {
        items = new ArrayList<>();
    }

    public void listeTous() {
        for (Item i : items) {
            i.liste();
        }
    }

    public void placer(Item i) throws ErreurRobot {
        if(i.estPlace()){
            throw new ErreurRobot("Piece::placer : Placement impossible l'objet est deja place");
        }
        else {
            items.add(i);
            i.setPlace();
        }
    }

    public void enlever(Item i) throws ErreurRobot {
        if (!(items.contains(i))) {
            throw new ErreurRobot("Piece::enlever : Action impossible, l'item n'est pas dans la piece.");
        } else {
            items.remove(i);
            i.clearPlace();
        }
    }
}
