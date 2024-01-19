package org.learning.demo.repository;

import org.learning.demo.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
}
