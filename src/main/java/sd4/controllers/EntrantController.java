package sd4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sd4.model.Entrant;
import sd4.model.EntrantVenueJoin;
import sd4.service.EntrantService;
import sd4.service.EntrantVenueJoinService;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EntrantController {

    @Autowired
    private EntrantService entrantService;

    @Autowired
    private EntrantVenueJoinService entrantVenueJoinService;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/")
    public ModelAndView unfiltered(ModelAndView modelAndView) {
        List<EntrantVenueJoin> entrantVenueJoinsList = new ArrayList<>();
        if (entrantService.findAll().isEmpty()){
            modelAndView.addObject("errorMessage", messageSource.getMessage("somethingWentWrong", null,  LocaleContextHolder.getLocale()));
            modelAndView.setViewName("/error");
        }
        else{
            modelAndView.addObject("entrantsFullSearch", entrantVenueJoinsList);
            modelAndView.addObject("entrants", entrantService.findAll());
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

    @PostMapping("/filter")
    public ModelAndView getEntrants(ModelAndView modelAndView,
                                    @RequestParam (required = false) String country,
                                    @RequestParam (required = false) String dFrom,
                                    @RequestParam (required = false) String dTo,
                                    @RequestParam (required = false) Integer capacity,
                                    @RequestParam (required = false) Integer totalPointsFrom,
                                    @RequestParam (required = false) Integer totalPointsTo,
                                    @RequestParam (required = false) String searchOption) {
        List<EntrantVenueJoin> entrantVenueJoinsList = new ArrayList<>();
        List<Entrant> entrantList = new ArrayList<>();
        try{
            if (country != null && dFrom.length() > 0 && dTo.length() > 0 && capacity != null && totalPointsFrom != null && totalPointsTo != null){
                modelAndView.addObject("entrantsFullSearch",
                        entrantVenueJoinService.getEntrantVenueJoinFullSearch(country, formatter.parse(dFrom), formatter.parse(dTo), capacity, totalPointsFrom, totalPointsTo));
                modelAndView.addObject("entrants", entrantList);
                modelAndView.setViewName("/index");
            }
            else if (country != null && dFrom.length() > 0 && dTo.length() > 0){
                if (searchOption == null){
                    modelAndView.addObject("entrants", entrantService.findAllByHostCountryContainingIgnoreCaseAndDateOfFinalBetweenOrderByTotalPoints(country, formatter.parse(dFrom), formatter.parse(dTo)));
                }
                else if(searchOption.equals("firstSemiFinal")){
                    modelAndView.addObject("entrants", entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "first-semi-final", "", ""));
                }
                else if(searchOption.equals("secondSemiFinal")){
                    modelAndView.addObject("entrants",  entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "", "second-semi-final", ""));
                }
                else if(searchOption.equals("final")){
                    modelAndView.addObject("entrants",  entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "", "", "final"));
                }
                else if(searchOption.equals("firstSemiFinal,secondSemiFinal")){
                    modelAndView.addObject("entrants", entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "first-semi-final","second-semi-final", ""));
                }
                else if(searchOption.equals("firstSemiFinal,final")){
                    modelAndView.addObject("entrants", entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "first-semi-final","","final"));
                }
                else if(searchOption.equals("secondSemiFinal,final")){
                    modelAndView.addObject("entrants", entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "", "second-semi-final", "final"));
                }
                else{
                    modelAndView.addObject("entrants",  entrantService.getEntrantByArtistCountryAndDateRangAndSection(country, formatter.parse(dFrom), formatter.parse(dTo),  "first-semi-final", "second-semi-final", "final"));
                }
                modelAndView.addObject("entrantsFullSearch", entrantVenueJoinsList);
                modelAndView.setViewName("/index");
            }
            else if (country.length() > 0){
                modelAndView.addObject("entrants",entrantService.findAllByHostCountryStartingWithOrderByTotalPoints (country));
                modelAndView.addObject("entrantsFullSearch", entrantVenueJoinsList);
                modelAndView.setViewName("/index");
            }
            else{
                modelAndView.addObject("errorMessage", messageSource.getMessage("formError", null,  LocaleContextHolder.getLocale()));
                modelAndView.setViewName("/error");
            }
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("somethingWentWrong", null,  LocaleContextHolder.getLocale()));
            modelAndView.setViewName("/error");
        }
        return modelAndView;
    }

    @GetMapping("/filter")
    public ModelAndView getEntrants(ModelAndView modelAndView) {
        List<EntrantVenueJoin> entrantVenueJoinsList = new ArrayList<>();
        if (entrantService.findAll().isEmpty()){
            modelAndView.addObject("errorMessage", messageSource.getMessage("somethingWentWrong", null,  LocaleContextHolder.getLocale()));
            modelAndView.setViewName("/error");
        }
        else{
            modelAndView.addObject("entrantsFullSearch", entrantVenueJoinsList);
            modelAndView.addObject("entrants", entrantService.findAll());
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }
    @GetMapping("/mostRecent")
    public ModelAndView getEntrantsTopTen(ModelAndView modelAndView) {
        List<EntrantVenueJoin> entrantVenueJoinsList = new ArrayList<>();
        if (entrantService.getMostRecent().isEmpty()){
            modelAndView.addObject("errorMessage", messageSource.getMessage("somethingWentWrong", null,  LocaleContextHolder.getLocale()));
            modelAndView.setViewName("/error");
        }
        else{
            modelAndView.addObject("entrantsFullSearch", entrantVenueJoinsList);
            modelAndView.addObject("entrants", entrantService.getMostRecent());
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewEntrant(ModelAndView modelAndView, @PathVariable String id)  {
        try{
            Integer aID = Integer.parseInt(id);
            if (entrantVenueJoinService.getEntrantVenueJoinByID(aID).isPresent()) {
                modelAndView.addObject("entrant", entrantVenueJoinService.getEntrantVenueJoinByID(aID));
                modelAndView.setViewName("/drillDown");
            }
            else{
                modelAndView.addObject("errorMessage", messageSource.getMessage("entrantNotFound", null,  LocaleContextHolder.getLocale()));
                modelAndView.setViewName("/error");
            }
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("somethingWentWrong", null,  LocaleContextHolder.getLocale()));
            modelAndView.setViewName("/error");
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView displayEditForm(ModelAndView modelAndView, @PathVariable String id)  {
        try {
            Integer aID = Integer.parseInt(id);
            if (entrantService.findOne(aID).isPresent()){
                modelAndView.addObject("entrant", entrantService.findOne(aID));
                modelAndView.setViewName("/editEntrant");
            }
            else {
                modelAndView.addObject("errorMessage", messageSource.getMessage("entrantNotFound", null,  LocaleContextHolder.getLocale()));
                modelAndView.setViewName("/error");
            }
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("somethingWentWrong", null,  LocaleContextHolder.getLocale()));
            modelAndView.setViewName("/error");
        }
        return modelAndView;
    }

    @PostMapping("/editEntrant")
    public ModelAndView editAEntrant(@Valid @ModelAttribute("entrant") Entrant entrant, BindingResult result,
                                     @RequestParam (required = false) String dateOfFinal) throws ParseException {
        if (dateOfFinal.length() > 0){
            if (result.hasErrors())
                return new ModelAndView("/editEntrant");
            entrantService.saveEntrant(entrant);
            return new ModelAndView("redirect:/");
        }
        else {
            return new ModelAndView("/error", "errorMessage", messageSource.getMessage("dateCannotBeEmpty", null,  LocaleContextHolder.getLocale()));
        }
    }
}
