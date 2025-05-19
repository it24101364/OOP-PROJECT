package com.example.bookingpayment.controller;

import com.example.bookingpayment.model.Booking;
import com.example.bookingpayment.model.Payment;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // allows access from your frontend
@RestController
@RequestMapping("/api")

public class BookingPaymentController {
    @PostMapping("/booking")
    public String handleBooking(@RequestBody Booking booking) {
        System.out.println("Booking Received: " + booking.getService() + ", " + booking.getDate());
        return "Booking submitted successfully!";
    }

    @PostMapping("/payment")
    public String handlePayment(@RequestBody Payment payment) {
        System.out.println("Payment Received: " + payment.getAmount() + ", " + payment.getMethod());
        return "Payment successful!";
    }

}
