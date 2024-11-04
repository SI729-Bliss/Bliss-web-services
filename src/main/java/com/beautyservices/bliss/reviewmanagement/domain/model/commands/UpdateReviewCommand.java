// src/main/java/com/beautyservices/bliss/reviewmanagement/domain/model/commands/UpdateReviewCommand.java
package com.beautyservices.bliss.reviewmanagement.domain.model.commands;

import com.beautyservices.bliss.reviewmanagement.domain.model.valueobjects.Punctuation;

public record UpdateReviewCommand(Long id, Punctuation punctuation, String comment, String images) {
}
