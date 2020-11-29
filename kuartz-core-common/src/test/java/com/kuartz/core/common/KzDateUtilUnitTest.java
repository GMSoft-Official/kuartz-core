package com.kuartz.core.common;

import com.kuartz.core.common.util.KzDateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

/**
 * @author Kutay Celebi
 * @since 28.10.2020 16:05
 */
@ExtendWith(SpringExtension.class)
@DisplayName("KZ Date Util Unit Test")
@EnableAutoConfiguration
@ActiveProfiles("unit-test")
public class KzDateUtilUnitTest {

    @Test
    @Tag("fast")
    @DisplayName("Aydan gun cikar ve ilk günü getir")
    public void substractMonthFirstDayTest() {
        final Date now = KzDateUtil.now();
        Assert.assertNotNull(now);

        final Date substracted = KzDateUtil.substractMonthFirstDay(now, 2);
        final int day = KzDateUtil.getDay(substracted);
        Assert.assertEquals(1, day);
    }
}
