package com.example.example.Dtos;

public class RouteDTO {

    private String pictureUrl;

    private String name;

    private String description;

    private Long id;

    public RouteDTO() {

    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RouteDTO setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RouteDTO setId(Long id) {
        this.id = id;
        return this;
    }
}