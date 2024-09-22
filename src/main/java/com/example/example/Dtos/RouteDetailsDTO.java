package com.example.example.Dtos;


import com.example.example.enitites.Category;
import com.example.example.enitites.Picture;
import com.example.example.enitites.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RouteDetailsDTO {

    private Long id;

    private String name;

    private String authorName;

    private String description;

    private int totalDistance;


    private String videoUrl;
    private String gpxCoordinates;

    private List<String> pictures;
    private Set<Category> categories;

    public Set<Category> getCategories() {
        return categories;
    }

    public RouteDetailsDTO setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public RouteDetailsDTO() {
        this.pictures = new ArrayList<>();
    }

    public RouteDetailsDTO(Route route, List<String> pictures) {
        this.name = route.getName();
        this.authorName = route.getAuthor().getFullName();
        this.description = route.getDescription();
        this.totalDistance = 1;
        this.videoUrl = route.getVideoUrl();
        this.pictures = pictures;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }


    public Long getId() {
        return id;
    }

    public RouteDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public RouteDetailsDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public RouteDetailsDTO setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public RouteDetailsDTO setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }
}