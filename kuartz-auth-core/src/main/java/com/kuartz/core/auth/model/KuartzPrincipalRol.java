package com.kuartz.core.auth.model;

import com.kuartz.core.auth.KuartzPrincipalRole;

import java.util.Objects;

/**
 * @author Kutay Celebi
 * @since 10.12.2020 02:08
 */
public class KuartzPrincipalRol {
    private String kod;

    public KuartzPrincipalRol() {
    }

    public KuartzPrincipalRol(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        KuartzPrincipalRol that = (KuartzPrincipalRol) o;
        return kod.equals(that.kod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kod);
    }
}
