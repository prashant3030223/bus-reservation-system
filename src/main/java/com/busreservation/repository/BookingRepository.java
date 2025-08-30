package com.busreservation.repository;

import com.busreservation.model.Booking;
import com.busreservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}