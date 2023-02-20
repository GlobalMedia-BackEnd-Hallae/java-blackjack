package gmbs.controller;

import gmbs.dto.PlayerDTO;
import gmbs.dto.ResultDTO;
import gmbs.model.*;
import gmbs.model.players.Player;
import gmbs.model.vo.UserInput;
import gmbs.view.Input;
import gmbs.view.Output;

import java.util.Arrays;
import java.util.List;

public class Controller {

    private static final String SPLIT_VALUE = ",";
    private final Input input = new Input();
    private final Output output = new Output();
    private final HandCalculator calculator = new HandCalculator();
    private BlackJackManager manager;

    public void playBlackJack() {
        manager = createManager();
        Player dealer = manager.createDealer();
        List<Player> users = requestUsers();
        showFirstStatus(users, dealer);
        users = usersHit(users);
        dealer = dealerHit(dealer);
        WinnerChecker checker = new WinnerChecker(dealer);
        List<ResultDTO> userResults = getUserResults(checker, users);
        ResultDTO dealerResult = new ResultDTO(dealer, checker.getTargetResult());
        showResult(userResults, dealerResult);
    }

    private BlackJackManager createManager() {
        Deck deck = new Deck(new CardGenerator().createWholeCards());
        return new BlackJackManager(deck);
    }

    private List<Player> requestUsers() {
        output.startDisplay();
        return createUserNames()
                .stream()
                .map(name -> (Player) manager.createUser(name))
                .toList();
    }

    private List<UserName> createUserNames() {
        return Arrays.stream(requestUserNames())
                .map(UserName::new)
                .toList();
    }

    private String[] requestUserNames() {
        return input.scan()
                .split(SPLIT_VALUE);
    }

    private void showFirstStatus(final List<Player> users, final Player dealer) {
        output.showPlayerInfo(PlayerDTO.oneCardOf(dealer));
        users.forEach(user -> output.showPlayerInfo(PlayerDTO.allCardOf(user)));
    }

    private List<Player> usersHit(final List<Player> users) {
        return users.stream()
                .map(this::chooseHit)
                .toList();
    }

    private Player chooseHit(final Player user) {
        Player afterHit = user;
        while (calculator.canHit(afterHit) && isYes(afterHit)) {
            afterHit = manager.hit(afterHit);
            output.showPlayerInfo(PlayerDTO.allCardOf(afterHit));
        }
        return afterHit;
    }

    private boolean isYes(final Player user) {
        output.hitDisplay(user.getName());
        UserInput userInput = new UserInput(input.scan());
        return userInput.isYes();
    }

    private Player dealerHit(final Player dealer) {
        Player afterHit = dealer;
        while (calculator.canHit(afterHit)) {
            afterHit = manager.hit(afterHit);
            output.showDealerHit();
        }
        return afterHit;
    }

    private List<ResultDTO> getUserResults(final WinnerChecker checker, final List<Player> users) {
        return users.stream()
                .map(user -> new ResultDTO(user, checker.getResult(user)))
                .toList();
    }

    private void showResult(final List<ResultDTO> users, final ResultDTO dealer) {
        users.forEach(output::showResultStatus);
        output.showResultStatus(dealer);
        users.forEach(output::showResult);
        output.showResult(dealer);
    }
}
