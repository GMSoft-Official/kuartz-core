package com.kuartz.core.feign.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("kuartz.client")
public class KuartzApiProperty {

    private Map<String, ClientProperty> clients = new HashMap<>();

    public Map<String, ClientProperty> getClients() {
        return clients;
    }

    public void setClients(Map<String, ClientProperty> clients) {
        this.clients = clients;
    }

    public class ClientProperty {
        private String url;

        private Map<String, KuartzServiceProperty> clients;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, KuartzServiceProperty> getClients() {
            return clients;
        }

        public void setClients(Map<String, KuartzServiceProperty> clients) {
            this.clients = clients;
        }
    }

    public class KuartzServiceProperty {
        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
