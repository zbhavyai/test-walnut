package com.hellowalnut.assessment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Wraps a boolean to be presented
 */
@Getter
@Setter
@AllArgsConstructor
public class SuccessResponse implements Response {
    /**
     * Success message
     */
    private boolean success;
}
