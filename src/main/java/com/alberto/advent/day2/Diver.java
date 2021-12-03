package com.alberto.advent.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Diver{

    private static final String FORWARD = "forward";
    private static final String DOWN = "down";
    private static final String UP = "up";

    List<Movement> instructions;

    public Diver() throws IOException{
        this.instructions = this.retrieveMovements();
    }

    public long getFinalPosition() {
        long finalPosition = this.findFinalPosition(false);
        System.out.println("Day two - The final position is: " + finalPosition);
        return finalPosition;
    }

    public long getFinalPositionAimed() {
        long finalPosition = this.findFinalPosition(true);
        System.out.println("Day two - The final position with aim is: " + finalPosition);
        return finalPosition;
    }

    /**
     * Returns the product of multiplying the position (the sum of all forward movement) times the depth (the final
     * magnitude of depth after adding the "down" movements and subtracting the "up" movements). If the isAimed value is
     * true, the aim is used to calculate the depth
     * @return The product of forward times depth
     */
    public long findFinalPosition(Boolean isAimed){
        long depth = 0L;
        long position = 0L;
        long aim = 0L;

        for(Movement instruction : this.instructions) {
            if(instruction.getDirection().equals(FORWARD)) {
                position += instruction.getMagnitude();
                if(isAimed) {
                    depth += aim * instruction.getMagnitude();
                }
            }

            if(instruction.getDirection().equals(DOWN)) {
                if(isAimed){
                    aim += instruction.getMagnitude();
                } else {
                    depth += instruction.getMagnitude();
                }
            }

            if(instruction.getDirection().equals(UP)) {
                if(isAimed){
                    aim -= instruction.getMagnitude();
                } else {
                    depth -= instruction.getMagnitude();
                }
            }
        }

        return position * depth;
    }

    /**
     * Reads the movements.txt file and creates a list of Movements. A movement consists of a direction and the
     * magnitude of the movement.
     * @return A collection of Movements
     * @throws IOException An Exception while parsing the file
     */
    public List<Movement> retrieveMovements() throws IOException{
        List<String> content = Files.readAllLines(Path.of("src/main/resources/movements.txt"));
        List<Movement> instructions = new ArrayList<>();

        for(String line : content) {
            String[] split = line.split("\\s+");
            instructions.add(Movement.builder()
                                     .direction(split[0])
                                     .magnitude(Long.parseLong(split[1]))
                                     .build());
        }

        return instructions;
    }
}
