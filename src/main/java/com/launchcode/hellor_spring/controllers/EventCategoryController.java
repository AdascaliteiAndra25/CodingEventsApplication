package com.launchcode.hellor_spring.controllers;
import com.launchcode.hellor_spring.data.EventCategoryRepository;
import com.launchcode.hellor_spring.models.EventCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    // Display all categories
    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    // Display form to create a new category
    @GetMapping("create")
    public String displayCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    // Process form to create a new category
    @PostMapping("create")
    public String processCreateCategoryForm(@ModelAttribute @Valid EventCategory newCategory,
                                            Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newCategory);
        return "redirect:/eventCategories";
    }

    // Display form to delete categories
    @GetMapping("delete")
    public String displayDeleteCategoryForm(Model model) {
        model.addAttribute("title", "Delete Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/delete";
    }

    // Process form to delete categories
    @PostMapping("delete")
    public String processDeleteCategoryForm(@RequestParam(required = false) int[] categoryIds) {
        if (categoryIds != null) {
            for (int id : categoryIds) {
                eventCategoryRepository.deleteById(id);
            }
        }
        return "redirect:/eventCategories";
    }
}
