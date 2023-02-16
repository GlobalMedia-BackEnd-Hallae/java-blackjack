package gmbs.model.players;

import gmbs.model.CardHand;

public class Dealer implements Player {

    private final CardHand cards;

    public Dealer(CardHand cards) {
        this.cards = cards;
    }

    @Override
    public Dealer draw(CardHand drawCards) {
        return new Dealer(CardHand.from(this.cards, drawCards));
    }

    @Override
    public CardHand getCardHand() {
        return cards;
    }
}
