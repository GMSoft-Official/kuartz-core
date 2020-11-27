package com.kuartz.core.common.enumaration;

/**
 * Exception kodlarinin yonetilecegi enumlarin interface sinifidir. Exceptionlarin birer kodunun olması ve mukerrer hata kodlarinin onune
 * gecmek icin exceptionlar bir enumun altinda toplanmasi icin yapilmistir.
 */
public interface ExceptionCode {
    String exceptionCode();

    String label();
}
