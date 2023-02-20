package gmbs.model.players;

import gmbs.model.first_class_collection.CardHand;
import gmbs.model.vo.UserName;

public class User implements Player {

    private final UserName name;
    private final CardHand cardHand;

    public User(final UserName name, final CardHand cardHand) {
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
        return name.getName();
    }
}
