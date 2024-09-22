package com.example.example.controllers;

import com.example.example.Dtos.RouteDetailsDTO;
import com.example.example.repositories.RouteRepository;
import com.example.example.services.RouteService;
import com.example.example.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class RouteController {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteRepository routeRepository, UserService userService, RouteService routeService) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.routeService = routeService;
    }
    @PostMapping("/routes/add")
    public String add(@Valid RouteDetailsDTO routeDetailsDTO, BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeDetailsDTO",routeDetailsDTO );
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResults.routeDetailsDTO", bindingResult);

            return "redirect:/routes/add";
        }
        if (routeService.addRoutes(routeDetailsDTO)) {
            return "redirect:/";
        }
        return "redirect:/routes/add";
    }
    @GetMapping("/routes/add")
    public String add(Model model, Principal principal) {
        if (!model.containsAttribute("routeDetailsDTO")) {
            model.addAttribute("routeDetailsDTO",new RouteDetailsDTO());
        }
        return "add-route";
    }

    @GetMapping("/routes")
    public String routes(Model model, Principal principal) {
        model.addAttribute("routes", routeRepository.findAll());
        return "routes";
    }

    @GetMapping("/routes/details/{id}")
    public String details(Model model, @PathVariable Long id) {
        model.addAttribute("routeDetails", routeService.findDetailsById(id));
        return "details";
    }

    @GetMapping("/routes/pedestrian")
    public String getPedetsrians(Model model) {
        model.addAttribute("pedestrianRoutes", routeRepository.findAllByCategories_name("Pedestrian".toUpperCase()));
        return "pedestrian";
    }

    @GetMapping("/routes/car")
    public String getCar(Model model) {
        model.addAttribute("pedestrianRoutes", routeRepository.findAllByCategories_name("car".toUpperCase()));
        return "pedestrian";
    }

    @GetMapping("/routes/motorcycle")
    public String getMotorcycle(Model model) {
        model.addAttribute("motorcycleRoutes", routeRepository.findAllByCategories_name("motorcycle".toUpperCase()));
        return "motorcycle";
    }


    @GetMapping("/routes/bicycle")
    public String getBicycle(Model model) {
        model.addAttribute("pedestrianRoutes", routeRepository.findAllByCategories_name("bicycle".toUpperCase()));
        return "pedestrian";
    }
}
