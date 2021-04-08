package com.github.prgrms.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OrderDto {
    private Long seq;
    private Long userSeq;
    private Long productSeq;
    private Long reviewSeq;
    private State state;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;
    private String requestMsg;
    private String rejectMsg;

    public OrderDto(UOrder source) {
        copyProperties(source, this);

    }

    public OrderDto(Long seq, Long userSeq, Long productSeq, Long reviewSeq, State state, String requestMsg, String rejectMsg, LocalDateTime rejectedAt, LocalDateTime createAt) {
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
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userSeq", userSeq)
                .append("productSeq", productSeq)
                .append("content", requestMsg)
                .append("createAt", createAt)
                .toString();
    }

}
