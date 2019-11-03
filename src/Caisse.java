public class Caisse extends Item {

    public Caisse(double masse) {
	this(masse, Position.positionDefaut());
    }

    public Caisse (double masse, Position p) {
	super(masse, p);
    }

    @Override
    public String toString() { return "Caisse " + this.id()
                                        +"\n    Masse "+this.masse()
                                        +"\n    Position " + this.position().toString()
                                        +"\n    Niveau " + this.niveau(); }
}
