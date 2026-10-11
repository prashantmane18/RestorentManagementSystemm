package com.restorentInfo.dto;

public record RestorentDTO(
    String id,
    String name,
    String address,
    String bestDish,
    double rating
) {
}
