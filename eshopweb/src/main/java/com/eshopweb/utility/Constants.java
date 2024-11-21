package com.eshopweb.utility;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public class Constants {

	public static final BigDecimal LESS_THEN_CRATE_LTC = BigDecimal.valueOf(4000);
    public static final BigDecimal LESS_THEN_CRATE_LTC_ADD_300_EURO = BigDecimal.valueOf(300);
	public static final double MAX_WEIGHT = 100;
	public static final String US = "US";
	public static final Map<String, String> mapOfStates = ImmutableMap.of(
			"CZ", "Czechia",
			"SK", "Slovakia",
			"A", "Austria",
			"DE", "Germany"
	);

	public static final List<String> listOfStatesCode = new ArrayList<>(mapOfStates.keySet());
	public static final List<String> listOfStatesName = new ArrayList<>(mapOfStates.values());

}
