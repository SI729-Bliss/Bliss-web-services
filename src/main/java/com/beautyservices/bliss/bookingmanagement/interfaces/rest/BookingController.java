package com.beautyservices.bliss.bookingmanagement.interfaces.rest;

import com.beautyservices.bliss.bookingmanagement.domain.model.commands.CreateBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.commands.DeleteBookingCommand;
import com.beautyservices.bliss.bookingmanagement.domain.model.queries.*;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingCommandService;
import com.beautyservices.bliss.bookingmanagement.domain.services.BookingQueryService;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.BookingResource;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.resources.CreateBookingResource;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform.BookingResourceFromEntityAssembler;
import com.beautyservices.bliss.bookingmanagement.interfaces.rest.transform.CreateBookingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
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

    @Operation(summary = "Get bookings by customer ID", description = "Returns all bookings for a specific customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found"),
            @ApiResponse(responseCode = "404", description = "Bookings not found")
    })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingResource>> getBookingsByCustomerId(
            @Parameter(description = "ID of the customer whose bookings are to be retrieved") @PathVariable Long customerId) {
        List<BookingResource> resources = bookingQueryService.handle(new GetReservationsByCustomerIdQuery(customerId))
                .stream()
                .map(BookingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get bookings by service ID", description = "Returns all bookings for a specific service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found"),
            @ApiResponse(responseCode = "404", description = "Bookings not found")
    })
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<BookingResource>> getBookingsByServiceId(
            @Parameter(description = "ID of the service whose bookings are to be retrieved") @PathVariable Long serviceId) {
        List<BookingResource> resources = bookingQueryService.handle(new GetReservationsByServiceIdQuery(serviceId))
                .stream()
                .map(BookingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get bookings by company ID", description = "Returns all bookings for a specific company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found"),
            @ApiResponse(responseCode = "404", description = "Bookings not found")
    })
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<BookingResource>> getBookingsByCompanyId(
            @Parameter(description = "ID of the company whose bookings are to be retrieved") @PathVariable Long companyId) {
        List<BookingResource> resources = bookingQueryService.handle(new GetReservationsByCompanyIdQuery(companyId))
                .stream()
                .map(BookingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get all bookings", description = "Returns all bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found"),
            @ApiResponse(responseCode = "404", description = "Bookings not found")
    })
    @GetMapping
    public ResponseEntity<List<BookingResource>> getAllBookings() {
        List<BookingResource> resources = bookingQueryService.handle(new GetAllReservationsQuery())
                .stream()
                .map(BookingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Create a new booking", description = "Creates a new booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<BookingResource> createBooking (@RequestBody CreateBookingResource resource) {
        var createBookingCommand = CreateBookingCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var bookingId = this.bookingCommandService.handle(createBookingCommand);

        if (bookingId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }
        var getReservationByIdQuery = new GetReservationByIdQuery(bookingId);
        var optionalReservation = this.bookingQueryService.handle(getReservationByIdQuery);

        var bookingResource = BookingResourceFromEntityAssembler.toResourceFromEntity(optionalReservation.get());
        return   new ResponseEntity<>(bookingResource, HttpStatus.CREATED);
    }




    @Operation(summary = "Delete a booking by ID", description = "Deletes a booking by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Booking deleted"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(
            @Parameter(description = "ID of the booking to be deleted") @PathVariable Long id) {
        bookingCommandService.handle(new DeleteBookingCommand(id));
        return ResponseEntity.noContent().build();
    }
}

