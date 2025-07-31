package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Nationalized
    @Column(name = "LastName", length = 50)
        private String lastName;

    @Nationalized
    @Column(name = "Email", length = 100)
    private String email;

    @Nationalized
    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Nationalized
    @Column(name = "Address", length = 200)
    private String address;

    @Nationalized
    @Column(name = "City", length = 100)
    private String city;

    @Nationalized
    @Column(name = "Country", length = 100)
    private String country;

    @Nationalized
    @Column(name = "PostalCode", length = 20)
    private String postalCode;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "CreatedAt")
    private Instant createdAt;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Nationalized
    @Column(name = "Gender", length = 10)
    private String gender;

    @Nationalized
    @Lob
    @Column(name = "Notes")
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}