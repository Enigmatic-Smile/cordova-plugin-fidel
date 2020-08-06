package com.fidelcordovalibrary.adapters;

import com.fidel.sdk.Fidel;
import com.fidelcordovalibrary.adapters.abstraction.CountryAdapter;

import java.util.HashMap;
import java.util.Map;

public final class FidelCountryAdapter implements CountryAdapter {

    private static final String EXPORTED_COUNTRY_KEY = "Country";

    private static final String UNITED_KINGDOM_COUNTRY_KEY = "unitedKingdom";
    private static final String UNITED_STATES_COUNTRY_KEY = "unitedStates";
    private static final String JAPAN_COUNTRY_KEY = "japan";
    private static final String SWEDEN_COUNTRY_KEY = "sweden";
    private static final String IRELAND_COUNTRY_KEY = "ireland";
    private static final String CANADA_COUNTRY_KEY = "canada";

    private static final String NOT_FOUND_COUNTRY_KEY = "notFound";

    public String keyFor(Fidel.Country country) {
        switch (country) {
            case UNITED_KINGDOM: return UNITED_KINGDOM_COUNTRY_KEY;
            case UNITED_STATES: return UNITED_STATES_COUNTRY_KEY;
            case JAPAN: return JAPAN_COUNTRY_KEY;
            case SWEDEN: return SWEDEN_COUNTRY_KEY;
            case IRELAND: return IRELAND_COUNTRY_KEY;
            case CANADA: return CANADA_COUNTRY_KEY;
        }
        return NOT_FOUND_COUNTRY_KEY;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        final Map<String, Integer> countriesMap = new HashMap<>();
        for (Fidel.Country country :
                Fidel.Country.values()) {
            String countryKey = keyFor(country);
            countriesMap.put(countryKey, country.ordinal());
        }
        constants.put(EXPORTED_COUNTRY_KEY, countriesMap);
        return constants;
    }

    @Override
    public Fidel.Country countryWithInteger(int integer) {
        if (integer < Fidel.Country.values().length) {
            return Fidel.Country.values()[integer];
        }
        return null;
    }
}
