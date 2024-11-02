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


    @PostMapping
    public ResponseEntity<PaymentResource> createSowing(@RequestBody CreatePaymentResource createPaymentResource) {
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(createPaymentResource);
        var paymentId = paymentCommandService.handle(createPaymentCommand);
        if(paymentId == 0L) return ResponseEntity.badRequest().build();

        var payment = paymentRepository.findById(paymentId).orElseThrow();

        var paymentResourceCreated = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment);

        return new ResponseEntity<>(paymentResourceCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        var getAllPaymentsQuery = new GetAllPaymentsQuery();
        var payments = paymentQueryService.handle(getAllPaymentsQuery);
        var paymentResource = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentResource);
    }

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
