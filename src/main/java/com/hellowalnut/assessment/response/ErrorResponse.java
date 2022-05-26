package com.hellowalnut.assessment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Wraps a custom message to be presented during errors
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse implements Response {
    /**
     * Error message
     */
    private String error;
}
