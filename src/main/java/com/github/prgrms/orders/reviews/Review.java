package com.github.prgrms.orders.reviews;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Review {

    private final Long seq;

    private final Long userSeq;

    private final Long productSeq;


    private final LocalDateTime createAt;
    private String content;


    public Review(String content) {
        this(null, null, null, content, null);
    }

    public Review(Long seq, Long userSeq, Long productSeq, String content, LocalDateTime createAt) {
        checkArgument(isNotEmpty(content), "content must be provided");
        checkArgument(
                content.length() <= 1000,
                "content length must be less than 1000 characters"
        );

        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.content = content;
        this.createAt = defaultIfNull(createAt, now());
    }

    public void setcontent(String content) {
        checkArgument(isNotEmpty(content), "content must be provided");
        checkArgument(
                content.length() <= 1000,
                "content length must be less than 1000 characters"
        );

        this.content = content;
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

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review Review = (Review) o;
        return Objects.equals(seq, Review.seq);
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
                .append("content", content)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private String content;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(Review Review) {
            this.seq = Review.seq;
            this.userSeq = Review.userSeq;
            this.productSeq = Review.productSeq;
            this.content = Review.content;
            this.createAt = Review.createAt;
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


        public Builder content(String content) {
            this.content = content;
            return this;
        }


        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Review build() {
            return new Review(
                    seq,
                    userSeq,
                    productSeq,
                    content,
                    createAt
            );
        }
    }

}