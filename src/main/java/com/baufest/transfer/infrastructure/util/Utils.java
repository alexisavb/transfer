package com.baufest.transfer.infrastructure.util;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Utils {
    public static String parseCamelCaseToSnakeCase(String camelCaseString) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camelCaseString);
    }
}
