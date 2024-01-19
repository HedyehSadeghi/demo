package org.learning.demo.controller;

import org.learning.demo.model.Cocktail;
import org.learning.demo.repository.CategoryRepository;
import org.learning.demo.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cocktails")
public class CocktailController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CocktailRepository cocktailRepository;


    @GetMapping("/list")
    public String list(Model model) {
        List<Cocktail> cocktailList = cocktailRepository.findAll();
        model.addAttribute("cocktailList", cocktailList);
        return "cocktails/list";

    }


}
