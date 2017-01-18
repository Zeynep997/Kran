package kran;

import inf.minife.fe.Constraint;
import inf.minife.fe.DOF;
import inf.minife.fe.Force;
import inf.minife.fe.Model;
import inf.minife.fe.RectangleS;
import inf.minife.fe.Truss2D;
import inf.minife.view2.Viewer2;

public class Kran {

	private Model model;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model m = new Kran().getModel();
		
		Block blockFuss; 
		Block blockArm;
		
		int anzahlBlockFuss;
		int anzahlBlockArm;
		double xOffsetRL;
		double yOffsetRL;
		int aufhaengungSeil;
		int aufhaengungGewicht;
		
		

		m.printStructure();
		m.solve();
		m.printResults();

		Viewer2 viewer = new Viewer2(m);
		viewer.setVisible(true);
				
	}
	
	public Kran(Block blockFuss, Block blockArm, int anzahlBlockFuss, 
			int anzahlBlockArm, double xOffsetRL, double yOffsetRL, 
			int aufhaengungSeil, int aufhaengungGewicht) {
		// model
		model = new Model();

		double E = 210000; // N/mm^2 (modulus of elasticity)
		double rho = 7.88E-6; // kg/mm^2 (density of steel)
		model.createMaterial(1, E, rho);

		for (int i = 1; i < anzahlBlockFuss; i++) { // <= anzahlBlockFuss
			RectangleS r;
			r = model.createSection(i, RectangleS.TYPE, Truss2D.TYPE);
			r.setTKY(10); // mm
			r.setTKZ(10); // mm

			// Realtable r = model.createRealtable(i, Truss2D.TYPE);
			// r.setValue(TrussElement.RT_A, A);
	}

		// nodes
		double b = 1000.0; // mm
		double h = 1000.0; // mm
		model.createNode(1, -b, 0, 0);  // Zentraler Punkt Anker
		model.createNode(2, b, 0, 0);
		model.createNode(3, 0, -h, 0);

		// forces
		Force f = new Force();
		f.setValue(DOF.T_X, 30000.0); // N
		f.setValue(DOF.T_Y, -20000.0); // N
		model.getNode(4).setForce(f);

		// constraints
		Constraint c = new Constraint();
		c.setFree(DOF.T_X, false);
		c.setFree(DOF.T_Y, false);
		model.getNode(1).setConstraint(c);
		model.getNode(2).setConstraint(c);
		model.getNode(3).setConstraint(c);

		// elements
		model.createElement(1, Truss2D.TYPE, model.getMaterial(1),
			model.getRealtable(1), model.getNode(1), model.getNode(4));
		model.createElement(3, Truss2D.TYPE, model.getMaterial(1),
				model.getRealtable(3), model.getNode(3), model.getNode(4));
	
	}
	
	public Model getModel() {
		return model;
	}
	
	

}
