package com.kuartz.env.configuration.properties;

import java.util.List;

//@ConfigurationProperties("kuartz.message") todo daha sonra kullanilacak.
public class KuartzMessageProperty {
    private List<String> bundlePaths;

    public KuartzMessageProperty(List<String> bundlePaths) {
        this.bundlePaths = bundlePaths;
    }

    public List<String> getBundlePaths() {
        return bundlePaths;
    }

    public void setBundlePaths(List<String> bundlePaths) {
        this.bundlePaths = bundlePaths;
    }
}
