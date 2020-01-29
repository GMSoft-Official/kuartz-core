package com.kuartz.core.common.model;

import com.kuartz.core.common.domain.KzPageable;

import java.io.Serializable;

public class KuartzQueryModel implements Serializable {
    private KzPageable pageable;

    public KuartzQueryModel(KzPageable pageable) {
        this.pageable = pageable;
    }

    public KzPageable getPageable() {
        return pageable;
    }

    public void setPageable(KzPageable pageable) {
        this.pageable = pageable;
    }
}
