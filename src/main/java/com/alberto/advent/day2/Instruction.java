package com.alberto.advent.day2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instruction{

    private String direction;
    private long amount;
}
