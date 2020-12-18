package com.kuartz.core.auth.security;




import com.kuartz.core.common.security.KuartzPrincipalModel;
import com.kuartz.core.common.security.KuartzPrincipalRol;
import com.kuartz.core.common.security.KuartzPrincipalYetki;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class KuartzSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    public KuartzSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    /**
     * User yetkisi ile checkYetkiye verilen yetkiler kiyaslanir. Eger methot'a verilen yetki kullanicidaki yetki ile basliyorsa true
     * doner.
     * <p/>
     * <p/>
     * <code>
     * Orn:
     * <p/>
     * Methot yetkisi = a_b_c_d
     * <p/>
     * Kullanici Yetkisi = a_b
     * <p>
     * <p/>
     * kullanici yetkilendirilir.
     *
     * </code>
     *
     * @param privileges {@link String}
     * @return boolean
     */
    public boolean checkYetki(String... privileges) {
        KuartzPrincipalModel principal = (KuartzPrincipalModel) authentication.getPrincipal();
        final Collection<KuartzPrincipalYetki> yetkiList = principal.getYetkiList();

        if (yetkiList == null) {
            return false;
        }

        for (KuartzPrincipalYetki userAuth : yetkiList) {
            for (String privilege : privileges) {
                if (privilege.startsWith(userAuth.getKod())) {
                    return true;
                }
            }
        }
        return false;
    }

    // todo javadoc.
    public boolean checkRol(String... roles) {
        KuartzPrincipalModel principal = (KuartzPrincipalModel) authentication.getPrincipal();
        Collection<KuartzPrincipalRol> userRoles = principal.getRolList();

        for (KuartzPrincipalRol userRole : userRoles) {
            for (String role : roles) {
                if (role.equals(userRole.getKod())) {
                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}
