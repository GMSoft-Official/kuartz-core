package com.kuartz.core.common.security;

import java.util.Map;
import java.util.Set;

/**
 * @author Kutay Celebi
 * @since 9.12.2020 02:20
 */
public class KuartzPrincipalModel implements KuartzPrincipal {
    private Long kullaniciId;

    private String kullaniciAdi;

    private Set<KuartzPrincipalRol> rolList;

    private Set<KuartzPrincipalYetki> yetkiList;

    private Map<String, Object> additionalInfo;

    public KuartzPrincipalModel() {
    }

    public KuartzPrincipalModel(String kullaniciAdi, Set<KuartzPrincipalRol> rolList, Set<KuartzPrincipalYetki> yetkiList) {
        this.kullaniciAdi = kullaniciAdi;
        this.rolList      = rolList;
        this.yetkiList    = yetkiList;
    }

    @Override
    public Long getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(Long kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    @Override
    public Object additionalInfo() {
        return additionalInfo;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public Set<KuartzPrincipalRol> getRolList() {
        return rolList;
    }

    public void setRolList(Set<KuartzPrincipalRol> rolList) {
        this.rolList = rolList;
    }

    public Set<KuartzPrincipalYetki> getYetkiList() {
        return yetkiList;
    }

    public void setYetkiList(Set<KuartzPrincipalYetki> yetkiList) {
        this.yetkiList = yetkiList;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
