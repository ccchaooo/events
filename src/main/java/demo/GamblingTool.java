package demo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dengchao
 * @date 2019/8/27 23:44
 */
public final class GamblingTool {

    private Set<String> equalProbabilityEventsGroup;

    public static class Builder {
        private Set<String> equalProbabilityEvents = new HashSet<>(2);


        public GamblingTool.Builder addEvent(String name) {
            equalProbabilityEvents.add(name);
            return this;
        }

        public GamblingTool build() {
            return new GamblingTool(this);
        }
    }

    private GamblingTool(GamblingTool.Builder builder) {
        equalProbabilityEventsGroup = builder.equalProbabilityEvents;
    }

    public String roll() {
        int rn = Math.abs(new Random().nextInt()) % (equalProbabilityEventsGroup.size());
        int i = 0;
        for (String e : equalProbabilityEventsGroup) {
            if (i == rn) {
                return e;
            }
            i++;
        }
        return null;
    }

    public Set<String> getEvents() {
        return equalProbabilityEventsGroup;
    }

    public float rollAndEqual(String eventName) {
        if (equalProbabilityEventsGroup.contains(eventName)) {
            return (float) 1 / equalProbabilityEventsGroup.size();
        } else {
            throw new RuntimeException("Specified value is out of Boundary");
        }
    }


    public float rollAndMatchSpecifiedSequence(List<String> list) {
        Set<String> outOfBoundary = list.stream().filter(str -> !equalProbabilityEventsGroup.contains(str))
                .collect(Collectors.toSet());
        if (outOfBoundary.size() > 0) {
            throw new RuntimeException("Specified value is out of boundary");
        }
        return (float) ((float) 1 / Math.pow((double) equalProbabilityEventsGroup.size(), (double) list.size()));
    }
}
