package net.devdiaries.reports.services;

import net.devdiaries.reports.Offer;
import net.devdiaries.reports.OfferDTO;
import net.devdiaries.reports.SearchCriteria;
import net.devdiaries.reports.exceptions.OfferException;
import net.devdiaries.reports.repository.OffersRepository;
import net.devdiaries.reports.repository.SearchCriteriaRepository;
import net.devdiaries.user.model.User;
import net.devdiaries.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class OffersService {

    private final SearchCriteriaRepository searchCriteriaRepository;
    private final UserRepository userRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public OffersService(SearchCriteriaRepository searchCriteriaRepository, UserRepository userRepository, OffersRepository offersRepository) {
        this.searchCriteriaRepository = searchCriteriaRepository;
        this.userRepository = userRepository;
        this.offersRepository = offersRepository;
    }

    public List<OfferDTO> getOffersForUser(UserDetails user) {
        User fetchedUser = userRepository.findByUserCredentialsUsername(user.getUsername()).orElseThrow(() -> new OfferException("cannot find user: " + user.getUsername()));
        SearchCriteria searchCriteria = searchCriteriaRepository.findAllByUser(fetchedUser).orElseThrow(() -> new OfferException("You do not have search criteria"));
        List<Offer> offers = findByCriteria(ofNullable(searchCriteria.getCity()).orElse(null),
                ofNullable(searchCriteria.getOnlyPrivate()).orElse(null),
                ofNullable(searchCriteria.getMinRoom()).orElse(null),
                ofNullable(searchCriteria.getMaxRoom()).orElse(null),
                ofNullable(searchCriteria.getMinYear()).orElse(null),
                ofNullable(searchCriteria.getMinPrice()).orElse(null),
                ofNullable(searchCriteria.getMaxPrice()).orElse(null),
                ofNullable(searchCriteria.getMaxM2price()).orElse(null),
                ofNullable(searchCriteria.getMinM2price()).orElse(null),
                ofNullable(searchCriteria.getMaxArea()).orElse(null),
                ofNullable(searchCriteria.getMinArea()).orElse(null)
        );
        return offers.stream()
                .filter(this::notNumberFields)
                .map(this::mapToOfferDTO).collect(Collectors.toList());
    }

    private List<Offer> findByCriteria(String city, Boolean isPrivate, Integer minRoom, Integer maxRoom, Integer minYear, Integer minPrice,
                                       Integer maxPrice, Integer minM2price, Integer maxM2price, Integer maxArea, Integer minArea) {
        return offersRepository.findAll((Specification<Offer>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (city != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("city"), city)));
            }
            if (isPrivate != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isPrivate"), isPrivate)));
            }
            if (minRoom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("roomNumber"), minRoom));
            }
            if (maxRoom != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("roomNumber"), maxRoom));
            }
            if (minYear != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("year"), minYear));
            }
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (minM2price != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("m2Price"), minM2price));
            }
            if (maxM2price != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("m2Price"), maxM2price));
            }
//            if (minArea != null) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("area"), minArea));
//            }
//            if (maxArea != null) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("area"), maxArea));
//            }
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    private boolean notNumberFields(Offer offer) {
        try {
            Double.parseDouble(offer.getArea());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private OfferDTO mapToOfferDTO(Offer offer) {
        return OfferDTO.builder()
                .area(Double.valueOf(offer.getArea()))
                .city(offer.getCity())
                .createDate(offer.getCreateDate())
                .district(offer.getDistrict())
                .floor(offer.getFloor())
                .imageUrl(offer.getImageUrl())
                .isPriceDown(offer.getIsPriceDown())
                .M2price(offer.getM2Price().doubleValue())
                .price(offer.getPrice().doubleValue())
                .roomNumber(offer.getRoomNumber())
                .textAbout(offer.getTextAbout())
                .rentPayments(offer.getRentPayments() != null ? offer.getRentPayments().doubleValue() : null)
                .title(offer.getTitle())
                .url(offer.getUrl())
                .phoneNumber(offer.getTelNumber())
                .year(offer.getYear())
                .build();
    }

}
