package gmbs.view;

import gmbs.domain.card.Card;
import gmbs.domain.card.Cards;
import gmbs.domain.game.BlackjackGame;
import gmbs.domain.game.GameResult;
import gmbs.domain.game.MatchResult;
import gmbs.domain.participant.Dealer;
import gmbs.domain.participant.Participant;
import gmbs.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String JOIN_DELIMITER = ", ";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    private OutputView() {

    }

    public static void printInitialCards(Dealer dealer, List<Player> players) {
        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), getPlayerNames(players));
        System.out.printf("%s: %s%n", dealer.getName(), getCardName(dealer.getCards().getValue().get(0)));
        for (Player player : players) {
            printCards(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardNames(Cards cards) {
        return cards.getValue().stream()
                .map(OutputView::getCardName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardName(Card card) {
        return card.getDenomination().getName() + card.getCardPattern().getName();
    }

    public static void printCards(Participant player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardNames(player.getCards()));
    }

    public static void printDealerDrawInfo() {
        System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DRAW_STANDARD);
    }

    public static void printCardsResult(Dealer dealer, List<Player> players) {
        System.out.println();
        printCardResult(dealer);
        for (Player player : players) {
            printCardResult(player);
        }
    }

    private static void printCardResult(Participant participant) {
        System.out.printf("%s 카드: %s - 결과: %d%n",
                participant.getName(), getCardNames(participant.getCards()), participant.getCards().sum());
    }

    public static void printGameResult(GameResult gameResult) {
        Map<Player, MatchResult> map = gameResult.getGameResult();

        System.out.printf("%n%s: %d승 %d무 %d패%n"
                , BlackjackGame.DEALER_NAME
                , gameResult.calculateDealerMatchResultCount(MatchResult.WIN)
                , gameResult.calculateDealerMatchResultCount(MatchResult.PUSH)
                , gameResult.calculateDealerMatchResultCount(MatchResult.LOSE)
        );

        for (Player player : map.keySet()) {
            System.out.printf("%s: %s%n", player.getName(), gameResult.getMatchResult(player).getValue());
        }
    }

    public static void printException(Exception e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());
    }
}
