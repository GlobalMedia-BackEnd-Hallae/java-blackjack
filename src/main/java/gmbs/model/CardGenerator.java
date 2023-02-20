package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.vo.Card;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CardGenerator {

    public List<Card> createWholeCards() {
        return Arrays.stream(CardSuits.values())
                .map(this::createByShape)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Card> createByShape(final CardSuits shape) {
        return Arrays.stream(BlackJackValue.values())
                .map(cardValue -> new Card(shape, cardValue))
                .toList();
    }
}
