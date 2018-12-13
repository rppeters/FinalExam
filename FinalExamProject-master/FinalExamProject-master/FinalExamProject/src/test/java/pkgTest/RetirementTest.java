package pkgTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;
import pkgException.OutOfRangeException;

public class RetirementTest {

	@Test
	public void TotalAmountSaved_Test() throws OutOfRangeException{
		
		int iYearsToWork = 40;
		double dAnnualReturnWorking = 0.07;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 0.02;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		
		try {
			
			Retirement r = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired,
					dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);
			
			double pv = r.TotalAmountSaved();
			assertEquals(1454485.55, pv, 0.1);
			
		} catch (Exception e) {
			fail("Failed to build Retirement");
		}
	}

	
	@Test
	public void AmountToSave_Test() throws OutOfRangeException{
		int iYearsToWork = 40;
		double dAnnualReturnWorking = 0.07;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 0.02;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		
		try {
			
			Retirement r = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired,
					dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);
			
			double pmt = r.AmountToSave();
			assertEquals(554.13, pmt, 0.1);
			
		} catch (Exception e) {
			fail("Failed to build Retirement");
		}
	}
}
