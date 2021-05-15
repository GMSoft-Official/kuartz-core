package com.kuartz.core.common;

import org.springframework.context.support.ResourceBundleMessageSource;

public class KuartzMessageSource extends ResourceBundleMessageSource {

    public KuartzMessageSource(String... baseName) {
        setBasenames(baseName);
    }

    public KuartzMessageSource() {
        //    bos yapici
    }
}
