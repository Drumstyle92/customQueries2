package it.develhope.customQueries2.entities;

import jakarta.persistence.*;

/**
 * @author Drumstyle92
 * Class representing various flight characteristics
 * and mapped as a table on the database
 */
@Entity
@Table
public class FlightEntity {

    /**
     * Id variable with primary key and auto increment to index the table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Variable representing the description of the flight and mapped into the reference table record
     */
    @Column
    private String description;

    /**
     * Variable representing the departure airport mapped into records in the reference table
     */
    @Column(name = "from_airport")
    private String fromAirport;

    /**
     * Variable representing the destination airport mapped into records in the reference table
     */
    @Column(name = "to_airport")
    private String toAirport;

    /**
     * Enumerated variable representing the flight status mapped into records in the reference table
     */
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    /**
     * Default constructor of the class
     */
    public FlightEntity(){}

    /**
     * @param id          Flight id parameter
     * @param description Flight description parameter
     * @param fromAirport Flight departure parameter
     * @param toAirport   Flight destination parameter
     * @param status      Flight status parameter
     *  Parameterized constructor of the class
     */
    public FlightEntity(long id, String description, String fromAirport, String toAirport, FlightStatus status) {
        this.id = id;
        this.description = description;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.status = status;
    }

    /**
     * @return the id
     * Method for encapsulation
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id
     * Method for encapsulation
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the description
     * Method for encapsulation
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description
     * Method for encapsulation
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the from airport
     * Method for encapsulation
     */
    public String getFromAirport() {
        return fromAirport;
    }

    /**
     * @param fromAirport the from airport
     * Method for encapsulation
     */
    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    /**
     * @return the to airport
     * Method for encapsulation
     */
    public String getToAirport() {
        return toAirport;
    }

    /**
     * @param toAirport the to airport
     * Method for encapsulation
     */
    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    /**
     * @return the status
     * Method for encapsulation
     */
    public FlightStatus getStatus() {
        return status;
    }

    /**
     * @param status the status
     * Method for encapsulation
     */
    public void setStatus(FlightStatus status) {
        this.status = status;
    }

}
