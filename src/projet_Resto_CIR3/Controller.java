package projet_Resto_CIR3;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class Controller implements Initializable {
	
	public Staff currStaff;
	public Inventaire currInv;
	public Salle currSalle;
	public Menu currMenu;
	public Menu currMenuBoissons;
	public TxtManagement StaffTxt;
	public TxtManagement inventaireTxt;
	public TxtManagement listeDeCourse;
	public TxtManagement platsTxt;
	public TxtManagement boissonsTxt;
	
	public boolean serviceLance = false;
	private ArrayList<Integer> centAnsPlatCounter = new ArrayList<Integer>();
	private ArrayList<Integer> centAnsBoissonsCounter = new ArrayList<Integer>();
	
	private ArrayList<ArrayList<String>> tablesCuisineFeed = new ArrayList<ArrayList<String>>();
	
	//Manager_______________________________________________________________________________
	@FXML
	private Label servLanceLabel;

	@FXML
	private ListView<String> SLV;
	@FXML
	private ListView<String> STLV;
	public void setSLV() {
		this.SLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.SLV.getItems().addAll(currStaff.getStaffByRole("Serveur"));
	}
	
	@FXML
	private ListView<String> BLV;
	@FXML
	private ListView<String> BTLV;
	public void setBLV() {
		this.BLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.BLV.getItems().addAll(currStaff.getStaffByRole("Barman"));
	}
	
	@FXML
	private ListView<String> CLV;
	@FXML
	private ListView<String> CTLV;
	public void setCLV() {
		this.CLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.CLV.getItems().addAll(currStaff.getStaffByRole("Cuistot"));
	}
	
	@FXML
	private ListView<String> MLV;
	@FXML
	private ListView<String> MTLV;
	public void setMLV() {
		this.MLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.MLV.getItems().addAll(currStaff.getStaffByRole("Manageur"));
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
	
	public void listeCourse(ActionEvent e) {
		this.currInv.listeCourse(listeDeCourse);
		try {
			File file = new File ("./src/Txts/");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}catch(IOException err){
			System.out.println("Erreur impossible d'ouvrir le file explorer ./src/Txts/" + " : Error " + e);
		}
	}
	
	public void LanceService(ActionEvent e) {
		String tmp = "";
		for(String i : this.STLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		for(String i : this.BTLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		for(String i : this.CTLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		for(String i : this.MTLV.getItems()) {
			tmp += this.currStaff.startShift(i);
			}
		this.serviceLance = this.currStaff.startService();
		if(this.serviceLance) {
			tmp += "\n Le service possede suffisament d'effectif pour demarrer !";
		}else {
			tmp += "\n Le service ne possede pas suffisament d'effectif pour demarrer !";
		}
		servLanceLabel.setText(tmp);
	}

	public void finService(ActionEvent e) {
		if(this.serviceLance) {
			this.currStaff.endService(this.StaffTxt);
			this.currInv.listeCourse(listeDeCourse);
			this.currInv.updateInv(inventaireTxt);
			this.serviceLance = false;
		}
	}
	
	//Serveur_______________________________________________________________________________
	@FXML
	private ChoiceBox<Integer> tableChoice;
			//currTableNum = tableChoice.getValue();
	private Integer[] tableNums = {0, 1, 2, 3, 4};
	
	@FXML
	private Label stockEmptyLabel;
	
	@FXML
	private ListView<String> commandeFeed;
	public void setCommandeFeed(ActionEvent e) {
		int index = this.tableChoice.getValue();
		this.commandeFeed.getItems().clear();
		this.commandeFeed.getItems().addAll(this.currSalle.listeTable.get(index).allPlatCommandeNamePrice());
		this.setTotalPrice();
	}
	
	public void centAns(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance && this.centAnsPlatCounter.get(index) == 0 && this.centAnsBoissonsCounter.get(index) == 0) {
			this.centAnsPlatCounter.set(index, 7);
			this.centAnsBoissonsCounter.set(index, 7);
			this.currSalle.listeTable.get(index).commandePlat(13, this.currMenu, this.currInv);
		}
		this.setCommandeFeed(e);
	}

	public void verreEau(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsBoissonsCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(0, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "verreEau");
				}else{stockEmptyLabel.setText("");};
				this.centAnsBoissonsCounter.set(index, this.centAnsBoissonsCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(0, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "verreEau");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void Limonade(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsBoissonsCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(1, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "Limonade");
				}else{stockEmptyLabel.setText("");};
				this.centAnsBoissonsCounter.set(index, this.centAnsBoissonsCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(1, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "Limonade");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void cidreDoux(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsBoissonsCounter.get(index) > 0) {
				if(this.currSalle.listeTable.get(index).commandePlatMenu100ans(2, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "cidreDoux");
				}else{stockEmptyLabel.setText("");};
				this.centAnsBoissonsCounter.set(index, this.centAnsBoissonsCounter.get(index)-1);
			}else {
				if(this.currSalle.listeTable.get(index).commandePlat(2, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "cidreDoux");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void biere(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsBoissonsCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(3, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "biere");
				}else{stockEmptyLabel.setText("");};
				this.centAnsBoissonsCounter.set(index, this.centAnsBoissonsCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(3, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "biere");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void jusFruit(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsBoissonsCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(4, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "jusFruit");
				}else{stockEmptyLabel.setText("");};
				this.centAnsBoissonsCounter.set(index, this.centAnsBoissonsCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(4, this.currMenuBoissons, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "jusFruit");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void SaladeTomate(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(0, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "SaladeTomate");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(0, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "SaladeTomate");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void Salade(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(1, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "Salade");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(1, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "Salade");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void PotageOignon(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(2, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PotageOignon");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(2, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PotageOignon");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void PotageTomate(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(3, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PotageTomate");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(3, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PotageTomate");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void PotageChampignon(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(this.currSalle.listeTable.get(index).commandePlatMenu100ans(4, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PotageChampignon");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(4, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PotageChampignon");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void BurgerRoyale(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(5, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "BurgerRoyale");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(5, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "BurgerRoyale");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void BurgerMedium(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(6, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "BurgerMedium");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(6, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "BurgerMedium");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void BurgerMeatOnly(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(7, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "BurgerMeatOnly");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(7, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "BurgerMeatOnly");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void PizzaFromage(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(8, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PizzaFromage");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(8, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PizzaFromage");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void PizzaReine(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(9, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PizzaReine");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(9, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PizzaReine");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void PizzaChorizo(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(10, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PizzaChorizo");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(10, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "PizzaChorizo");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void FajitasPoulet(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(11, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "FajitasPoulet");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(11, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "FajitasPoulet");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	public void FajitasViande(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			if(this.centAnsPlatCounter.get(index) > 0) {
				if(!this.currSalle.listeTable.get(index).commandePlatMenu100ans(12, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "FajitasViande");
				}else{stockEmptyLabel.setText("");};
				this.centAnsPlatCounter.set(index, this.centAnsPlatCounter.get(index)-1);
			}else {
				if(!this.currSalle.listeTable.get(index).commandePlat(12, this.currMenu, this.currInv)) {
					stockEmptyLabel.setText("stock insuffisants pour commander " + "FajitasViande");
				}else{stockEmptyLabel.setText("");};
			}
		}
		this.setCommandeFeed(e);
	}
	
	@FXML
	private Label totalPriceLabel;
	public void setTotalPrice() {
		int index = this.tableChoice.getValue();
		int res = 0;
		for (int i=0; i<this.currSalle.listeTable.get(index).listePlatsCommandes.size(); i++) {
			res += this.currSalle.listeTable.get(index).listePlatsCommandes.get(i).prix;
		}
		totalPriceLabel.setText("Prix Total : " + res);
	}
	
	public void tablePaye(ActionEvent e) {
		int index = this.tableChoice.getValue();
		if(this.serviceLance) {
			this.currSalle.listeTable.get(index).makeFacture();
		}
		this.currSalle.listeTable.get(index).listePlatsCommandes.clear();
		this.setCommandeFeed(e);
		//Flush cuisine feed
	}
	
	public void validCommande(ActionEvent e) {
		int index = this.tableChoice.getValue();
		ArrayList<String> res = new ArrayList<String>();
		if(this.serviceLance) {
			res.add("table "+index+" =====================================================================================================");
			for (int i=0; i<this.currSalle.listeTable.get(index).listePlatsCommandes.size(); i++) {
				if (i >= this.tablesCuisineFeed.get(index).size()) {
					this.tablesCuisineFeed.get(index).add(this.currSalle.listeTable.get(index).listePlatsCommandes.get(i).nomDuPlat);
					res.add(this.currSalle.listeTable.get(index).listePlatsCommandes.get(i).nomDuPlat);
				}
			}
			this.cuisineFeed.getItems().addAll(res);
		}
	}
	
	//Barman/Cuisinier______________________________________________________________________
	@FXML
	private ListView<String> cuisineFeed;
	
	//Init__________________________________________________________________________________
	public void initResto() {
		this.StaffTxt = new TxtManagement("StaffList");
		this.inventaireTxt = new TxtManagement("inventaire");
		this.listeDeCourse = new TxtManagement("liste_de_course");
		this.platsTxt = new TxtManagement("plats");
		this.boissonsTxt = new TxtManagement("boissons");
		this.currStaff = new Staff(this.StaffTxt);
		this.currInv = new Inventaire(this.inventaireTxt);
		this.currSalle = new Salle(this.tableNums.length);
		this.currMenu = new Menu(this.platsTxt);
		this.currMenuBoissons = new Menu(this.boissonsTxt);
		//this.currMenu.affMenu();
		for (int i=0; i<this.tableNums.length; i++) {
			this.centAnsPlatCounter.add(0);
			this.centAnsBoissonsCounter.add(0);
			this.tablesCuisineFeed.add(new ArrayList<String>());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Init______________________________________________________
		this.initResto();
		
		//Manager___________________________________________________
		this.servLanceLabel.setWrapText(true);
		this.setSLV();
		this.SLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!STLV.getItems().contains(SLV.getSelectionModel().getSelectedItem())){
			    	STLV.getItems().add(SLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		this.setBLV();
		this.BLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!BTLV.getItems().contains(BLV.getSelectionModel().getSelectedItem())){
			    	BTLV.getItems().add(BLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		this.setCLV();
		this.CLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!CTLV.getItems().contains(CLV.getSelectionModel().getSelectedItem())){
			    	CTLV.getItems().add(CLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		this.setMLV();
		this.MLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    if (!MTLV.getItems().contains(MLV.getSelectionModel().getSelectedItem())){
			    	MTLV.getItems().add(MLV.getSelectionModel().getSelectedItem());
			    }
			}
		});
		
		//Serveur___________________________________________________
		this.tableChoice.getItems().addAll(this.tableNums);
		this.tableChoice.setValue(0);
		this.tableChoice.setOnAction(this::setCommandeFeed);
		this.stockEmptyLabel.setWrapText(true);
		
	}
	//System.out.println();
}
