package com.lab;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class TransferController extends WebMvcConfigurerAdapter
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(TransferForm transferForm)
    {
        return "form";
    }

    @PostMapping("/")
    public String checkTransferData(@Valid TransferForm transferForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "form";
        }

        return "redirect:/results";
    }
}
