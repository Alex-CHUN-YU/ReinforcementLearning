package ncku.edu.wmmks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Reinforcement learning about Q learning.
 * @version 1.0 2017/07/30
 * @author ALEX-CHUN-YU
 */
class QLearning {

    /**
     * states.
     */
    private ArrayList<State> states;

    /**
     * actions.
     */
    private ArrayList<Action> actions;

    /**
     * whether finished or not.
     */
    private boolean isFinish;

    /**
     * current state.
     */
    private State currentState;

    /**
     * next state.
     */
    private State nextState;

    /**
     * current State Action Pair.
     */
    private StateActionPair currentStateActionPair;

    /**
     * random of fix form.
     */
    private Random rand = new Random(4);

    /**
     * establish q-table
     */
    private QTable qTable;

    /**
     * Constructor.
     */
    QLearning() {
        System.out.println("Using StartQLearning Function To Learning:");
    }

    /**
     * start q learning.
     */
    void StartQLearning() {
        int step; // 每次走幾次才會到終點
        int minStep = 0; // 最優化
        double currentActionReward;
        double terminalReward;
        double nextActionReward;
        double updateReward;
        // load state and action into q-table
        InitQLearning();
        qTable = new QTable(this.states, this.actions);
        qTable.showQTable();
        System.out.println("------------------------------------------------");
        for (int i = 0; i < QLearningParameter.episode; i++) {
            // 一切從零開始
            currentState = this.states.get(0);
            currentStateActionPair.setState(currentState);
            isFinish = false;
            step = 0;
            while (!isFinish) {
                currentActionReward = chooseMaxActionReward(); // 選擇一個最大的 action reward
                terminalReward = getFeedback(); //　檢查是否為最後一個 node
                nextActionReward = getNextMaxActionReward(nextState); //　下一個　node 選擇一個最大的 action reward
                updateReward = currentActionReward
                        + QLearningParameter.alpha * (terminalReward
                        + QLearningParameter.lambda * nextActionReward
                        - currentActionReward);
                updateQTable(currentStateActionPair, updateReward); // 更新 q table
                updateEnvironment(); // 更新為下一步之狀態
                step++;
            } //while (!isFinish)
            System.out.println("Round:" + i + " Cost Step:" + step);
            if (minStep > step || minStep == 0) {
                minStep = step;
            }
            qTable.showQTable();
            System.out.println("------------------------------------------------");
        } // for (int i = 0; i < QLearningParameter.episode; i++)
        System.out.println("最少可走到終點之步數:" + minStep);
    }

    /**
     * new a q-table and set parameter.
     */
    private void InitQLearning() {
        loadState();
        loadAction();
        currentStateActionPair = new StateActionPair();
    }

    /**
     * set states.
     */
    private void loadState() {
        states = new ArrayList<>();
        for (int i = 0; i < QLearningParameter.maxStateNum; i++) {
            states.add(new State(Integer.toString(i), i));
        }
    }

    /**
     * set actions.
     */
    private void loadAction() {
        actions = new ArrayList<>();
        for (int i = 0; i < QLearningParameter.maxActionNum; i++) {
            if (i == 0) {
                actions.add(new Action("left", i));
            } else {
                actions.add(new Action("right", i));
            }
        } //for (int i = 0; i < QLearningParameter.maxActionNum; i++)
    }

    /**
     * find current max action score.
     * @return max action score
     */
    private double chooseMaxActionReward() {
        if (rand.nextDouble() > QLearningParameter.jumpProb) {
            double maxScore = 0.0;
            Action maxScoreAction = actions.get(0);
            for (Action action:actions) {
                currentStateActionPair.setAction(action);
                if (qTable.getActionScore(currentStateActionPair) > maxScore) {
                    maxScore = qTable.getActionScore(currentStateActionPair);
                    maxScoreAction = action;
                }
            } // for (Action action:actions)
            if (maxScore == 0.0) {
                currentStateActionPair.setAction(
                        actions.get(rand.nextInt(QLearningParameter.maxActionNum)));
            } else {
                currentStateActionPair.setAction(maxScoreAction);
            }
        } else {
            currentStateActionPair.setAction(
                    actions.get(rand.nextInt(QLearningParameter.maxActionNum)));
        }
        return qTable.getActionScore(currentStateActionPair);
    }

    /**
     * 判斷是否為最後一個點 is last node?
     * @return yes or no
     */
    private double getFeedback() {
        nextState = getNextState(currentStateActionPair); // 走到下一個 state 的 state
        if(nextState.getStateNum() == QLearningParameter.maxStateNum - 1) { // state 為最後一個 node
            isFinish = true; // 結束迴圈
            return 1; // reward
        } else {
            return 0; // reward
        }
    }

    /**
     * get next step.
     * @param currentStateActionPair current state and action
     * @return next state.
     */
    private State getNextState(StateActionPair currentStateActionPair) {
        int stateIndex = currentStateActionPair.getState().getStateNum();
        int actionIndex = currentStateActionPair.getAction().getActionNum();
        if(actionIndex == 0) { // 向左
            stateIndex --; // 向左
            if(stateIndex == -1) { // index = 0 且向左邊
                stateIndex = 0;
            }
        } else { // 向右
            stateIndex ++;
        }
        return states.get(stateIndex);
    }

    /**
     * find next max action score.
     * @return max action score
     */
    private double getNextMaxActionReward(State state) {
        double maxScore = 0.0;
        StateActionPair stateActionPair = new StateActionPair();
        stateActionPair.setState(state);
        Action maxScoreAction = actions.get(0);
        for (Action action:actions) {
            stateActionPair.setAction(action);
            if (qTable.getActionScore(stateActionPair) > maxScore) {
                maxScore = qTable.getActionScore(stateActionPair);
                maxScoreAction = action;
            }
        } //for (Action action:actions)
        if (maxScore == 0.0) {
            stateActionPair.setAction(
                    actions.get(rand.nextInt(QLearningParameter.maxActionNum)));
        } else {
            stateActionPair.setAction(maxScoreAction);
        }
        return qTable.getActionScore(stateActionPair);
    }

    /**
     * renew q-table.
     * @param stateActionPair current stateActionPair
     * @param updateReward update q-table
     */
    private void updateQTable(StateActionPair stateActionPair, double updateReward) {
        qTable.updateQTableScore(stateActionPair, updateReward);
    }

    /**
     * update state.
     */
    private void updateEnvironment() {
        currentState = nextState;
        currentStateActionPair.setState(currentState);
    }

}
