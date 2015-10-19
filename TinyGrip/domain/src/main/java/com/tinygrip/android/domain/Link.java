
package com.tinygrip.android.domain;

/**
 * Class that represents a Link in the domain layer.
 */
public class Link {

  private final String href;
  private final boolean templated;

  public Link(String href, boolean templated) {
    this.href = href;
    this.templated = templated;
  }

  public String getHref() {
    return href;
  }

  public boolean isTemplated() {
    return templated;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** Link Entity Details *****\n");
    stringBuilder.append("href=" + this.getHref() + "\n");
    stringBuilder.append("templated=" + this.isTemplated() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}