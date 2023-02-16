package gmbs.model.players;

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
    public User draw(CardHand drawCards) {
       return new User(this.name, CardHand.from(this.cardHand, drawCards));
    }

    @Override
    public CardHand getCardHand() {
        return cardHand;
    }
}
