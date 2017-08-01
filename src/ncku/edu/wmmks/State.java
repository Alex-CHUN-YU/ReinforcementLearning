package ncku.edu.wmmks;

/**
 * Reinforcement Learning State.
 * @version 1.0 2017/07/30
 * @author ALEX-CHUN-YU
 */
class State {

    /**
     * state name.
     */
    private String stateName;

    /**
     * state number.
     */
    private int stateNum;

    /**
     * constructor.
     * @param stateName state name
     * @param stateNum state number
     */
    State(String stateName, int stateNum) {
        this.stateName = stateName;
        this.stateNum = stateNum;
    }

    /**
     * set state name.
     * @param stateName set state name
     */
     void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * set state number.
     * @param stateNum set state number
     */
     void setStateNum(int stateNum) {
        this.stateNum = stateNum;
    }

    /**
     * get state name
     * @return get state number
     */
     String getStateName() {
        return this.stateName;
    }

    /**
     * get state number.
     * @return get state number
     */
     int getStateNum() {
        return this.stateNum;
    }

    /**
     * 字串型態表示 state name add state number.
     * @return state name add state number
     */
    @Override
    public String toString() {
        return this.stateName + " " + this.stateNum;
    }

    /**
     * 判斷是否物件型態相同，且 state name and state number 也必須是一樣的值
     * @param obj state
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        // 只要同一個物件型態，且值相同即可(較為寬鬆) !
        try {
            if (obj.getClass().equals(this.getClass())) {
                State stateObj = (State) obj;
                if (stateObj.getStateName().equals(this.stateName) &&
                        (stateObj.getStateNum() == this.stateNum)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.print("Error:" + e.toString());
        }
        return false;
        // 只會判斷，是否為同一個物件(較為嚴謹) !
        //return super.equals(obj);
    }

}
