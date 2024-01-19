package org.learning.demo.controller;

import jakarta.validation.Valid;
import org.learning.demo.model.Category;
import org.learning.demo.model.Cocktail;
import org.learning.demo.repository.CategoryRepository;
import org.learning.demo.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/create")
    public String create(@RequestParam(name = "categoryId", required = true) Integer categoryId, Model model) {
        Optional<Category> result = categoryRepository.findById(categoryId);
        if (result.isPresent()) {
            Category category = result.get();
            model.addAttribute("category", category);

            Cocktail newCocktail = new Cocktail();
            newCocktail.setCategory(category);
            model.addAttribute("cocktail", newCocktail);
            return "cocktails/create";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + categoryId + " not found");
        }

    }


    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("cocktail") Cocktail cocktailForm, BindingResult bindingResult, Model model) {
        //valido oggetto
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", cocktailForm.getCategory());
            return "cocktails/create";
        }
        Cocktail storedCocktail = cocktailRepository.save(cocktailForm);

        return "redirect:/cocktails/list";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Cocktail> result = cocktailRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("cocktail", result.get());
            return "cocktails/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cocktail with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Cocktail cocktailForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cocktails/edit";
        }
        Cocktail updatedCocktail = cocktailRepository.save(cocktailForm);
        return "redirect:/cocktails/list";

    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Cocktail> result = cocktailRepository.findById(id);
        if (result.isPresent()) {
            Cocktail cocktailToDelete = result.get();
            cocktailRepository.delete(cocktailToDelete);
            redirectAttributes.addFlashAttribute("redirectMessage", "cocktail" + result.get().getName() + " deleted");
            return "redirect:/cocktails/list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cocktail with id " + id + " not found");
        }

    }


}
