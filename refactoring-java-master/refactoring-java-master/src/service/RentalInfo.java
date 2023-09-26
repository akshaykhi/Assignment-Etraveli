package service;

import pojo.Customer;
import pojo.Movie;
import pojo.MovieRental;
import reference.MovieType;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> hm = new HashMap<>();
    hm.put("F001", new Movie("You've Got Mail", "regular"));
    hm.put("F002", new Movie("Matrix", "regular"));
    hm.put("F003", new Movie("Cars", "childrens"));
    hm.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    AtomicInteger frequentEnterPoints = new AtomicInteger();
    // String is immutable and string builder is not
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getCustomerName() + "\n");

    List<MovieRental> rentals = customer.getRentals();

    // converted for each with stream
    totalAmount = rentals.stream()
            .mapToDouble(rental -> {
              String movieCode = hm.get(rental.getMovieId()).getMovieCode();
              double thisAmount = determineAmountForEachMovie(movieCode, rental.getDays());

              frequentEnterPoints.getAndIncrement();

              if ("new".equals(movieCode) && rental.getDays() > 2) {
                frequentEnterPoints.getAndIncrement();
              }

              result.append("\t").append(hm.get(rental.getMovieId()).getMovieTitle()).append("\t").append(thisAmount).append("\n");

              return thisAmount;
            })
            .sum();

    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints.get()).append(" frequent points\n");

    return result.toString();
  }

  // I replaced if statement with switch case
  public double determineAmountForEachMovie(String choice, int days) {
    double thisAmount = 0;
    String s = MovieType.getMovieTypeRegular();
    switch (choice) {
      case "regular":
        thisAmount = 2;
        if (days > 2) {
          thisAmount += (days - 2) * 1.5;
        }
        break;
      case "new":
        thisAmount = days * 3;
        break;
      case "childrens":
        thisAmount = 1.5;
        if (days > 3) {
          thisAmount += (days - 3) * 1.5;
        }
        break;
    }
    return thisAmount;
    }


  }