package projet_Resto_CIR3;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class Controller implements Initializable {
	
	public Staff currStaff;
	public TxtManagement StaffTxt;
	
	
	public boolean serviceLance = false;
	
	//Manager_______________________________________________________________________________
	
	@FXML
	private Label servLanceLabel;

	@FXML
	private ListView<String> SLV;
	@FXML
	private ListView<String> STLV;
	public void setSLV() {
		SLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		SLV.getItems().addAll(currStaff.getStaffByRole("Serveur"));
	}
	
	@FXML
	private ListView<String> BLV;
	@FXML
	private ListView<String> BTLV;
	public void setBLV() {
		BLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		BLV.getItems().addAll(currStaff.getStaffByRole("Barman"));
	}
	
	@FXML
	private ListView<String> CLV;
	@FXML
	private ListView<String> CTLV;
	public void setCLV() {
		CLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		CLV.getItems().addAll(currStaff.getStaffByRole("Cuistot"));
	}
	
	@FXML
	private ListView<String> MLV;
	@FXML
	private ListView<String> MTLV;
	public void setMLV() {
		MLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		MLV.getItems().addAll(currStaff.getStaffByRole("Manageur"));
	}
	
	public void swapWorkers(ActionEvent e) {
		STLV.getItems().clear();
		BTLV.getItems().clear();
		CTLV.getItems().clear();
		MTLV.getItems().clear();
	}

	public void affFactures(ActionEvent e) {
		try {
			File file = new File ("./src/Txts/Factures/");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}catch(IOException err){
			System.out.println("Erreur impossible d'ouvrir le file explorer ./src/Txts/Factures/" + " : Error " + e);
		}
	}
	
	public void LanceService(ActionEvent e) {
		String tmp = "";
		for(String i : STLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		for(String i : BTLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		for(String i : CTLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		for(String i : MTLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		this.serviceLance = this.currStaff.startService();
		if(this.serviceLance) {
			tmp += "\n Le service possède suffisament d'effectif pour démarrer !";
		}else {
			tmp += "\n Le service ne possède pas suffisament d'effectif pour démarrer !";
		}
		servLanceLabel.setText(tmp);
	}

	public void finService(ActionEvent e) {
		if(serviceLance) {
			this.currStaff.endService(this.StaffTxt);
			this.serviceLance = false;
		}
	}
	//Init__________________________________________________________________________________
	
	public void initResto() {
		this.StaffTxt = new TxtManagement("StaffList");
		this.currStaff = new Staff(StaffTxt);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Init___________________________
		
		this.initResto();
		
		//Manager________________________
		servLanceLabel.setWrapText(true);
		this.setSLV();
		SLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!STLV.getItems().contains(SLV.getSelectionModel().getSelectedItem())){
			    	STLV.getItems().add(SLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		this.setBLV();
		BLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!BTLV.getItems().contains(BLV.getSelectionModel().getSelectedItem())){
			    	BTLV.getItems().add(BLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		this.setCLV();
		CLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!CTLV.getItems().contains(CLV.getSelectionModel().getSelectedItem())){
			    	CTLV.getItems().add(CLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		this.setMLV();
		MLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!MTLV.getItems().contains(MLV.getSelectionModel().getSelectedItem())){
			    	MTLV.getItems().add(MLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		
		
		
	}
	
}
