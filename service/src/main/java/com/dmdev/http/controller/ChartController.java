package com.dmdev.http.controller;

import com.dmdev.dto.ChartCreateEditDto;
import com.dmdev.service.ChartService;
import com.dmdev.validation.group.CreateAction;
import com.dmdev.validation.group.UpdateAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.groups.Default;

@Controller
@RequestMapping("/charts")
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("charts", chartService.findAll());
        return "chart/charts";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return chartService.findById(id)
                .map(user -> {
                    model.addAttribute("chart", user);
                    return "chart/chart";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated({Default.class, CreateAction.class}) ChartCreateEditDto chart,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("chart", chart);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        chartService.create(chart);
        return "chart/chart";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated({Default.class, UpdateAction.class}) ChartCreateEditDto chart) {
        return chartService.update(id, chart)
                .map(it -> "redirect:/charts/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        chartService.delete(id);
        return "redirect:/charts";
    }

}
