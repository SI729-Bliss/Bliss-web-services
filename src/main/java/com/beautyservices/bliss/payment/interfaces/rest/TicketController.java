package com.beautyservices.bliss.payment.interfaces.rest;


import com.beautyservices.bliss.payment.domain.model.queries.GetTicketByIdQuery;
import com.beautyservices.bliss.payment.domain.services.TicketCommandService;
import com.beautyservices.bliss.payment.domain.services.TicketQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.TicketRepository;
import com.beautyservices.bliss.payment.interfaces.rest.resources.CreateTicketResource;
import com.beautyservices.bliss.payment.interfaces.rest.resources.TicketResource;
import com.beautyservices.bliss.payment.interfaces.rest.transform.CreateTicketCommandFromResourceAssembler;
import com.beautyservices.bliss.payment.interfaces.rest.transform.TicketResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/payments/tickets")
@Tag(name = "Ticket", description = "Ticket endpoints")
public class TicketController {
    private final TicketCommandService ticketCommandService;
    private final TicketQueryService ticketQueryService;

    private final TicketRepository ticketRepository;

    public TicketController(TicketCommandService ticketCommandService, TicketQueryService ticketQueryService, TicketRepository ticketRepository) {
        this.ticketCommandService = ticketCommandService;
        this.ticketQueryService = ticketQueryService;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping
    public ResponseEntity<TicketResource> createTicket(@RequestBody CreateTicketResource resource) {
        var createTicketCommand = CreateTicketCommandFromResourceAssembler.toCommandFromResource(resource.reservationId());
        var ticketId = ticketCommandService.handle(createTicketCommand);
        if(ticketId == 0L) return ResponseEntity.badRequest().build();
        var getTicketByIdQuery = new GetTicketByIdQuery(ticketId);
        var ticket = ticketQueryService.handle(getTicketByIdQuery);
        if(ticket.isEmpty()) return ResponseEntity.badRequest().build();
        var ticketResource =  TicketResourceFromEntityAssembler.toResourceFromEntity(ticket.get());
        return ResponseEntity.ok(ticketResource);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResource> getTicket(@RequestParam Long ticketId) {
        var getTicketByIdQuery = new GetTicketByIdQuery(ticketId);
        var ticket = ticketQueryService.handle(getTicketByIdQuery);
        if(ticket.isEmpty()) return ResponseEntity.notFound().build();
        var ticketResource =  TicketResourceFromEntityAssembler.toResourceFromEntity(ticket.get());
        return ResponseEntity.ok(ticketResource);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicket(@RequestParam Long ticketId) {
        var ticket = ticketRepository.findById(ticketId);
        if(ticket.isEmpty()) return ResponseEntity.notFound().build();
        ticketRepository.deleteById(ticketId);

        return ResponseEntity.ok("Ticket with given id successfully deleted.");

    }
}
