package com.example.restaurant.projection;

import java.util.UUID;

public interface MenuProjection {

    UUID getId();

    String getProductName();

    Long getPrice();

    UUID getAttachmentId();

}
