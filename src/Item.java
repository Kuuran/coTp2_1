public abstract class Item {

    private boolean estPlace;
    private double masse;
    private int id;
    private Position p;
    private int niveau;

    private static int lastId = 0;

    protected Item(double masse, Position p) {
        this.masse = masse;
        this.p = p;
        id = ++lastId;
        niveau = 0;
        estPlace = false;
    }

    public void liste() {
        System.out.println(this.toString());
    }

    public int niveau() { return niveau; }

    protected void setNiveau(int niveau){ this.niveau=niveau;}

    public void upNiveau(){
        niveau+=1;
    }

    public void downNiveau(){
        niveau-=1;
    }

    protected void deplace(double dx, double dy) throws ErreurRobot{
        p.move(dx, dy);
    }

    public double masse() { return masse; }

    protected void setMasse(double m){
        masse = m;
    }

    public double masseTotale() {
        return this.masse();
    }

    public int id() { return id; }

    /* retourne une copie de la position, pour qu'on ne puisse pas modifier la
     * position d'un objet par effet de bord sur la valeur retournee.
     */
    public Position position() {
	return new Position(p.x(), p.y());
    }

    public boolean estPlace(){
        return estPlace;
    }
    public void setPlace(){
        estPlace = true;
    }
    public void clearPlace(){
        estPlace = false;
    }
}
