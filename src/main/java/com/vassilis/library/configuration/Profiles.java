package com.vassilis.library.configuration;

public final class Profiles {
    public static final String CB_PROFILE = "cb";
    public static final String MEM_PROFILE = "!" + CB_PROFILE;

    private Profiles() {
        throw new UnsupportedOperationException("No instances allowed");
    }
}