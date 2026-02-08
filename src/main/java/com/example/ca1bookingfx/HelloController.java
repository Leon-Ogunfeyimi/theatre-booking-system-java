package com.example.ca1bookingfx;
import com.thoughtworks.xstream.XStream;
import java.io.*;
import javafx.scene.layout.GridPane;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

public class HelloController {

    // Show Management UI Components
    @FXML
    private TextField showName;
    @FXML
    private Spinner<Integer> showDuration;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Spinner<Integer> stallPrice;
    @FXML
    private Spinner<Integer> balconyPrice;
    @FXML
    private Spinner<Integer> circlePrice;
    @FXML
    private ListView<Show> showsList;


    // Performance Management UI Components
    @FXML
    private ComboBox<Show> performancesName;
    @FXML
    private ComboBox<String> performancesTime;
    @FXML
    private DatePicker performanceDate;
    @FXML
    private ListView<Performance> performancesList;

    // Customer Management UI Components
    @FXML
    private TextField customerName;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private ListView<Customer> customersList;

    // Booking Management UI Components
    @FXML
    private ComboBox<Performance> performanceInfo;
    @FXML
    private ComboBox<Customer> customersInfo;
    @FXML
    private ListView<Booking> bookingsList;
    @FXML
    private ListView<String> availableSeatList;
    @FXML
    private TextField seatsSelected;
    @FXML
    private TabPane tabPane;


    // Data Structures for storing information
    private LinkedList<Show> shows = new LinkedList<>();
    private LinkedList<Customer> customers = new LinkedList<>();
    private LinkedList<Performance> performances = new LinkedList<>();
    private LinkedList<Booking> bookings = new LinkedList<>();

