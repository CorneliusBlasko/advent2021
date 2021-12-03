package com.alberto.advent.day2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement{

    private String direction;
    private long magnitude;
}
