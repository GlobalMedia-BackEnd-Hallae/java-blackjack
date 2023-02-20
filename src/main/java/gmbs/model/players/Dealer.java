package gmbs.model.players;

import gmbs.model.CardHand;
import gmbs.model.UserName;

public class Dealer implements Player {

    private static final UserName name = new UserName("dealer");
    private final CardHand cards;

    public Dealer(final CardHand cards) {
        this.cards = cards;
    }

    @Override
    public Dealer draw(final CardHand drawCards) {
        return new Dealer(CardHand.from(this.cards, drawCards));
    }

    @Override
    public CardHand getCardHand() {
        return cards;
    }


    @Override
    public String getName() {
        return name.getName();
    }
}
