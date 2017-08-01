package ncku.edu.wmmks;

/**
 * Reinforcement Learning Action.
 * @version 1.0 2017/07/30
 * @author ALEX-CHUN-YU
 */
class Action {

    /**
     * action name.
     */
    private String actionName;

    /**
     * action number.
     */
    private int actionNum;

    /**
     * constructor.
     * @param actionName action name
     * @param actionNum action number
     */
    Action(String actionName, int actionNum) {
        this.actionName = actionName;
        this.actionNum = actionNum;
    }

    /**
     * set action name.
     * @param actionName set action name
     */
    void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * set action number.
     * @param actionNum set action number
     */
    void setActionNum(int actionNum) {
        this.actionNum = actionNum;
    }

    /**
     * get action name
     * @return get action number
     */
    String getActionName() {
        return this.actionName;
    }

    /**
     * get action number.
     * @return get action number
     */
    int getActionNum() {
        return this.actionNum;
    }

    /**
     * 字串型態表示 action name add action number.
     * @return action name add action number
     */
    @Override
    public String toString() {
        return this.actionName + " " + this.actionNum;
    }

    /**
     * 判斷是否物件型態相同，且 action name and action number 也必須是一樣的值
     * @param obj action
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        // 只要同一個物件型態，且值相同即可(較為寬鬆) !
        try {
            if (obj.getClass().equals(this.getClass())) {
                Action actionObj = (Action) obj;
                if (actionObj.getActionName().equals(this.actionName) &&
                        (actionObj.getActionNum() == this.actionNum)) {
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
