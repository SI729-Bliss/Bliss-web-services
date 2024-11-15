package com.beautyservices.bliss.payment.interfaces.rest;

import com.beautyservices.bliss.payment.domain.model.queries.GetAllPaymentsQuery;
import com.beautyservices.bliss.payment.domain.model.queries.GetPaymentByIdQuery;
import com.beautyservices.bliss.payment.domain.services.PaymentCommandService;
import com.beautyservices.bliss.payment.domain.services.PaymentQueryService;
import com.beautyservices.bliss.payment.infrastructure.jpa.repositories.PaymentRepository;
import com.beautyservices.bliss.payment.interfaces.rest.resources.CreatePaymentResource;
import com.beautyservices.bliss.payment.interfaces.rest.resources.PaymentResource;
import com.beautyservices.bliss.payment.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.beautyservices.bliss.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
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
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long paymentId) {
        var getPaymentByIdQuery = new GetPaymentByIdQuery(paymentId);
        var optionalPayment = this.paymentQueryService.handle(getPaymentByIdQuery);
        if (optionalPayment.isEmpty())
            return ResponseEntity.badRequest().build();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(optionalPayment.get());
        return ResponseEntity.ok(paymentResource);
    }


}
