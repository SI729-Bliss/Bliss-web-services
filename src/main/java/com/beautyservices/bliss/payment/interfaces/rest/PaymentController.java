package com.beautyservices.bliss.payment.interfaces.rest;

import com.beautyservices.bliss.payment.domain.model.queries.GetAllPaymentsQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentByIdQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentByReservationIdQuery;
import com.beautyservices.bliss.payment.domain.model.valueobjects.ReservationId;
import com.beautyservices.bliss.payment.domain.services.PaymentCommandService;
import com.beautyservices.bliss.payment.domain.services.PaymentQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentRepository;
import com.beautyservices.bliss.payment.interfaces.rest.resources.CreatePaymentResource;
import com.beautyservices.bliss.payment.interfaces.rest.resources.PaymentResource;
import com.beautyservices.bliss.payment.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.beautyservices.bliss.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PaymentController
 * <p>
 *     This class is the REST controller for the Payment API.
 *     It contains the endpoints for creating and retrieving payments.
 *     The class uses the PaymentCommandService and PaymentQueryService to handle the commands and queries.
 *     <ul>
 *         <li>POST /api/v1/payments/payment/ - Create a payment</li>
 *         <li>GET /api/v1/payments/payment/ - Get all payments</li>
 *         <li>GET /api/v1/payments/payment/{paymentId} - Get a payment by id</li>
 *     </ul>
 * </p>
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/payments/payment/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payment", description = "Payment API")
public class PaymentController {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService, PaymentRepository paymentRepository) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
        this.paymentRepository = paymentRepository;
    }


    /**
     * Create a payment
     * @param createPaymentResource the resource containing data for the payment to be created
     * @return the created payment resource
     * @see CreatePaymentResource
     * @see PaymentResource
     */
    @Operation(summary = "Create a payment",
            description = "Create a payment from database",
            operationId = "createPayment",
            responses={
                @ApiResponse(responseCode = "201",
                        description = "Payment created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                ),
                @ApiResponse(responseCode = "400",
                        description = "Bad request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                )
        }
    )
    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource createPaymentResource) {
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(createPaymentResource);
        var paymentId = this.paymentCommandService.handle(createPaymentCommand);
        if(paymentId == 0L) return ResponseEntity.badRequest().build();

        var payment = paymentRepository.findById(paymentId).orElseThrow();

        var paymentResourceCreated = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment);

        return new ResponseEntity<>(paymentResourceCreated, HttpStatus.CREATED);
    }


    /**
     * Get all payments
     * @return a list of all payments
     * @see PaymentResource
     */
    @Operation(summary = "Get all payments",
            description = "Get all payments from database",
            operationId = "getAllPayments",
            responses={
                @ApiResponse(responseCode = "200",
                        description = "Payments found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                ),
                @ApiResponse(responseCode = "400",
                        description = "Bad request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                )
        }
    )
    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        var getAllPaymentsQuery = new GetAllPaymentsQuery();
        var payments = paymentQueryService.handle(getAllPaymentsQuery);
        var paymentResource = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentResource);
    }

    /**
     * Get a payment by id
     * @param paymentId the id of the payment to retrieve
     * @return the payment resource
     * @see PaymentResource
     */
    @Operation(summary = "Get a payment by id",
            description = "Get a payment by id from database",
            operationId = "getPaymentById",
            responses={
                @ApiResponse(responseCode = "200",
                        description = "Payment found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                ),
                @ApiResponse(responseCode = "400",
                        description = "Bad request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                )
        }
    )
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long paymentId) {
        var getPaymentByIdQuery = new GetPaymentByIdQuery(paymentId);
        var optionalPayment = this.paymentQueryService.handle(getPaymentByIdQuery);
        if (optionalPayment.isEmpty())
            return ResponseEntity.badRequest().build();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(optionalPayment.get());
        return ResponseEntity.ok(paymentResource);
    }

    /**
     * Find payments by reservation id
     * @param reservationId the id of the reservation to retrieve payment
     * @return a payment
     * @see PaymentResource
     */
    @Operation(summary = "Find payments by reservation id",
            description = "Find payments by reservation id from database",
            operationId = "findPaymentsByReservationId",
            responses={
                @ApiResponse(responseCode = "201",
                        description = "Payments found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                ),
                @ApiResponse(responseCode = "400",
                        description = "Bad request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PaymentResource.class)
                        )
                )
        }
    )
    @GetMapping("/payment/{reservationId}")
    public ResponseEntity<PaymentResource> getPaymentByReservationId(@RequestParam(name = "reservationId") Long reservationId) {
        if(reservationId == null) return ResponseEntity.badRequest().build();
        ReservationId id = new ReservationId(reservationId);
        var getPaymentByReservationIdQuery = new GetPaymentByReservationIdQuery(id);
        var optionalPayment = this.paymentQueryService.handle(getPaymentByReservationIdQuery);
        if (optionalPayment.isEmpty())
            return ResponseEntity.badRequest().build();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(optionalPayment.get());
        return ResponseEntity.ok(paymentResource);
    }
}
