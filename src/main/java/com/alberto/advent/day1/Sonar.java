package com.alberto.advent.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sonar{

    public Long sweep(List<Long> measurements){
        long larger = 0L;

        for(int i = 1; i < measurements.size(); i++) {
            Long previous = measurements.get(i-1);
            Long next = measurements.get(i);
            boolean isLarger = next > previous;

            if(isLarger) {
                larger++;
            }
        }
        return larger;
    }

    public List<Long> createWindows(List<Long> measurements){
        List<Long> windows = new ArrayList<>();

        for(int i = 0; i < measurements.size(); i++) {
            if(i < (measurements.size() - 2)) {
                windows.add(measurements.get(i) + measurements.get(i+1) + measurements.get(i+2));
            }
        }
        return windows;
    }

    public List<Long> retrieveMeasurements() throws IOException{
        List<String> content = Files.readAllLines(Path.of("src/main/resources/measurements.txt"));
        return content.stream().map(Long::valueOf).collect(Collectors.toList());
    }
}
