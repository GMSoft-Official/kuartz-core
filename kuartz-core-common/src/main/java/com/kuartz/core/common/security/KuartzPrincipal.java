package com.kuartz.core.common.security;

import java.util.Collection;

/**
 * @author Kutay Celebi
 * @since 11.12.2020 17:17
 */
public interface KuartzPrincipal {

    String getKullaniciAdi();

    <P extends KuartzPrincipalPrivilege> Collection<P> getYetkiList();

    <R extends KuartzPrincipalRole> Collection<R> getRolList();

    Object additionalInfo();
}
