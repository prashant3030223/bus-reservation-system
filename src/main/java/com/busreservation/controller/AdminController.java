package com.busreservation.controller;

import com.busreservation.exception.ResourceNotFoundException;
import com.busreservation.model.*;
import com.busreservation.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private BusService busService;
    @Autowired private ScheduleService scheduleService;
    @Autowired private BookingService bookingService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("totalBuses", busService.countBuses());
        model.addAttribute("totalSchedules", scheduleService.countSchedules());
        model.addAttribute("totalBookings", bookingService.countBookings());
        return "admin/admin-dashboard";
    }

    @GetMapping("/bookings")
    public String viewAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin/view-bookings";
    }

    @GetMapping("/buses")
    public String manageBuses(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("buses", busService.getAllBuses());
        return "admin/manage-buses";
    }

    @PostMapping("/buses/add")
    public String addBus(@Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("buses", busService.getAllBuses());
            return "admin/manage-buses";
        }
        busService.saveBus(bus);
        return "redirect:/admin/buses";
    }

    @GetMapping("/buses/edit/{id}")
    public String showEditBusForm(@PathVariable Long id, Model model) {
        Bus bus = busService.getBusById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + id));
        model.addAttribute("bus", bus);
        return "admin/edit-bus";
    }

    @PostMapping("/buses/update/{id}")
    public String updateBus(@PathVariable Long id, @Valid @ModelAttribute("bus") Bus busDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-bus";
        }
        busDetails.setId(id);
        busService.saveBus(busDetails);
        return "redirect:/admin/buses";
    }

    @GetMapping("/buses/delete/{id}")
    public String deleteBus(@PathVariable Long id) {
        busService.deleteBusById(id);
        return "redirect:/admin/buses";
    }
    
    @GetMapping("/schedules")
    public String manageSchedules(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("buses", busService.getAllBuses());
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "admin/manage-schedules";
    }

    @PostMapping("/schedules/add")
    public String addSchedule(@ModelAttribute Schedule schedule, @RequestParam Long busId) {
        Bus bus = busService.getBusById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + busId));
        schedule.setBus(bus);
        scheduleService.saveSchedule(schedule);
        return "redirect:/admin/schedules";
    }

    @GetMapping("/schedules/edit/{id}")
    public String showEditScheduleForm(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + id));
        model.addAttribute("schedule", schedule);
        model.addAttribute("buses", busService.getAllBuses());
        return "admin/edit-schedule";
    }

    @PostMapping("/schedules/update/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute Schedule scheduleDetails, @RequestParam Long busId) {
        Bus bus = busService.getBusById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + busId));
        scheduleDetails.setId(id);
        scheduleDetails.setBus(bus);
        scheduleService.saveSchedule(scheduleDetails);
        return "redirect:/admin/schedules";
    }

    @GetMapping("/schedules/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
        return "redirect:/admin/schedules";
    }
}