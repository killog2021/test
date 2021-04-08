package com.github.prgrms.orders;

public enum State {

    REQUESTED("REQUESTED"),
    ACCEPTED("ACCEPTED"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED"),
    REJECTED("REJECTED");


    String status;

    private State(String completed) {
    }

}
