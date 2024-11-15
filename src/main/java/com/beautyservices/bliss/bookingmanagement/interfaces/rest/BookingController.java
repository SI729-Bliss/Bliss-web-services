package com.beautyservices.bliss.bookingmanagement.interfaces.rest;

import com.beautyservices.bliss.bookingmanagement.domain.services.BookingCommandService;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.CreateBookingResource;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;
import com.beautyservices.bliss.bookingmanagement.resources.CreateBookingCommandFromResourceAssembler;
import com.beautyservices.bliss.bookingmanagement.resources.UpdateBookingCommandFromResourceAssembler;
import com.beautyservices.bliss.bookingmanagement.resources.BookingResourceFromEntityAssembler;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.DeleteBookingCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given id must not be null");
        }
        var command = updateBookingCommandFromResourceAssembler.toCommand(id, resource);
        var updatedBooking = bookingCommandService.handle(command)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        var bookingResource = BookingResourceFromEntityAssembler.toResourceFromEntity(updatedBooking);
        return ResponseEntity.ok(bookingResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            bookingCommandService.handle(new DeleteBookingCommand(id));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}