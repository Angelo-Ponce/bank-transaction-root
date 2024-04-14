package com.neoris.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Base response vo
 * @param <T>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseVo<T> {
    @Builder.Default
    private Integer code = 200;
    private String message;
    private List<String> errors;
    private T data;
}
