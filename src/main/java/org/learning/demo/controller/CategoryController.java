package org.learning.demo.controller;

import org.learning.demo.model.Category;
import org.learning.demo.repository.CategoryRepository;
import org.learning.demo.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String list(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "categories/list";

    }


}
