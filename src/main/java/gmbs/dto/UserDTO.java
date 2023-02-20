package gmbs.dto;

import gmbs.model.Card;
import gmbs.model.players.Player;

import java.util.stream.Collectors;

public class UserDTO implements PlayerDTO {

    private final String cards;
    private final String name;

    private UserDTO(final Player player) {
        this.cards = player.getCardHand()
                .getCards()
                .stream()
                .map(Card::getName)
                .collect(Collectors.joining());
        this.name = player.getName();
    }

    public static UserDTO of(final Player player) {
        return new UserDTO(player);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCards() {
        return cards;
    }
}
