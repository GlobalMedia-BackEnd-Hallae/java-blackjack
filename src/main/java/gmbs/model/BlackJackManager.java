package gmbs.model;

import gmbs.model.players.Dealer;
import gmbs.model.players.Player;
import gmbs.model.players.User;

public class BlackJackManager {

    private static final int DRAW_COUNT = 1;
    private static final int START_HAND_COUNT = 2;
    private final HandCalculator calculator = new HandCalculator();
    private final Deck deck;

    public BlackJackManager(final Deck deck) {
        this.deck = deck;
    }

    public Player hit(final Player player) {
        validateCanHit(player);
        CardHand drawCard = deck.draw(DRAW_COUNT);
        return player.draw(drawCard);
    }

    private void validateCanHit(final Player player) {
        if(!calculator.canHit(player)) {
            throw new IllegalArgumentException("[err] can't hit");
        }
    }

    public Dealer createDealer() {
        return new Dealer(drawFirstHand());
    }

    public User createUser(final String userName) {
        return new User(userName, drawFirstHand());
    }

    private CardHand drawFirstHand() {
        return deck.draw(START_HAND_COUNT);
    }

}
