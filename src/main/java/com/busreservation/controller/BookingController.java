package com.busreservation.controller;

import com.busreservation.exception.ResourceNotFoundException;
import com.busreservation.model.*;
import com.busreservation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
public class BookingController {

    @Autowired private ScheduleService scheduleService;
    @Autowired private BookingService bookingService;
    @Autowired private UserService userService;

    @GetMapping("/book/{scheduleId}")
    public String showBookingPage(@PathVariable Long scheduleId, Model model) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + scheduleId));
        model.addAttribute("schedule", schedule);
        model.addAttribute("booking", new Booking());
        return "booking-page";
    }

    @PostMapping("/book/confirm")
    public String confirmBooking(@ModelAttribute Booking booking, @RequestParam Long scheduleId, Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        Schedule schedule = scheduleService.getScheduleById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + scheduleId));

        booking.setUser(currentUser);
        booking.setSchedule(schedule);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("CONFIRMED");

        Booking savedBooking = bookingService.saveBooking(booking);
        return "redirect:/ticket/" + savedBooking.getId();
    }
    
    @GetMapping("/ticket/{bookingId}")
    public String showTicketPage(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        model.addAttribute("booking", booking);
        return "ticket";
    }

    @GetMapping("/my-bookings")
    public String showMyBookings(Model model, Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        model.addAttribute("bookings", bookingService.getBookingsByUser(currentUser));
        return "my-bookings";
    }

    @PostMapping("/booking/cancel/{id}")
    public String cancelBooking(@PathVariable Long id, Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        Booking booking = bookingService.getBookingById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        if (!booking.getUser().getId().equals(currentUser.getId())) {
            return "redirect:/my-bookings?error";
        }

        booking.setStatus("CANCELLED");
        bookingService.saveBooking(booking);
        return "redirect:/my-bookings?cancel_success";
    }
}