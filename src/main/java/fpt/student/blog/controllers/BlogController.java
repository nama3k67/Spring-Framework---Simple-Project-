package fpt.student.blog.controllers;

import fpt.student.blog.entities.Blogs;
import fpt.student.blog.entities.Category;
import fpt.student.blog.models.BlogDTO;
import fpt.student.blog.services.BLogService;
import fpt.student.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    private final BLogService bLogService;
    private final CategoryService categoryService;

    @Autowired
    public BlogController(BLogService bLogService, CategoryService categoryService) {
        this.bLogService = bLogService;
        this.categoryService = categoryService;
    }

    @GetMapping("home")
    public String display(ModelMap model){
        List<Blogs> blogs = bLogService.findAll(Sort.by("dateModify").descending());
        model.addAttribute("blogs", blogs);
        return "home";
    }

    @GetMapping("displayByCategory")
    public String displayByCategory(ModelMap model, @RequestParam("cateId") int id){
        List<Blogs> blogs = bLogService.findByCateId(id);
        model.addAttribute("blogs", blogs);
        return "home";
    }

    @GetMapping("blog")
    public String displayById(ModelMap model, @RequestParam("id") int id){
        List<Blogs> blogs = new ArrayList<>();
        Blogs blog = bLogService.findById(id).orElse(new Blogs());
        blogs.add(blog);
        model.addAttribute("blogs", blogs);
        int count = blog.getPageCount() + 1;
        blog.setPageCount(count);
        bLogService.save(blog);
        return "blog";
    }

    @GetMapping("aboutme")
    public String aboutme(){
        return "aboutme";
    }

    @GetMapping("overview")
    public String overview(ModelMap model){
        List<Blogs> blogs = bLogService.findAll(Sort.by("dateModify").descending());
        model.addAttribute("blogs", blogs);
        return "overview";
    }

    @GetMapping("createNewBlog")
    public String createNewPage(ModelMap model){
        List<Category> categories = (List<Category>) categoryService.findAll();
        BlogDTO blogDTO = new BlogDTO();
        model.addAttribute("categories", categories);
        model.addAttribute("blogDTO", blogDTO);
        return "createNewBlog";
    }

    @PostMapping("createNewBlog")
    public String createNewBlog(ModelMap model, @Validated @ModelAttribute(name = "blogDTO") BlogDTO blogDTO, BindingResult result){
        if (result.hasErrors()){
            List<Category> categories = (List<Category>) categoryService.findAll();
            model.addAttribute("categories", categories);
            return "createNewBlog";
        }
        Blogs blog = new Blogs();
        Date date = new Date(new java.util.Date().getTime());
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setPicture(blogDTO.getPicture());
        blog.setPageCount(0);
        blog.setCateId(blogDTO.getCateId());
        blog.setDateModify(date);
        Category category = categoryService.findById(blogDTO.getCateId()).get();
        blog.setCategoryByCateId(category);
        bLogService.save(blog);

        return "redirect:home";
    }

    @GetMapping("edit/{id}")
    public String editBLog(ModelMap model, @PathVariable("id") int id){
        Blogs blog = bLogService.findById(id).get();
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setCateId(blog.getCateId());
        blogDTO.setContent(blog.getContent());
        blogDTO.setPicture(blog.getPicture());
        blogDTO.setTitle(blog.getTitle());

        model.addAttribute("blogDTO", blogDTO);
        model.addAttribute("id", id);

        List<Category> categories = (List<Category>) categoryService.findAll();
        model.addAttribute("categories", categories);
        return "editBlog";
    }

    @PostMapping("edit/{id}")
    public String submitEdit(ModelMap model,@Validated @ModelAttribute(name = "blogDTO") BlogDTO blogDTO, BindingResult result, @PathVariable("id") int id){
        if (result.hasErrors()){
            List<Category> categories = (List<Category>) categoryService.findAll();
            model.addAttribute("categories", categories);
            return "editBlog";
        }
        Blogs blog = bLogService.findById(id).get();
        blog.setPicture(blogDTO.getPicture());
        blog.setCateId(blogDTO.getCateId());
        blog.setContent(blogDTO.getContent());
        blog.setTitle(blogDTO.getTitle());

        Date date = new Date(new java.util.Date().getTime());
        blog.setDateModify(date);
        Category category = categoryService.findById(blogDTO.getCateId()).get();
        blog.setCategoryByCateId(category);
        bLogService.save(blog);

        return "redirect:/profile";
    }

    @GetMapping("delete/{id}")
    public String deleteBlog(@PathVariable("id") int id){
        bLogService.deleteById(id);
        return "redirect:/profile";
    }
}
