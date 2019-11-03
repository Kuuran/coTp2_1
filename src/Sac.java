public class Sac extends Item {

    protected double perte; /* taux de perte */

    public Sac(double masse, double perte) {
	this(masse, perte, Position.positionDefaut());
    }

    public Sac(double masse, double perte, Position p) {
	super(masse, p);
	this.perte = perte;
    }
    @Override
    protected void deplace(double dx, double dy) throws ErreurRobot{
        double distance = java.lang.Math.hypot(dx, dy);
        setMasse(masse() - masse() * perte * distance);
        super.deplace(dx, dy);
    }

    @Override
    public String toString() { return "Sac " + this.id()
            +"\n    Masse " + this.masse()
            +"\n    Perte " + this.perte
            +"\n    Position " + this.position().toString()
            +"\n    Niveau " + this.niveau(); }
}
