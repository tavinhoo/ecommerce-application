package com.ecommerce.productmanagement.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ProductDTO(@NotBlank String name, @NotBlank String description, @NotNull Double price, @NotBlank Boolean available) {
}
