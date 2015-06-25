package io.github.nejc92.mcts;

import io.github.nejc92.mcts.examples.TicTacToePlayer;
import io.github.nejc92.mcts.examples.TicTacToeState;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MctsTest {

    private static final int NUMBER_OF_ITERATIONS = 600;
    private static final int NUMBER_OF_GAMES = 100;
    private static final double EXPLORATION_PARAMETER = 0.4;

    private Mcts<TicTacToeState, String, TicTacToePlayer> mcts = new Mcts<>(NUMBER_OF_ITERATIONS);
    private TicTacToePlayer.Type playerToBegin = TicTacToePlayer.Type.NOUGHT;

    @Test
    public void testUctSearch() {
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            TicTacToeState state = new TicTacToeState(playerToBegin);
            playOneTicTacToeGame(state);
            assertTrue(state.isDraw());
            switchPlayerOrder();
        }
    }

    private void playOneTicTacToeGame(TicTacToeState state) {
        while (!state.isTerminal()) {
            String nextAction = mcts.uctSearch(state, EXPLORATION_PARAMETER);
            state.performActionForCurrentAgent(nextAction);
        }
    }

    private void switchPlayerOrder() {
        switch (playerToBegin) {
            case NOUGHT:
                playerToBegin = TicTacToePlayer.Type.CROSS;
                break;
            case CROSS:
                playerToBegin = TicTacToePlayer.Type.NOUGHT;
        }
    }
}