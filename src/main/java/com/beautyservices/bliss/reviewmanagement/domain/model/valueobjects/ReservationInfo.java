package com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ReservationInfo {
    private Long serviceId;
    private Long companyId;
 //   private Long reservationId;

    public ReservationInfo(Long serviceId, Long companyId) {
        this.serviceId = serviceId;
        this.companyId = companyId;
    }

    public ReservationInfo() {}


}