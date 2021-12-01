package com.alberto.advent.dayone;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Counter{

    public Long measure() throws IOException{
        List<Long> measurements = this.parseFile();
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

    private List<Long> parseFile() throws IOException{
        Path fileName = Path.of("src/main/resources/001.txt");
        String content = Files.readString(fileName);
        String[] stringArray = content.split("\\r?\\n");
        List<String> stringList = Arrays.asList(stringArray);
        return stringList.stream().map(Long::valueOf).collect(Collectors.toList());
    }
}
