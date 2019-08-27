package demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author dengchao
 * @date 2019/8/28 00:49
 */
public class GamblingToolTest {
    private GamblingTool coin;
    private GamblingTool dice;

    @Before
    public void setUp() {
        coin = new GamblingTool.Builder()
                .addEvent("0")
                .addEvent("1")
                .build();
        dice = new GamblingTool.Builder()
                .addEvent("1")
                .addEvent("2")
                .addEvent("3")
                .addEvent("4")
                .addEvent("5")
                .addEvent("6")
                .build();
    }

    @Test
    public void roll() {
        assert Stream.of("0", "1").collect(Collectors.toSet()).contains(coin.roll());
        assert Stream.of("1", "2", "3", "4", "5", "6").collect(Collectors.toSet()).contains(dice.roll());
    }

    @Test
    public void getEvents() {
        assert Stream.of("0", "1").collect(Collectors.toSet()).containsAll(coin.getEvents());
        assert Stream.of("1", "2", "3", "4", "5", "6").collect(Collectors.toSet()).containsAll(dice.getEvents());
    }

    @Test
    public void rollAndEqual() {
        assert (float) 1 / 2 == coin.rollAndEqual("1");
        assert (float) 1 / 6 == dice.rollAndEqual("3");
    }

    @Test
    public void rollAndMatchSpecifiedSequence() {
        List<String> coinSequence = Stream.of("1", "1", "1", "0", "0").collect(Collectors.toList());
        assert (float) 1 / (float) Math.pow(2, 5) == coin.rollAndMatchSpecifiedSequence(coinSequence);
        List<String> diceSequence = Stream.of("1", "3", "5").collect(Collectors.toList());
        assert (float) 1 / (float) Math.pow(6, 3) == dice.rollAndMatchSpecifiedSequence(diceSequence);
    }
}