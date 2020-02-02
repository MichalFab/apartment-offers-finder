package net.devdiaries.reports.services;

import net.devdiaries.reports.SearchCriteria;
import net.devdiaries.reports.exceptions.OfferException;
import net.devdiaries.reports.repository.SearchCriteriaRepository;
import net.devdiaries.user.model.User;
import net.devdiaries.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportCreationService {

    private final SearchCriteriaRepository searchCriteriaRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportCreationService(SearchCriteriaRepository searchCriteriaRepository, UserRepository userRepository) {
        this.searchCriteriaRepository = searchCriteriaRepository;
        this.userRepository = userRepository;
    }

    public SearchCriteria saveReportCreation(SearchCriteria searchCriteria, UserDetails userDetails) {
        User user = userRepository.findByUserCredentialsUsername(userDetails.getUsername()).orElseThrow(() -> new OfferException("Cannot save report"));
        if (searchCriteriaRepository.findAllByUser(user).isPresent()) {
            SearchCriteria currentSearchCriteria = searchCriteriaRepository.findAllByUser(user).get();
            currentSearchCriteria.setCity(searchCriteria.getCity());
            currentSearchCriteria.setEmail(searchCriteria.getEmail());
            currentSearchCriteria.setMaxArea(searchCriteria.getMaxArea());
            currentSearchCriteria.setMinArea(searchCriteria.getMinArea());
            currentSearchCriteria.setMaxPrice(searchCriteria.getMaxPrice());
            currentSearchCriteria.setMinPrice(searchCriteria.getMinPrice());
            currentSearchCriteria.setMaxM2price(searchCriteria.getMaxM2price());
            currentSearchCriteria.setMaxRoom(searchCriteria.getMaxRoom());
            currentSearchCriteria.setMinRoom(searchCriteria.getMinRoom());
            currentSearchCriteria.setMinM2price(searchCriteria.getMinM2price());
            currentSearchCriteria.setMinYear(searchCriteria.getMinYear());
            currentSearchCriteria.setOnlyPrivate(searchCriteria.getOnlyPrivate());
            return searchCriteriaRepository.save(currentSearchCriteria);

        } else {
            searchCriteria.setUser(user);
        }
        return searchCriteriaRepository.save(searchCriteria);
    }

    public SearchCriteria getUserSearchCriteria(UserDetails userDetails) {
        User user = userRepository.findByUserCredentialsUsername(userDetails.getUsername()).orElseThrow(() -> new OfferException("Cannot get user info"));
        Optional<SearchCriteria> userSearchCriteria = searchCriteriaRepository.findAllByUser(user);
        return userSearchCriteria.orElse(null);
    }

}
