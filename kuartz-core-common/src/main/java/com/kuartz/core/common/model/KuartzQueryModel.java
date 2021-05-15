package com.kuartz.core.common.model;

import com.kuartz.core.common.domain.KzPageable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
public class KuartzQueryModel implements Serializable {
    private static final long serialVersionUID = 6803637047885938752L;
    @Builder.Default
    protected Boolean    deleted  = Boolean.FALSE;
    @Builder.Default
    protected KzPageable pageable = new KzPageable(0, 10);
}
