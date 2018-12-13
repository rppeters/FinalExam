package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

import pkgException.OutOfRangeException;


public class Retirement {

	private int iYearsToWork;
	private double dAnnualReturnWorking;
	private int iYearsRetired;
	private double dAnnualReturnRetired;
	private double dRequiredIncome;
	private double dMonthlySSI;
	
	//TODO: Build the constructor, getters and setters for the attributes above.
	public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired,
			double dRequiredIncome, double dMonthlySSI) throws OutOfRangeException{
		
		//years must be positive
		if (iYearsToWork < 0) {
			System.out.println(iYearsToWork);
			try {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.YearsToWork);
			} catch (Exception e) {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.YearsToWork);
			}
		} else {this.iYearsToWork = iYearsToWork;}

		//annual return while working must be between 0 and 20%
		if (dAnnualReturnWorking < 0 || dAnnualReturnWorking > 0.2) {
			System.out.println(dAnnualReturnWorking);
			try {
				System.out.println(dAnnualReturnWorking);
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.AnnualReturnWorking);
			} catch (Exception e) {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.AnnualReturnWorking);
			}
		} else {this.dAnnualReturnWorking = dAnnualReturnWorking;}
		
		//years retired must be positive
		if (iYearsRetired < 0) {
			System.out.println(iYearsRetired);
			try {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.YearsRetired);
			} catch (Exception e) {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.YearsRetired);
			}
		} else {this.iYearsRetired = iYearsRetired;}

		//annual return while retired must be btween 0 and 3%
		if (dAnnualReturnRetired < 0 || dAnnualReturnRetired > 0.03) {
			System.out.println(dAnnualReturnRetired);
			try {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.AnnualReturnRetired);
			} catch (Exception e) {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.AnnualReturnRetired);
			}
		} else {this.dAnnualReturnRetired = dAnnualReturnRetired;}
		
		//can be positive or negative (maybe all you do is donate your money; I don't judge)
		this.dRequiredIncome = dRequiredIncome;
		
		//must be positive (the government gives you money!)
		if (dMonthlySSI < 0) {
			System.out.println(dMonthlySSI);
			try {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.MonthlySSI);
			} catch (Exception e) {
				throw new OutOfRangeException(pkgEnum.EAttributeViolation.MonthlySSI);
			}
		} else {this.dMonthlySSI = dMonthlySSI;}
	}
	
	
	public int getiYearsToWork() {
		return iYearsToWork;
	}




	public void setiYearsToWork(int iYearsToWork) {
		this.iYearsToWork = iYearsToWork;
	}




	public double getdAnnualReturnWorking() {
		return dAnnualReturnWorking;
	}




	public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
		this.dAnnualReturnWorking = dAnnualReturnWorking;
	}




	public int getiYearsRetired() {
		return iYearsRetired;
	}




	public void setiYearsRetired(int iYearsRetired) {
		this.iYearsRetired = iYearsRetired;
	}




	public double getdAnnualReturnRetired() {
		return dAnnualReturnRetired;
	}




	public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
		this.dAnnualReturnRetired = dAnnualReturnRetired;
	}




	public double getdRequiredIncome() {
		return dRequiredIncome;
	}




	public void setdRequiredIncome(double dRequiredIncome) {
		this.dRequiredIncome = dRequiredIncome;
	}




	public double getdMonthlySSI() {
		return dMonthlySSI;
	}




	public void setdMonthlySSI(double dMonthlySSI) {
		this.dMonthlySSI = dMonthlySSI;
	}




	public double AmountToSave()
	{
		return Math.abs(FinanceLib.pmt(dAnnualReturnWorking / 12, iYearsToWork * 12, 
				0, TotalAmountSaved(), false));
	}
	
	public double TotalAmountSaved()
	{
		return Math.abs(FinanceLib.pv(dAnnualReturnRetired / 12 , iYearsRetired * 12, 
				dRequiredIncome - dMonthlySSI, 0, false));
	}
}
