package project;

import java.util.Date;

public class PassengerClass {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String title;
    private String email;
    private String address;
    private String code;
    private String phoneNumber;


    // Constructor
    public PassengerClass(String firstName, String lastName, String birthDate, String title, String email, String address, String code, String phoneNumber, int flightId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.address = address;
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }


    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

 
}

