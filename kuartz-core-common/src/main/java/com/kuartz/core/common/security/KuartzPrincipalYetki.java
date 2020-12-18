package com.kuartz.core.common.security;

import java.util.Objects;

/**
 * @author Kutay Celebi
 * @since 10.12.2020 02:09
 */
public class KuartzPrincipalYetki implements KuartzPrincipalPrivilege{

    private String kod;

    public KuartzPrincipalYetki() {
    }

    public KuartzPrincipalYetki(String kod) {
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
        KuartzPrincipalYetki that = (KuartzPrincipalYetki) o;
        return kod.equals(that.kod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kod);
    }
}
