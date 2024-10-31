package com.beautyservices.bliss.profilemanagement.interfaces.rest;

import com.beautyservices.bliss.profilemanagement.domain.services.BookingCommandService;
import com.beautyservices.bliss.profilemanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.CreateBookingResource;
import com.beautyservices.bliss.profilemanagement.interfaces.rest.resources.BookingResource;
import com.beautyservices.bliss.profilemanagement.resources.CreateBookingCommandFromResourceAssembler;
import com.beautyservices.bliss.profilemanagement.resources.UpdateBookingCommandFromResourceAssembler;
import com.beautyservices.bliss.profilemanagement.resources.BookingResourceFromEntityAssembler;
import com.beautyservices.bliss.profilemanagement.domain.model.commands.DeleteBookingCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bookings", description = "Booking Management Endpoints")
public class BookingController {

    private final BookingQueryService bookingQueryService;
    private final BookingCommandService bookingCommandService;
    private final CreateBookingCommandFromResourceAssembler createBookingCommandFromResourceAssembler;
    private final UpdateBookingCommandFromResourceAssembler updateBookingCommandFromResourceAssembler;

    public BookingController(BookingQueryService bookingQueryService,
                             BookingCommandService bookingCommandService,
                             CreateBookingCommandFromResourceAssembler createBookingCommandFromResourceAssembler,
                             UpdateBookingCommandFromResourceAssembler updateBookingCommandFromResourceAssembler) {
        this.bookingQueryService = bookingQueryService;
        this.bookingCommandService = bookingCommandService;
        this.createBookingCommandFromResourceAssembler = createBookingCommandFromResourceAssembler;
        this.updateBookingCommandFromResourceAssembler = updateBookingCommandFromResourceAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResource> getBookingById(@PathVariable Long id) {
        var booking = bookingQueryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        var bookingResource = BookingResourceFromEntityAssembler.toResourceFromEntity(booking);
        return ResponseEntity.ok(bookingResource);
    }

    @PostMapping
    public ResponseEntity<BookingResource> createBooking(@RequestBody CreateBookingResource resource) {
        var command = createBookingCommandFromResourceAssembler.toCommand(resource);
        var createdBooking = bookingCommandService.handle(command);
        var bookingResource = BookingResourceFromEntityAssembler.toResourceFromEntity(createdBooking);
        return ResponseEntity.ok(bookingResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResource> updateBooking(@PathVariable Long id, @RequestBody BookingResource resource) {
        var command = updateBookingCommandFromResourceAssembler.toCommand(id, resource);
        var updatedBooking = bookingCommandService.handle(command)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        var bookingResource = BookingResourceFromEntityAssembler.toResourceFromEntity(updatedBooking);
        return ResponseEntity.ok(bookingResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingCommandService.handle(new DeleteBookingCommand(id));
        return ResponseEntity.noContent().build();
    }
}