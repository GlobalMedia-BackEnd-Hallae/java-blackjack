package gmbs.model.players;

import gmbs.model.Card;
import gmbs.model.CardHand;

public class Dealer implements Player {

    private final CardHand cards;

    public Dealer(CardHand cards) {
        this.cards = cards;
    }

    @Override
    public Dealer hit(Card card) {
        return new Dealer(CardHand.from(this.cards, card));
    }

    @Override
    public CardHand getCardHand() {
        return cards;
    }
}
