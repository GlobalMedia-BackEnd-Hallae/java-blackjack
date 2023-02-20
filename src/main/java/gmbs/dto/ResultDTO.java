package gmbs.dto;

import gmbs.model.vo.BlackJackResult;
import gmbs.model.vo.Card;
import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Player;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultDTO {

    private final String name;
    private final String cards;
    private final String result;
    private final int cardSum;

    public ResultDTO(final Player player, final BlackJackResult result) {
        this.name = player.getName();
        this.cards = extractCards(player);
        this.result = extractResults(result);
        this.cardSum = result.getResultHandSum();
    }

    private String extractResults(final BlackJackResult result) {
        if (hasSingleResult(result)) {
            return extractSingleResult(result);
        }
        return extractMultipleResult(result);
    }

    private boolean hasSingleResult(final BlackJackResult result) {
        return result.getResultsData()
                .entrySet()
                .stream()
                .filter(a -> a.getValue() > 0)
                .toList()
                .size() == 1;
    }

    private static String extractSingleResult(final BlackJackResult result) {
        return result.getResultsData()
                .entrySet()
                .stream()
                .filter(a -> a.getValue() > 0)
                .map(entry -> entry.getKey().getDescription())
                .collect(Collectors.joining());
    }

    private static String extractMultipleResult(final BlackJackResult result) {
        return result.getResultsData()
                .entrySet()
                .stream()
                .filter(a -> a.getValue() > 0)
                .map(getResultWithValue())
                .collect(Collectors.joining());
    }

    private static Function<Map.Entry<Result, Integer>, String> getResultWithValue() {
        return entry -> entry.getValue().toString() + entry.getKey().getDescription();
    }

    private String extractCards(final Player player) {
        return player.getCardHand()
                .getCards()
                .stream()
                .map(Card::getName)
                .collect(Collectors.joining());
    }

    public String getName() {
        return name;
    }

    public String getCards() {
        return cards;
    }

    public String getResult() {
        return result;
    }

    public int getCardSum() {
        return cardSum;
    }
}
