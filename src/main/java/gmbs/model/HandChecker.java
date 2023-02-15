package gmbs.model;


import gmbs.model.black_jack_enum.BlackJackValue;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class HandChecker {

    private static final int BLACK_JACK = 21;
    private static final int SMALLER_ACE_VALUE = BlackJackValue.ACE.value();
    private static final int BIGGER_ACE_VALUE = BlackJackValue.ACE.alternativeValue();

    public int handSum(CardHand hand) {
        if (hand.aceCount() > 0) {
            return handSumWithAce(hand);
        }
        return handSumNoAce(hand);
    }

    private int handSumNoAce(CardHand hand) {
        return hand.getCards()
                .stream()
                .map(Card::getNumberValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int handSumWithAce(CardHand hand) {
        int sumWithoutAce = sumWithoutAce(hand);
        List<Integer> possibleAceSum = possibleSumAce(hand.aceCount());
        OptionalInt sum = sumWithAce(sumWithoutAce, possibleAceSum);
        if(sum.isPresent()){
            return sum.getAsInt();
        }
        throw new IllegalArgumentException("[err] hand already busted");
    }

    private int sumWithoutAce(CardHand hand) {
        return hand.getCards()
                .stream()
                .filter(card -> !card.getCardValue()
                        .name()
                        .equals("ACE"))
                .map(Card::getNumberValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private OptionalInt sumWithAce(int sumWithoutAce, List<Integer> possibleAceSum) {
        return possibleAceSum.stream()
                .map(sumAce -> sumAce + sumWithoutAce)
                .filter(sum -> sum <= BLACK_JACK)
                .mapToInt(Integer::intValue)
                .max();
    }

    private List<Integer> possibleSumAce(int aceCount) {
        if (aceCount == 1) {
            return List.of(SMALLER_ACE_VALUE, BIGGER_ACE_VALUE);
        }
        List<Integer> previousDegree = possibleSumAce(aceCount - 1);
        return getCurrentDegree(previousDegree);
    }

    private List<Integer> getCurrentDegree(List<Integer> previousDegree) {
        int previousLastIndex = previousDegree.size() - 1;
        int currentLastValue = previousDegree.get(previousLastIndex) + BIGGER_ACE_VALUE;
        List<Integer> currentDegree = new ArrayList<>(previousDegree.stream()
                .map(value -> value + SMALLER_ACE_VALUE)
                .toList());
        currentDegree.add(currentLastValue);
        return currentDegree;
    }
}
