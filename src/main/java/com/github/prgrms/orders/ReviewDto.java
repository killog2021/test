package com.github.prgrms.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ReviewDto {

  private final Long seq;

  private final Long userSeq;

  private final Long productSeq;

  private String content;

  private final LocalDateTime createAt;

  public ReviewDto(ReviewDto source) { 
   copyProperties(source, this);
   this.content = source.getContent().orElse(null);
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
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("userSeq", userSeq)
      .append("productSeq", productSeq)
      .append("content", content)
      .append("createAt", createAt)
      .toString();
  }


}