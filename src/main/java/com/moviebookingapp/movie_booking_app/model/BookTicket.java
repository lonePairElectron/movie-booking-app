package com.moviebookingapp.movie_booking_app.model;

import lombok.Data;

import java.util.List;

@Data
public class BookTicket {

    private String id;
    private Integer numberOfTickets;
    private List<String> seatNumbers;
    private Double totalPrice;
    private String showId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }
    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
    public List<String> getSeatNumbers() {
        return seatNumbers;
    }
    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getShowId() {
        return showId;
    }
    public void setShowId(String showId) {
        this.showId = showId;
    }


}
