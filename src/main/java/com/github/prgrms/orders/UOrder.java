package com.github.prgrms.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;


public class UOrder {
    private final Long seq;
    private Long userSeq;
    private Long productSeq;
    private Long reviewSeq;
    private State state;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;
    private String requestMsg;
    private String rejectMsg;

    public UOrder(Long seq, Long userSeq, Long productSeq, Long reviewSeq, State state, String requestMsg, String rejectMsg, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.reviewSeq = reviewSeq;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.rejectedAt = rejectedAt;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UOrder UOrder = (UOrder) o;
        return Objects.equals(seq, UOrder.getSeq());
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userSeq", userSeq)
                .append("productSeq", productSeq)
                .append("content", requestMsg)
                .append("createAt", createAt)
                .toString();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private Long reviewSeq;
        private State state;

        private String requestMsg;
        private String rejectMsg;

        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(UOrder UOrder) {
            this.seq = UOrder.seq;
            this.userSeq = UOrder.userSeq;
            this.productSeq = UOrder.productSeq;
            this.reviewSeq = UOrder.reviewSeq;
            this.state = UOrder.state;
            this.requestMsg = UOrder.requestMsg;
            this.rejectMsg = UOrder.rejectMsg;
            this.rejectedAt = UOrder.rejectedAt;
            this.createAt = UOrder.createAt;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }


        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }


        public Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Builder requestMsg(String requestMsg) {
            this.requestMsg = requestMsg;
            return this;
        }

        public Builder rejectMsg(String rejectMsg) {
            this.rejectMsg = rejectMsg;
            return this;
        }

        public Builder rejectedAt(LocalDateTime rejectedAt) {
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Builder reviewSeq(Long reviewSeq) {
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Builder state(State state) {
            this.state = state;
            return this;
        }

        public UOrder build() {
            return new UOrder(seq, userSeq, productSeq, reviewSeq, state, requestMsg, rejectMsg, rejectedAt, createAt
            );
        }


    }

}