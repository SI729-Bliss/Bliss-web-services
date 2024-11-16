package com.beautyservices.bliss.bookingmanagement.interfaces.rest;

import com.beautyservices.bliss.bookingmanagement.domain.model.commands.DeleteBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.queries.*;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingCommandService;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.CreateBookingResource;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform.BookingResourceAssembler;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform.CreateBookingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Booking", description = "Booking API")
public class BookingController {

    private final BookingQueryService bookingQueryService;
    private final BookingCommandService bookingCommandService;

    public BookingController(BookingQueryService bookingQueryService, BookingCommandService bookingCommandService) {
        this.bookingQueryService = bookingQueryService;
        this.bookingCommandService = bookingCommandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResource> getBookingById(@PathVariable Long id) {
        return bookingQueryService.handle(new GetBookingByIdQuery(id))
                .map(BookingResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingResource>> getBookingsByCustomerId(@PathVariable Long customerId) {
        List<BookingResource> resources = bookingQueryService.handle(new GetBookingsByCustomerIdQuery(customerId))
                .stream()
                .map(BookingResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<BookingResource>> getBookingsByServiceId(@PathVariable Long serviceId) {
        List<BookingResource> resources = bookingQueryService.handle(new GetBookingsByServiceIdQuery(serviceId))
                .stream()
                .map(BookingResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<BookingResource>> getBookingsByCompanyId(@PathVariable Long companyId) {
        List<BookingResource> resources = bookingQueryService.handle(new GetBookingsByCompanyIdQuery(companyId))
                .stream()
                .map(BookingResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }



    @GetMapping
    public ResponseEntity<List<BookingResource>> getAllBookings() {
        List<BookingResource> resources = bookingQueryService.handle(new GetAllBookingsQuery())
                .stream()
                .map(BookingResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<BookingResource> createBooking(@RequestBody CreateBookingResource resource) {
        var command = CreateBookingCommandFromResourceAssembler.toCommand(resource);
        var reservation = bookingCommandService.handle(command);
        return reservation.map(r -> ResponseEntity.ok(BookingResourceAssembler.toResource(r)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingCommandService.handle(new DeleteBookingCommand(id));
        return ResponseEntity.noContent().build();
    }
}