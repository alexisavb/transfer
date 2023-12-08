package com.baufest.transfer.infrastructure.util;

import java.net.URI;

public class Constants {

    private Constants() {}

    public static final String SOURCE = "Source";
    public static final String TARGET = "Target";
    public static final URI URI_SOURCE_TRANSFER_MS = URI.create("/baufest_core/transfer_api");
    public static final String JSON_CONTENT_TYPE = "application/json";
}
