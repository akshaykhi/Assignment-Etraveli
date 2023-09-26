package testCases;


import org.junit.jupiter.api.Test;
import pojo.Customer;

import pojo.MovieRental;
import service.RentalInfo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class RentalInfoTest {

         RentalInfo rentalInfo = new RentalInfo();


        @Test
        public void testStatementWithRegularMovie() {
            List<MovieRental> rentals = new ArrayList<>();
            rentals.add(new MovieRental("F001", 3));
            Customer customer = new Customer("John Doe", rentals);

            String statement = rentalInfo.statement(customer);

            String expectedStatement = "Rental Record for John Doe\n" +
                    "\tYou've Got Mail\t3.5\n" +
                    "Amount owed is 3.5\n" +
                    "You earned 1 frequent points\n";
            assertEquals(expectedStatement, statement);
        }

        @Test
        public void testStatementWithNewReleaseMovie() {
            List<MovieRental> rentals = new ArrayList<>();
            rentals.add(new MovieRental("F004", 4));
            Customer customer = new Customer("Alice Smith", rentals);

            String statement = rentalInfo.statement(customer);

            // Verify the statement for the new release movie
            String expectedStatement = "Rental Record for Alice Smith\n" +
                    "\tFast & Furious X\t12.0\n" +
                    "Amount owed is 12.0\n" +
                    "You earned 2 frequent points\n";
            assertEquals(expectedStatement, statement);
        }

        @Test
        public void testStatementWithChildrensMovie() {
            List<MovieRental> rentals = new ArrayList<>();
            rentals.add(new MovieRental("F003", 2));
            Customer customer = new Customer("Bob Johnson", rentals);

            String statement = rentalInfo.statement(customer);

            // Verify the statement for the children's movie
            String expectedStatement = "Rental Record for Bob Johnson\n" +
                    "\tCars\t1.5\n" +
                    "Amount owed is 1.5\n" +
                    "You earned 1 frequent points\n";
            assertEquals(expectedStatement, statement);
        }

        @Test
        public void testStatementWithMultipleRentals() {
            List<MovieRental> rentals = new ArrayList<>();
            rentals.add(new MovieRental("F001", 3));
            rentals.add(new MovieRental("F002", 2));
            rentals.add(new MovieRental("F004", 4));
            Customer customer = new Customer("Eve Brown", rentals);

            String statement = rentalInfo.statement(customer);
            String expectedStatement = "Rental Record for Eve Brown\n" +
                    "\tYou've Got Mail\t3.5\n" +
                    "\tMatrix\t2.0\n" +
                    "\tFast & Furious X\t12.0\n" +
                    "Amount owed is 17.5\n" +
                    "You earned 4 frequent points\n";
            assertEquals(expectedStatement, statement);
        }

        @Test
        public void testStatementWithNoRentals() {
            List<MovieRental> rentals = new ArrayList<>();
            Customer customer = new Customer("No Rentals", rentals);

            String statement = rentalInfo.statement(customer);
            String expectedStatement = "Rental Record for No Rentals\n" +
                    "Amount owed is 0.0\n" +
                    "You earned 0 frequent points\n";
            assertEquals(expectedStatement, statement);
        }

        @Test
        public void testDetermineAmountForEachMovie() {
            assertEquals(2.0, rentalInfo.determineAmountForEachMovie("regular", 1));
            assertEquals(3.5, rentalInfo.determineAmountForEachMovie("regular", 3));
            assertEquals(9.0, rentalInfo.determineAmountForEachMovie("new", 3));
            assertEquals(1.5, rentalInfo.determineAmountForEachMovie("childrens", 2));
            assertEquals(3.0, rentalInfo.determineAmountForEachMovie("childrens", 4));
        }
    }


