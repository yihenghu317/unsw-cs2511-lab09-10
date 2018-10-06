package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MoviesController implements Initializable{
	@FXML
	private ArrayList<Button> Addtocart;
	
	@FXML
	private Label TotalPrice;
	
	@FXML 
	private ArrayList<TextField> RentalDays;

	@FXML
	private ArrayList<Label> MovieType;
	
	@FXML
	private ArrayList<Label> MovieTitle;
	
	@FXML
	private ArrayList<Label> MovieNormalprice;
	
	@FXML
	private ArrayList<Label> MovieDailyprice;
	
	@FXML
	private Label alarm;

	@FXML
	private TableView<RentalItem> orderlist;
	
	@FXML
	private TableColumn<RentalItem,String> MovieColumn;
	
	@FXML
	private TableColumn<RentalItem,Double> PriceColumn;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private Label datealarm;
	
	@FXML
	private Label surchargenotice;
	
	@FXML
	private Label discountnotice;
	
	@FXML
	private Button deletebutton;
	
	private Stage currStage;
	private ObservableList<RentalItem> rentalmovies;
	private RentalBasket rentalbasket;
	private Date rentaldate;
//	private ArrayList<Movie> movies;
	
	
	public MoviesController(Stage s) {
		currStage = s;
		this.alarm = new Label();
		this.datealarm = new Label();
		this.orderlist = new TableView<>();
		this.MovieColumn = new TableColumn<>();
		this.PriceColumn = new TableColumn<>();
		this.rentalmovies = FXCollections.observableArrayList();
		this.rentalbasket = new RentalBasket();
		this.rentaldate = new Date();
	}
	
	
	@FXML
	public void addtocartm1() {	
		 compute_price(0);
	}
	
	@FXML
	public void addtocartm2() {
		compute_price(1);
	}
	@FXML
	public void addtocartm3() {
		compute_price(2);
	}
	@FXML
	public void addtocartm4() {
		compute_price(3);
	}
	@FXML
	public void addtocartm5() {
		compute_price(4);
	}
	
	
	@FXML
	public void deleteitem() {
		ObservableList<RentalItem> selectedRows, all;
		all = orderlist.getItems();
		selectedRows = orderlist.getSelectionModel().getSelectedItems();
		
		for (RentalItem item:selectedRows) {
			all.remove(item);
			rentalbasket.remove_Lineitem(item);
		}
		TotalPrice.setText(String.valueOf(rentalbasket.getPrice()) + "$");
	}
	
	public void compute_price(int i) {
		try {
			Integer.parseInt(RentalDays.get(i).getText());	
			int days = Integer.parseInt(RentalDays.get(i).getText());
			if (days < 0) {
				alarm.setText("Please enter a valid number");
				return;
			}
			
			if (!get_date()) return;
			datealarm.setText("");
			double price = get_movies().get(i).getCharge(days);
			RentalItem lineitem = new RentalItem(get_movies().get(i),days,rentaldate);
			rentalbasket.setDate(rentaldate);
			rentalbasket.add_Lineitem(lineitem);
			TotalPrice.setText(String.valueOf(rentalbasket.getPrice()) + "$");
			RentalDays.get(i).setText("");
			alarm.setText("");			
			this.rentalmovies.add(lineitem);
			orderlist.setItems(rentalmovies);
		} catch (Exception e) {
			alarm.setText("Please enter a valid number");
		}
	}
	
	public ObservableList<RentalItem> getMovie(){
		ObservableList<RentalItem> movies = FXCollections.observableArrayList();
		return movies;
	}
	
	public ArrayList<Movie> get_movies(){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		ChildrenPrice children = new ChildrenPrice();
		ClassicPrice classic = new ClassicPrice();
		NewReleasePrice newrelease = new NewReleasePrice();
		RegularPrice regular = new RegularPrice();
		
		
		Movie m1 = new Movie("Annabelle",children);
		Movie m2 = new Movie("Inception",classic);
		Movie m3 = new Movie("The Shining", regular);
		Movie m4 = new Movie("It",newrelease);
		Movie m5 = new Movie("The Cube",regular);
		movies.add(m1);
		movies.add(m2);
		movies.add(m3);
		movies.add(m4);
		movies.add(m5);	
		return movies;	
	}
	
	public void initData() {
		for (int i = 0; i < MovieTitle.size();i++) {
			MovieTitle.get(i).setText(get_movies().get(i).getTitle());
			MovieType.get(i).setText(get_movies().get(i).get_type());
			MovieDailyprice.get(i).setText(String.valueOf(get_movies().get(i).getPriceCode().getDailycharge())+ "$");
			MovieNormalprice.get(i).setText(String.valueOf(get_movies().get(i).getPriceCode().getNormalcharge()) + "$");	
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TotalPrice.setText(String.valueOf(rentalbasket.getPrice()) + "$");
		initData();
		
		MovieColumn.setCellValueFactory(new PropertyValueFactory<RentalItem,String>("movietitle"));
		MovieColumn.setMinWidth(200);
		PriceColumn.setCellValueFactory(new PropertyValueFactory<RentalItem,Double>("price"));
		orderlist.setItems(getMovie());
		
	}
	
	public boolean get_date() {
		try {
			rentaldate.setYear(date.getValue().getYear());
			rentaldate.setMonth(date.getValue().getMonthValue());
			rentaldate.setDate(date.getValue().getDayOfMonth());
			return true;
		} catch (Exception e) {
			datealarm.setText("Please enter valid date");
			return false;
		}
	}
	
	
}
