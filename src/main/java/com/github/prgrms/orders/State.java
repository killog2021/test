package com.github.prgrms.orders;

public enum State {

    REQUESTED("REQUESTED"),
    ACCEPTED("ACCEPTED"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED"),
    REJECTED("REJECTED");


    String value;

    State(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

}
