package com.seoultech.sanEseo.district;

class District {
    private Long id;
    private final String name;

    public District(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
