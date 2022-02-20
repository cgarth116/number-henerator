package com.irbish.numberhenerator.repositories;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.irbish.numberhenerator.additional.DefaultString.REGION_116RUS;

public class NumberBaseImpl implements NumberBase{

    private final HashMap<String, Set<String>> numberBase;

    public NumberBaseImpl(){
        numberBase = new HashMap<String, Set<String>>() {{
            put(REGION_116RUS, new LinkedHashSet<>());
        }};
    }

    @Override
    public Set<String> getRegionBase(String region) {
        return numberBase.get(region);
    }

    @Override
    public Boolean isRegion(String region) {
        return numberBase.containsKey(region);
    }
}
