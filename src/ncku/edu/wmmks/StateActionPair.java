package ncku.edu.wmmks;

/**
 * Reinforcement Learning State and Action Pair.
 * @version 1.0 2017/07/30
 * @author ALEX-CHUN-YU
 */
class StateActionPair {

    /**
     * event state.
     */
    State state;

    /**
     * event action.
     */
    Action action;

    /**
     * constructor.
     */
    StateActionPair() {
        this.state = null;
        this.action = null;
    }

    /**
     * constructor.
     * @param state state
     * @param action action
     */
    StateActionPair(State state, Action action) {
        this.state = state;
        this.action = action;
    }

    /**
     * set state.
     * @param state state
     */
    void setState(State state) {
        this.state = state;
    }

    /**
     * set action.
     */
    void setAction(Action action) {
        this.action = action;
    }

    /**
     * get state.
     */
    State getState() {
        return this.state;
    }

    /**
     * get action.
     */
    Action getAction() {
        return this.action;
    }

    /**
     * 字串型態表示 state add action.
     */
    @Override
    public String toString() {
        return this.state.toString() + " " + this.action.toString();
    }

    /**
     * 判斷是否物件型態相同，且 state and action 也必須是一樣的值
     * @param obj StateActionPair
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        // 只要同一個物件型態，且值相同即可(較為寬鬆) !
        try {
            if (obj.getClass().equals(this.getClass())) {
                StateActionPair stateActionPairObj = (StateActionPair)obj;
                if (stateActionPairObj.getState().equals(this.state)
                        && (stateActionPairObj.getAction().equals(this.action))) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
        }
        return false;
        // 只會判斷，是否為同一個物件(較為嚴謹) !
        //return super.equals(obj);
    }

    /**
     * hash code 必須存在，因為你以物件當 key 它若不存在會有 bug.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return (action.toString() + state.toString()).hashCode();
    }
}
