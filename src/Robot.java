import java.util.ArrayList;

public class Robot extends Item {

    private double chargeMax;
    private ArrayList<Item> rCharge;

    public Robot(double masse, double max) {
	this(masse, max, Position.positionDefaut());
    }

    public Robot(double masse, double max, Position p) {
	    super(masse, p);
	    chargeMax = max;
	    rCharge = new ArrayList<>();
    }

    public double chargeMax() { return chargeMax; }

    public double charge() {
        double c = 0;
        for(Item i: rCharge){
            c += i.masseTotale();
        }
        return c;
    }

    @Override
    public double masseTotale() {
	    return this.masse() + this.charge();
    }


    public void deplacer(double dx, double dy) throws ErreurRobot {//todo pas possible de deplacer un robot chargé si celui qui le porte se déplace
        if(!(this.niveau() == 0)){
            throw new ErreurRobot("Ce robot ne peut pas etre deplace.");
        }
        else{
            super.deplace(dx, dy);
            for(Item i: rCharge){
                if (i instanceof Robot){
                    Robot r = (Robot) i;
                    r.deplace(dx, dy);
                }else {
                    i.deplace(dx, dy);
                }
            }
        }
    }

    @Override
    protected void deplace(double dx, double dy) throws ErreurRobot{
        super.deplace(dx, dy);
        for(Item i: rCharge){
            i.deplace(dx, dy);
        }
    }

    @Override
    public void upNiveau(){
        super.upNiveau();

        for(Item i : rCharge){
            i.upNiveau();
        }
    }

    @Override
    public void downNiveau(){
        super.downNiveau();

        for(Item i : rCharge){
            i.downNiveau();
        }
    }

    public void charger(Item i) throws ErreurRobot {
        if(!(niveau() == 0)){
            throw new ErreurRobot("Robot::charger : Chargement impossible, le robot n'est pas au sol.");
        }
        else if(!(i.niveau() == 0)){
            System.out.println(i.niveau());
            throw new ErreurRobot("Robot::charger : Chargement impossible, l'item n'est pas au sol.");
        }
        else if(!((i.masseTotale() + charge()) <= chargeMax)){
            System.out.println(charge() + i.masseTotale());
            throw new ErreurRobot("Robot::charger : Chargement impossible, charge trop importante.");
        }
        else{
            rCharge.add(i);
            i.upNiveau();
            i.deplace(position().x()-i.position().x(), position().y()-i.position().y());
        }
    }

    public void decharger(Item i) throws ErreurRobot {

        if(!(niveau() == 0)){
            throw new ErreurRobot("Robot::decharger : Dechargement impossible, le robot n'est pas au sol");
        }
        else if(!(rCharge.contains(i))){
            throw new ErreurRobot("Robot::decharger : Dechargement impossible, l'item n'est pas charge sur ce robot");
        }
        else{
            rCharge.remove(i);
            i.downNiveau();
        }
    }

    @Override
    public String toString() {
        StringBuilder charge = new StringBuilder();

        if(!rCharge.isEmpty())
        {
            charge.append("\n---------------------");
            for(Item i : rCharge){
                charge.append("\n").append(i.toString());
            }
            charge.append("\n=====================");
        }

        return "Robot "+ this.id()
            +"\n    Masse "+this.masse()
                +"\n    Charge max "+this.chargeMax
            +"\n    Masse totale " + this.masseTotale()
            +"\n    Position " + this.position().toString()
            +"\n    Niveau " + this.niveau()
                +"\n    Objets transportés " + rCharge.size() + charge; }
}