    @FXML
    public void initialize() {
        // Set up spinners with appropriate value ranges for numerical fields
        showDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(60, 280, 60));
        stallPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 25));
        balconyPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 300, 150));
        circlePrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 200, 60));

        // Populate the performance time ComboBox with options
        performancesTime.getItems().addAll("Afternoon", "Evening");

        // Link selected show to corresponding performances
        showsList.getSelectionModel().selectedItemProperty().addListener((obs, oldShow, newShow) -> {
            if (newShow != null) {
                updatePerformanceComboBox(newShow);  /// Update performance ComboBox for the selected show
            }
        });
        // Listen for tab changes and update seating layout when the "Seating Layout" tab is selected
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab.getText().equals("Seating Layout")) {
                showSeatingLayout();
            }
        });

        // Update available seats when a performance is selected
        performanceInfo.getSelectionModel().selectedItemProperty().addListener((obs, oldPerformance, newPerformance) -> {
            if (newPerformance != null) {
                updateAvailableSeats(newPerformance);
            }
        });
    }

    // Add a new show
    @FXML
    public void onAddShowButtonClick() {
        String title = showName.getText();
        int time = showDuration.getValue();
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        int stall = stallPrice.getValue();
        int circle = circlePrice.getValue();
        int balcony = balconyPrice.getValue();
        // Validate that required fields are filled
        if (title.isEmpty() || start == null || end == null) {
            System.out.println("Please fill all show details.");
            return;
        }

        // Create and add new show to the list
        Show newShow = new Show(title, time, start.toString(), end.toString(), stall, circle, balcony);
        shows.add(newShow);
        showsList.getItems().setAll(shows);
        performancesName.getItems().add(newShow);// Update the performances ComboBox with the new show

        clearShowFields(); // Clear the form fields after adding the show
        System.out.println("Show added: " + newShow);
    }

    // Delete a selected show
    @FXML
    public void onDeleteShowButtonClick() {
        Show selectedShow = showsList.getSelectionModel().getSelectedItem();
        if (selectedShow != null) {
            shows.remove(selectedShow);
            showsList.getItems().setAll(shows);
            performancesName.getItems().remove(selectedShow); // Remove the show from the performances ComboBox
            System.out.println("Show deleted: " + selectedShow);
        } else {
            System.out.println("No show selected.");
        }
    }

    // Add a new Customer
    @FXML
    public void onAddCustomerButtonClick() {
        String name = customerName.getText();
        String emailInput = email.getText();
        String phone = phoneNumber.getText();

        // Validate that required fields are filled
        if (name.isEmpty() || emailInput.isEmpty() || phone.isEmpty()) {
            System.out.println("Please fill all customer details.");
            return;
        }

        // Create and add new customer to the list
        Customer newCustomer = new Customer(name, emailInput, phone);
        customers.add(newCustomer);
        customersList.getItems().setAll(customers);
        customersInfo.getItems().add(newCustomer); // Update the customers ComboBox

        clearCustomerFields(); // Clear the customer form fields after adding the customer
        System.out.println("Customer added: " + newCustomer);
    }

    // Delete a Customer
    @FXML
    public void onDeleteCustomerButtonClick() {
        Customer selectedCustomer = customersList.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customers.remove(selectedCustomer);
            customersList.getItems().setAll(customers);
            customersInfo.getItems().remove(selectedCustomer); // Update ComboBox
            System.out.println("Customer deleted: " + selectedCustomer);
        } else {
            System.out.println("No customer selected.");
        }
    }

    // Add a new Performance
    @FXML
    public void onAddPerformanceButtonClick() {
        Show selectedShow = performancesName.getValue();
        String time = performancesTime.getValue();
        LocalDate date = performanceDate.getValue();
        // Validate that required fields are selected
        if (selectedShow == null || time == null || date == null) {
            System.out.println("Please select a show, time, and date for the performance.");
            return;
        }

        // Create and add the new performance
        Performance newPerformance = new Performance(selectedShow, date, time);
        performances.add(newPerformance);

        // Ensure the performance is added to the corresponding show
        selectedShow.addPerformance(newPerformance);

        reloadPerformanceList();  // Refresh the performance list to include the new performance
        updatePerformanceComboBox(selectedShow);  // Update the ComboBox with the latest performance list for the selected show
        clearPerformanceFields();
        System.out.println("Performance added: " + newPerformance);
    }

    // Delete a Performance
    @FXML
    public void onDeletePerformanceButtonClick() {
        // Get the selected performance from the ListView
        Performance selectedPerformance = performancesList.getSelectionModel().getSelectedItem();

        if (selectedPerformance != null) {
            // Remove the selected performance from the performances list
            performances.remove(selectedPerformance);

            // Remove the performance from the associated show
            Show associatedShow = selectedPerformance.getShow();
            if (associatedShow != null) {
                associatedShow.removePerformance(selectedPerformance);
            }

            // Remove bookings associated with the performance
            bookings.removeIf(booking -> booking.getPerformance().equals(selectedPerformance));
            bookingsList.getItems().setAll(bookings);

            // Refresh the ListView to reflect the changes
            performancesList.getItems().setAll(performances);
            updatePerformanceComboBox(associatedShow);

            System.out.println("Performance deleted: " + selectedPerformance);
        } else {
            System.out.println("No performance selected to delete.");
        }
    }

    @FXML
    public void onDeleteBookingButtonClick() {
        // Get the selected booking from the ListView
        Booking selectedBooking = bookingsList.getSelectionModel().getSelectedItem();

        if (selectedBooking != null) {
            // Remove the selected booking from the list
            bookings.remove(selectedBooking);

            // Update the ListView
            bookingsList.getItems().setAll(bookings);

            // Release the seats booked in the associated performance
            Performance associatedPerformance = selectedBooking.getPerformance();
            associatedPerformance.releaseSeats(selectedBooking.getSeats());

            System.out.println("Booking deleted: " + selectedBooking);
        } else {
            System.out.println("No booking selected to delete.");
        }
    }


    // Reload the performance list for ListView
    public void reloadPerformanceList() {
        performancesList.getItems().clear();
        for (Performance performance : performances) {
            performancesList.getItems().add(performance);
        }
        System.out.println("Refreshed performance list");
    }

    private void updatePerformanceComboBox(Show show) {
        performanceInfo.getItems().clear();

        // Iterate through the LinkedList to populate the ComboBox
        for (Performance performance : performances) {
            performanceInfo.getItems().add(performance);
        }
    }


    // Add a new Booking
    @FXML
    public void onAddBookingButtonClick() {
        Performance selectedPerformance = performanceInfo.getSelectionModel().getSelectedItem();
        Customer selectedCustomer = customersInfo.getSelectionModel().getSelectedItem();
        String seatsText = seatsSelected.getText();

        // Validation for Booking
        if (selectedPerformance == null) {
            System.out.println("Please select a performance.");
            return;
        }
        if (selectedCustomer == null) {
            System.out.println("Please select a customer.");
            return;
        }
        if (seatsText == null || seatsText.isEmpty()) {
            System.out.println("Please provide seat details.");
            return;
        }

        // Convert the seat details into a list of individual seats
        List<String> seatsToBook = List.of(seatsText.split(",\\s*"));

        // Attempt to book the seats
        if (selectedPerformance.bookSeats(seatsToBook)) {
            // Create a new Booking with both customer and performance info
            Booking newBooking = new Booking(selectedCustomer, selectedPerformance, seatsToBook);
            bookings.add(newBooking);
            bookingsList.getItems().setAll(bookings);
            System.out.println("Booking added: " + newBooking);
        } else {
            System.out.println("Unavailable seat.");
        }

        clearBookingFields();
    }

    // Update available seats when a performance is selected
    private void updateAvailableSeats(Performance performance) {
        availableSeatList.getItems().clear();
        if (performance != null) {
            availableSeatList.getItems().addAll(performance.getAvailableSeats());
        }
    }

    @FXML
    private GridPane seatingGrid;

    @FXML
    public void showSeatingLayout() {
        // Clear previous seating
        seatingGrid.getChildren().clear();

        // Define seating sections to match the rubric
        String[][] balcony = {
                {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8"},
                {"B9", "B10", "B11", "B12", "B13", "B14", "B15", "B16"},
                {"B17", "B18", "B19", "B20", "B21", "B22", "B23", "B24"}
        };

        String[][] circle = {
                {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10"},
                {"C11", "C12", "C13", "C14", "C15", "C16", "C17", "C18", "C19", "C20"},
                {"C21", "C22", "C23", "C24", "C25", "C26", "C27", "C28", "C29", "C30"}
        };

        String[][] stalls = {
                {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10"},
                {"S11", "S12", "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20"},
                {"S21", "S22", "S23", "S24", "S25", "S26", "S27", "S28", "S29", "S30"},
                {"S31", "S32", "S33", "S34", "S35", "S36", "S37", "S38", "S39", "S40"}
        };

        // Add sections to the seating grid with appropriate starting positions
        addSectionToGrid(balcony, 0, 0, "balcony-button");  // Balcony at the top
        addSectionToGrid(circle, 4, 0, "circle-button");    // Circle below the balcony
        addSectionToGrid(stalls, 8, 0, "stalls-button");    // Stalls at the bottom
    }

    private void addSectionToGrid(String[][] section, int startRow, int startColumn, String styleClass) {
        for (int row = 0; row < section.length; row++) {
            for (int col = 0; col < section[row].length; col++) {
                Button seatButton = new Button(section[row][col]);
                seatButton.setMinSize(20, 20);
                seatButton.getStyleClass().add(styleClass);

                // Handle seat button clicks
                seatButton.setOnAction(event -> System.out.println("Seat selected: " + seatButton.getText()));

                seatingGrid.add(seatButton, col + startColumn, row + startRow);
            }
        }
    }

    @FXML
    public void onResetFacilityButtonClick() {
        // Clear all lists
        shows.clear();
        performances.clear();
        bookings.clear();
        customers.clear();

        // Clear all ListViews and ComboBoxes in the UI
        showsList.getItems().clear();
        performancesList.getItems().clear();
        bookingsList.getItems().clear();
        customersList.getItems().clear();

        performancesName.getItems().clear();
        performanceInfo.getItems().clear();
        customersInfo.getItems().clear();
        availableSeatList.getItems().clear();


        clearShowFields();
        clearCustomerFields();
        clearPerformanceFields();
        clearBookingFields();

        System.out.println("All data has been reset: shows, performances, bookings, and customers.");
    }

    // Helper Methods
    private void clearShowFields() {
        showName.clear();
        showDuration.getValueFactory().setValue(120);
        startDate.setValue(null);
        endDate.setValue(null);
        stallPrice.getValueFactory().setValue(50);
        balconyPrice.getValueFactory().setValue(100);
        circlePrice.getValueFactory().setValue(75);
    }

    private void clearCustomerFields() {
        customerName.clear();
        email.clear();
        phoneNumber.clear();
    }

    private void clearPerformanceFields() {
        performancesName.setValue(null);
        performancesTime.setValue(null);
        performanceDate.setValue(null);
    }

    private void clearBookingFields() {
        performanceInfo.setValue(null);
        customersInfo.setValue(null);
        seatsSelected.clear();
    }

    public void saveAll() {
        XStream xstream = new XStream(new com.thoughtworks.xstream.io.xml.DomDriver());

        // Allow specific types
        xstream.allowTypes(new Class[]{
                com.example.ca1bookingfx.Show.class,
                com.example.ca1bookingfx.Customer.class,
                com.example.ca1bookingfx.Performance.class,
                com.example.ca1bookingfx.Booking.class
        });

        try {
            // Save shows
            try (ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("shows.xml"))) {
                out.writeObject(shows);
            }

            // Save customers
            try (ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("customers.xml"))) {
                out.writeObject(customers);
            }

            // Save performances
            try (ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("performances.xml"))) {
                out.writeObject(performances);
            }

            // Save bookings
            try (ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("bookings.xml"))) {
                out.writeObject(bookings);
            }

            System.out.println("Data saved successfully to separate XML files.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadAll() {
        XStream xstream = new XStream(new com.thoughtworks.xstream.io.xml.DomDriver());

        // Allow specific types
        xstream.allowTypes(new Class[]{
                com.example.ca1bookingfx.Show.class,
                com.example.ca1bookingfx.Customer.class,
                com.example.ca1bookingfx.Performance.class
        });

        try {
            // Load shows
            try (ObjectInputStream in = xstream.createObjectInputStream(new FileReader("shows.xml"))) {
                shows = (LinkedList<Show>) in.readObject();
            }

            // Load customers
            try (ObjectInputStream in = xstream.createObjectInputStream(new FileReader("customers.xml"))) {
                customers = (LinkedList<Customer>) in.readObject();
            }

            // Load performances
            try (ObjectInputStream in = xstream.createObjectInputStream(new FileReader("performances.xml"))) {
                performances = (LinkedList<Performance>) in.readObject();
            }


            // Update UI components with loaded data
            showsList.getItems().setAll(shows);
            customersList.getItems().setAll(customers);
            performancesList.getItems().setAll(performances);

            // Update combo boxes
            performancesName.getItems().setAll(shows);
            customersInfo.getItems().setAll(customers);

            System.out.println("Data loaded successfully from separate XML files.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}