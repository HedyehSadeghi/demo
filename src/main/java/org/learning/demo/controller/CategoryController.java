package org.learning.demo.controller;

import jakarta.validation.Valid;
import org.learning.demo.model.Category;
import org.learning.demo.repository.CategoryRepository;
import org.learning.demo.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "categories/list";

    }


    @GetMapping("/create")
    public String create(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/create";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category categoryForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/create";
        }
        Category savedCategory = categoryRepository.save(categoryForm);
        return "redirect:/categories";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            Category categoryToEdit = result.get();
            model.addAttribute("category", categoryToEdit);
            return "categories/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("category") Category categoryForm, BindingResult bindingResult, Model model) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            if (bindingResult.hasErrors()) {
                return "categories/edit";
            } else {
                Category categorySaved = categoryRepository.save(categoryForm);
                return "redirect:/categories";
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + "not found");
        }

    }


}
