package com.eshopweb.enumType;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-06-08
 */
public enum EnumLtcState {

    LTC_LW("ltc_lw"),
    LTC_4("ltc_4"),
    LTC_8("ltc_8");

    private final String ltc;

    EnumLtcState(String ltc) {
        this.ltc = ltc;
    }

    public String getLtc() {
        return ltc;
    }
}



