public class TesteRobot {

    public static void main(String [] args) throws ErreurRobot {
	Piece r = new Piece(), rr = new Piece();
	Sac s1, s2, s3, s4;
	Caisse c1, c2, c3;
	Robot r1, r2, r3;

	s1 = new Sac(100, 0.02, new Position (10.0, 10.0)); r.placer(s1);
	s2 = new Sac(400, 0.01, new Position (20.0, 20.0)); r.placer(s2);
	s3 = new Sac(10, 0.01); r.placer(s3);
	s4 = new Sac(10, 0.0); r.placer(s4);

	c1 = new Caisse(20, new Position (20.0, 0.0)); r.placer(c1);
	c2 = new Caisse(200, new Position (30.0, 0.0)); r.placer(c2);
	c3 = new Caisse(200); r.placer(c3);

	/* les robots ont un bras manipulateur qui leur permet
	 * de charger les objets quelle que soit la position ou
	 * ils sont par rapport a l'objet a charger
	 */
	r1 = new Robot(50, 250, new Position(5.0, 5.0)); r.placer(r1);
	r2 = new Robot(10, 1000, new Position(15.0, 15.0)); r.placer(r2);
	/* celui-la on fait expres de ne pas le placer */
	r3 = new Robot(10, 1000, new Position(15.0, 15.0));

	System.out.println("Positions initiales");
	r.listeTous();

	System.out.println("\nChargement sur r1");
	r1.charger(s1);
	r1.charger(c1);
	r1.charger(s3);

	try {
	    r3.charger(s3);
            assert(false);
	} catch(ErreurRobot e) {
		System.out.println(e.getMessage());
	}
	try {
	    rr.placer(c1);
            assert false;
	} catch(ErreurRobot e) {
		System.out.println(e.getMessage());
	}

	try {
	    r1.charger(s2); // chargement trop lourd.
	    assert(false);
	} catch(ErreurRobot e) {
		System.out.println(e.getMessage());
	}

	try {
	    r2.charger(s3);	// s3 n'est plus au sol.
	    assert false;
	} catch(ErreurRobot e) {
		System.out.println(e.getMessage());
	}
	System.out.println("\nPositions apres chargement sur r1");
	r.listeTous();

	System.out.println("\nDeplacement de r1");
	r1.deplacer(4.0, 3.0);
	System.out.println("\nPositions apres deplacement de r1");
	r.listeTous();

	System.out.println("\nChargement sur r2");
	r2.charger(c2);
	r2.charger(s2);
	r2.charger(r1);
	System.out.println("\nPositions apres chargement sur r2");
	r.listeTous();

	System.out.println("\nDeplacement de r1");
	r2.deplacer(20.0, 20.0);
	try {
	    r1.decharger(s3);
            assert false;
	} catch(ErreurRobot e) {}
	System.out.println("\nPositions apres deplacement de r2");
	r.listeTous();

	System.out.println("\nDechargement a partir de r2");
	r2.decharger(c2);
	r2.decharger(r1);
	System.out.println("\nPositions apres dechargement de r2");
	r.listeTous();

	r2.deplacer(20.0, 20.0);
	r2.charger(r1);
	r2.decharger(r1);

	System.out.println("\nDechargement a partir de r1");
	r1.decharger(c1);
	r1.decharger(s1);
	r1.charger(s4);

	s1.liste();
	r1.charger(s1);
	s1.liste();
	r1.decharger(s1);
	s1.liste();

	System.out.println("\nPositions finales");
	r.listeTous();
    }
}
