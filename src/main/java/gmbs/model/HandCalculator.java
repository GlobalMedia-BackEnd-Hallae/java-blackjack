package gmbs.model;


import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.players.Player;
import gmbs.model.players.User;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class HandCalculator {

    private static final int BLACK_JACK = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int SMALLER_ACE_VALUE = BlackJackValue.ACE.value();
    private static final int BIGGER_ACE_VALUE = BlackJackValue.ACE.alternativeValue();

    public boolean canHit(Player player) {
        int sumHand = sumHand(player);
        if (isUser(player)) {
            return canUserHit(sumHand);
        }
        return canDealerHit(sumHand);
    }

    private boolean isUser(Player player) {
        return player.getClass() == User.class;
    }

    private boolean canUserHit(int cardSum) {
        return cardSum < BLACK_JACK;
    }

    private boolean canDealerHit(int cardSum) {
        return cardSum <= DEALER_HIT_THRESHOLD;
    }

    public int sumHand(CardHand hand) {
        if (hand.aceCount() > 0) {
            return sumHandWithAce(hand);
        }
        return sumNoAceHand(hand);
    }

    public int sumHand(Player player) {
        CardHand hand = player.getCardHand();
        if (hand.aceCount() > 0) {
            return sumHandWithAce(hand);
        }
        return sumNoAceHand(hand);
    }


    private int sumNoAceHand(CardHand hand) {
        return hand.getCards()
                .stream()
                .map(Card::getNumberValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int sumHandWithAce(CardHand hand) {
        int sumWithoutAce = sumWithoutAce(hand);
        List<Integer> possibleAceSum = possibleSumAce(hand.aceCount());
        return sumWithAce(sumWithoutAce, possibleAceSum);
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

    private int sumWithAce(int sumWithoutAce, List<Integer> possibleAceSum) {
        OptionalInt noBustMax = getNoBustMax(sumWithoutAce, possibleAceSum);
        OptionalInt bustMin = getBustMin(sumWithoutAce, possibleAceSum);
        if (noBustMax.isPresent()) {
            return noBustMax.getAsInt();
        }
        if (bustMin.isPresent()) {
            return bustMin.getAsInt();
        }
        throw new IllegalArgumentException("[err] no sum value");
    }

    private OptionalInt getBustMin(int sumWithoutAce, List<Integer> possibleAceSum) {
        return possibleAceSum.stream()
                .map(sumAce -> sumAce + sumWithoutAce)
                .filter(this::isBustValue)
                .mapToInt(Integer::intValue)
                .min();
    }

    private OptionalInt getNoBustMax(int sumWithoutAce, List<Integer> possibleAceSum) {
        return possibleAceSum.stream()
                .map(sumAce -> sumAce + sumWithoutAce)
                .filter(sum -> !isBustValue(sum))
                .mapToInt(Integer::intValue)
                .max();
    }

    private boolean isBustValue(int sumValue) {
        return sumValue > BLACK_JACK;
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
