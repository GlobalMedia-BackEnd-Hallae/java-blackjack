package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.vo.Card;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CardGenerator {

    public List<Card> createWholeCards() {
        return Arrays.stream(CardSuits.values())
                .map(this::createByShape)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Card> createByShape(final CardSuits shape) {
        return Arrays.stream(BlackJackValue.values())
                .map(cardValue -> new Card(shape, cardValue))
                .collect(Collectors.toUnmodifiableList());
    }
}
