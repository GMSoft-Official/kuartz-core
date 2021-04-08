package com.kuartz.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Kutay Celebi
 * @since 4.02.2021 01:34
 */
@Data
@SuperBuilder
@AllArgsConstructor
public class KuartzResponseModel<T> {
    @Builder.Default
    private Boolean success = true;
    private T       data;

}
