package kran;

import java.awt.geom.Point2D;

public class Block {

	double hoehe;
	double breite;
	int streben;
	
	Point2D.Double punkt1;
	Point2D.Double punkt2;
	
	boolean istVertikal;
	
	public Block(double hoehe, double breite, int streben, Point2D.Double punkt1, Point2D.Double punkt2) throws Exception {
		this.hoehe = hoehe;
		this.breite = breite;
		this.streben = streben;
		this.punkt1 = punkt1;
		this.punkt2 = punkt2;
		
		try {
			if(punkt1.x == punkt2.x) {
				istVertikal = true;
			}
			else if(punkt1.y == punkt2.y) {
				istVertikal = false;
			}
			else {
				throw new Exception("Die Eingabe der Punkte sind falsch! \nKorrigiere deine Eingabe!");
			} 
		}
		catch(Exception e) {
			throw e; 
		}
		
	}
	
	public void erzeugePunkte() {
		Point2D.Double neuerPunkt1;
		Point2D.Double neuerPunkt2;
		
		if(istVertikal == true) {
			neuerPunkt1 = new Point2D.Double(punkt1.x, punkt1.y + hoehe);
			neuerPunkt2 = new Point2D.Double(punkt2.x, punkt2.y + hoehe);
		}
		else{
			neuerPunkt1 = new Point2D.Double(punkt1.x + breite, punkt1.y);
			neuerPunkt2 = new Point2D.Double(punkt2.x + breite, punkt2.y);
		}
	}
	
	
	
}
