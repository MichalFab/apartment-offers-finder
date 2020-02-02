package net.devdiaries.reports;

import net.devdiaries.reports.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/offers")
public class OffersController {

    private final OffersService userReportsService;

    @Autowired
    public OffersController(OffersService userReportsService) {
        this.userReportsService = userReportsService;
    }

    @GetMapping(produces = "application/json")
    public List<OfferDTO> currentUser(@AuthenticationPrincipal UserDetails user){
        System.out.println(user.toString());
        return userReportsService.getOffersForUser(user);
    }
}
