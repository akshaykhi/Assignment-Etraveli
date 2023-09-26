package pojo;

import java.util.List;

public class Customer {
    private String customerName;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        customerName = name;
        this.rentals = rentals;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
