package net.devdiaries.reports;

import lombok.Data;
import net.devdiaries.user.model.User;

import javax.persistence.*;

@Entity
@Data
public class SearchCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String city;
    @Column
    private Integer minRoom;
    @Column
    private Integer maxRoom;
    @Column
    private Integer minArea;
    @Column
    private Integer maxArea;
    @Column
    private Integer maxPrice;
    @Column
    private Integer minPrice;
    @Column
    private Integer minM2price;
    @Column
    private Integer maxM2price;
    @Column
    private Integer minYear;
    @Column
    private Boolean onlyPrivate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public Integer getMinRoom() {
        return minRoom;
    }

    public Integer getMaxRoom() {
        return maxRoom;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public Integer getMinM2price() {
        return minM2price;
    }

    public Integer getMaxM2price() {
        return maxM2price;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public Boolean getOnlyPrivate() {
        return onlyPrivate;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
