package net.devdiaries.reports;


import net.devdiaries.reports.services.ReportCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/report")
public class SearchCriteriaController {

    private final ReportCreationService reportCreationService;

    @Autowired
    public SearchCriteriaController(ReportCreationService reportCreationService) {
        this.reportCreationService = reportCreationService;
    }

    @GetMapping
    public SearchCriteria getSearchCriteria(@AuthenticationPrincipal UserDetails user) {
        return reportCreationService.getUserSearchCriteria(user);
    }


    @PostMapping
    public SearchCriteria createReport(@RequestBody SearchCriteria searchCriteria, @AuthenticationPrincipal UserDetails user) {
        System.out.println(searchCriteria.getEmail());
        System.out.println(user.getUsername());
        reportCreationService.saveReportCreation(searchCriteria, user);
    return searchCriteria;
    }
}
