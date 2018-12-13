package pkgApp.controller;

import java.net.URL;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pkgApp.RetirementApp;
import pkgCore.Retirement;
import pkgException.OutOfRangeException;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private GridPane rGP;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML 
	private TextField txtAnnualReturnWorking;
	
	@FXML 
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetired;
	
	@FXML 
	private TextField txtRequiredIncome;
	
	@FXML 
	private TextField txtMonthlySSI;
	
	@FXML 
	private Label lblSaveEachMonthCalculation;
	
	@FXML
	private Label lblWhatYouNeedToSaveCalculation;
	

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
		for (Object o : rGP.getChildren()) {
			if (o != null) {
				if (o instanceof TextField) {
					TextField tf = (TextField) o;
					if (GridPane.getColumnIndex(tf) == 1) {
						tf.clear();
					}
				}
				if (o instanceof Label) {
					Label lbl = (Label) o;
					if (GridPane.getColumnIndex(lbl) == 1) {
						lbl.setText(" ");
					}
				}
			}
		}
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		try {
			
			Retirement r = new Retirement(
					Integer.parseInt(txtYearsToWork.getText()), 
					Double.parseDouble(txtAnnualReturnWorking.getText()), 
					Integer.parseInt(txtYearsRetired.getText()), 
					Double.parseDouble(txtAnnualReturnRetired.getText()),
					Double.parseDouble(txtRequiredIncome.getText()), 
					Double.parseDouble(txtMonthlySSI.getText()));
			
			DecimalFormat df = new DecimalFormat("#.00");
			lblSaveEachMonthCalculation.setText("$" + df.format(r.AmountToSave()));
			lblWhatYouNeedToSaveCalculation.setText("$" + df.format(r.TotalAmountSaved()));
			
		//failure to parse input into correct data format	
		} catch (NumberFormatException e) {
			
			for (Object o : rGP.getChildren()) {
				if (o instanceof Label && o != null) {
					Label lbl = (Label) o;
					if (GridPane.getColumnIndex(lbl) == 1) {
						lbl.setText("Please fill in all fields with numbers");
					}
				}
			}
			
		//failure for data to be in correct ranges specified in Retirement Constructor
		} catch (OutOfRangeException e) {
			if (e.getEAttribute() == pkgEnum.EAttributeViolation.YearsToWork) {txtYearsToWork.setText("Must be a Positive Integer");}
			if (e.getEAttribute() == pkgEnum.EAttributeViolation.AnnualReturnWorking) {txtAnnualReturnWorking.setText("Rate Must be Between 0% and 20%");}
			if (e.getEAttribute() == pkgEnum.EAttributeViolation.YearsRetired) {txtYearsRetired.setText("Must be a Positive Integer");}
			if (e.getEAttribute() == pkgEnum.EAttributeViolation.AnnualReturnRetired) {txtAnnualReturnRetired.setText("Rate must be between 0 and 3%");}
			if (e.getEAttribute() == pkgEnum.EAttributeViolation.MonthlySSI) {txtMonthlySSI.setText("Must be a Positive Number");}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
