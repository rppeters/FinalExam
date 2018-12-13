package pkgException;

//custom exception for inputs into retirement through UI using an eAttributeViolation to track which attribute is bad
public class OutOfRangeException extends Exception{

	private pkgEnum.EAttributeViolation eAttribute;
	
	public OutOfRangeException(pkgEnum.EAttributeViolation eAttribute) {
		this.eAttribute = eAttribute;
	}
	
	public pkgEnum.EAttributeViolation getEAttribute() {
		return this.eAttribute;
	}
	
}
