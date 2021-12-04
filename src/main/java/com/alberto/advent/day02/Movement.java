package com.alberto.advent.day02;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Movement {

  private String direction;
  private long magnitude;
}
