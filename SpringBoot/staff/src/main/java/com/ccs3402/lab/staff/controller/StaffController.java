package com.ccs3402.lab.staff.controller;

import com.ccs3402.lab.staff.repository.DepartmentRepository;
import com.ccs3402.lab.staff.repository.StaffRepository;
import com.ccs3402.lab.staff.model.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        return "list-staff";
    }

    @GetMapping("signup")
    public String showSignupForm(Staff staff, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "add-staff";
    }

    @PostMapping("add")
    public String addStaff(@Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-staff";
        }
        staffRepository.save(staff);
        return "redirect:/staff/list";
    }

    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        return "choose-staff-to-update";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Staff staff = staffRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));

        model.addAttribute("staff", staff);
        model.addAttribute("departments", departmentRepository.findAll());
        return "update-staff";
    }

    @PostMapping("update/{id}")
    public String updateStaff(@PathVariable("id") long id, @Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            staff.setId((int) id);
            return "update-staff";
        }
        model.addAttribute("staffs", staffRepository.findAll());
        staffRepository.save(staff);
        return "list-staff";
    }

    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        return "choose-staff-to-delete";
    }

    @GetMapping("delete/{id}")
    public String deleteStaff(@PathVariable("id") long id, Model model) {
        Staff staff = staffRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        staffRepository.delete(staff);
        model.addAttribute("staffs", staffRepository.findAll());
        return "list-staff";
    }
}
