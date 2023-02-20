package gmbs.model.players;

import gmbs.model.CardHand;

public class User implements Player {

    private final String name;
    private final CardHand cardHand;

    public User(final String name, final CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    @Override
    public User draw(final CardHand drawCards) {
       return new User(this.name, CardHand.from(this.cardHand, drawCards));
    }

    @Override
    public CardHand getCardHand() {
        return cardHand;
    }

    @Override
    public String getName() {
        return name;
    }
}
