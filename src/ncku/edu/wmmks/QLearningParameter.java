package ncku.edu.wmmks;

/**
 * Q learning parameter.
 * @version 1.0 2017/07/30
 * @author ALEX-CHUN-YU
 */
class QLearningParameter {

    /**
     * state scale.
     */
    static int maxStateNum = 4;

    /**
     * action scale.
     */
    static int maxActionNum = 2;

    /**
     * learning rate.
     */
    static double alpha = 0.4;

    /**
     * discount factor.
     */
    static double lambda = 0.9;

    /**
     * Train count.
     */
    static int episode = 10;

    /**
     * jump other node probability and avoid cycle.
     */
    static double jumpProb = 0.05;

}
