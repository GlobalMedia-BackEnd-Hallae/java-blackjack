package gmbs.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDistributor {

    private static final String DECK_IS_EMPTY = "카드가 모두 소요됐습니다.";
    private static final List<Card> CACHE = new ArrayList<>();

    private final Stack<Card> deck = new Stack<>();

    static {
        for (CardPattern cardPattern : CardPattern.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                CACHE.add(new Card(cardNumber, cardPattern));
            }
        }
    }

    public CardDistributor() {
        Collections.shuffle(CACHE);
        deck.addAll(CACHE);
    }

    public Card distribute() {
        if (isEmpty()) {
            throw new IllegalStateException(DECK_IS_EMPTY);
        }
        return deck.pop();
    }

    private boolean isEmpty() {
        return deck.isEmpty();
    }

    @Override
    public String toString() {
        return "CardDistributor{" +
                "deck=" + deck +
                '}';
    }
}
