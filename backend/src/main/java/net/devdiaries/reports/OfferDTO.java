package net.devdiaries.reports;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

import java.util.Date;
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OfferDTO {
    String url;
    String imageUrl;
    String title;
    String city;
    String district;
    Double area;
    String textAbout;
    Double price;
    Double M2price;
    Integer phoneNumber;
    Integer roomNumber;
    Integer floor;
    Boolean isPrivate;
    Integer year;
    Double rentPayments;
    Boolean isPriceDown;
    Date createDate;
}
