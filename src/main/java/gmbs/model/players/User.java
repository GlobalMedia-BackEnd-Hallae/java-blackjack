package gmbs.model.players;

import gmbs.model.Card;
import gmbs.model.CardHand;

public class User implements Player {

    private final String name;
    private final CardHand cardHand;

    public User(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public String getName() {
        return name;
    }

    @Override
    public User hit(Card card) {
       return new User(this.name, CardHand.from(this.cardHand, card));
    }

    @Override
    public CardHand getCardHand() {
        return cardHand;
    }
}
