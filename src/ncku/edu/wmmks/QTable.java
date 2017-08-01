package ncku.edu.wmmks;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * q-table format.
 * @version 1.0 2017/07/30
 * @author ALEX-CHUN-YU
 */
class QTable {

    /**
     * state and action score.
     */
    Hashtable<StateActionPair, Double> actionScore;

    /**
     * Constructor.
     */
    QTable(ArrayList<State> states, ArrayList<Action> actions) {
        initQTable(states, actions);
    }

    /**
     * Q-table init.
     */
    private void initQTable(ArrayList<State> states, ArrayList<Action> actions) {
        actionScore = new Hashtable<>();
        for (State state:states) {
            for (Action action:actions) {
              actionScore.put(new StateActionPair(state, action), 0.0);
            }
        }
    }

    /**
     * show q-table
     */
    void showQTable() {
        for(StateActionPair stateActionPair:actionScore.keySet()) {
            System.out.println(stateActionPair.toString() + " 分數:"
                    + actionScore.get(stateActionPair));
        }
    }

    /**
     * update q-table score.
     */
    void updateQTableScore(StateActionPair stateActionPair, double updateScore) {
        actionScore.put(stateActionPair, updateScore);
    }

    /**
     * get action score.
     */
    double getActionScore(StateActionPair stateActionPair) {
        return actionScore.get(stateActionPair);
    }

}
