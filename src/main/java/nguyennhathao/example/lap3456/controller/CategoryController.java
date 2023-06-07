package nguyennhathao.example.lap3456.controller;

import jakarta.validation.Valid;
import nguyennhathao.example.lap3456.entity.Category;
import nguyennhathao.example.lap3456.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllCategories(Model model){
        List<Category> category = categoryService.getAllCategories();
        model.addAttribute("categories",category );
        return "category/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("categories", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("categories") Category book){
        categoryService.saveCategory(book);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Category editCate = categoryService.getCategoryById(id);
        if(editCate != null){
            model.addAttribute("category", editCate);
            return "category/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("category")Category updateCate, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("category", categoryService.getAllCategories());
            return "category/edit";
        }
        categoryService.getAllCategories().stream()
                .filter(category -> category.getId() == updateCate.getId())
                .findFirst()
                .ifPresent(category -> {

                    categoryService.saveCategory(updateCate);
                });
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        Iterator<Category> iterator = categoryService.getAllCategories().iterator();
        while (iterator.hasNext()){
            Category book = iterator.next();
            if(book.getId() == id){
                iterator.remove();
                categoryService.deleteCategory(id);
                break;}
        }
        return "redirect:/categories";
    }

}
