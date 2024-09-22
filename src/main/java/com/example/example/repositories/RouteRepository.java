package com.example.example.repositories;

import com.example.example.enitites.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    Route findAllByCategories_name(String categories_name);
}
