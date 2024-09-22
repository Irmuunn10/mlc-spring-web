package com.example.example.services;

import com.example.example.CurrentUser;
import com.example.example.Dtos.RouteDetailsDTO;
import com.example.example.enitites.Category;
import com.example.example.enitites.Picture;
import com.example.example.enitites.Route;
import com.example.example.enitites.User;
import com.example.example.repositories.RouteRepository;
import com.example.example.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public RouteService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = new ModelMapper();
    }

    public RouteDetailsDTO findDetailsById(Long id) {
        Optional<Route> exist = routeRepository.findById(id);
        if (exist.isPresent()) {
            Route route = exist.get();
            return new RouteDetailsDTO(route, route.getPictures().stream().map(Picture::getUrl).toList());
        }
        return new RouteDetailsDTO();
    }

    public boolean addRoutes(RouteDetailsDTO routeDetailsDTO) {
        User author = this.userRepository.findByUsername(routeDetailsDTO.getAuthorName());

        Route route = this.modelMapper.map(routeDetailsDTO, Route.class)
                .setName(routeDetailsDTO.getName())
                .setAuthor(author)
                .setDescription(routeDetailsDTO.getDescription())
                .setVideoUrl(routeDetailsDTO.getVideoUrl())
                .setGpxCoordinates(routeDetailsDTO.getGpxCoordinates())
                .setCategories(routeDetailsDTO.getCategories());

        routeRepository.save(route);
        return true;
    }
}
