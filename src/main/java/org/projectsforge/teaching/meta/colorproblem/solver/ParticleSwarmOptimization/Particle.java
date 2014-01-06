package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import org.projectsforge.teaching.meta.problem.Problem;

import java.util.Random;

public class Particle {
    private Problem.Solution solution;
    private Problem.Solution bestLocalSolution;
    private double[] speed;
    private Random rand;

    public Particle(Problem.Solution solution) {
        this.solution = solution;
        bestLocalSolution = solution.copy();
        rand = new Random();
        //newRandomSpeed();
        newZeroSpeed();
    }

    public Problem.Solution getBestLocalSolution() {
        return bestLocalSolution;
    }

    public Problem.Solution getSolution() {
        return solution;
    }

    public double[] getSpeed() {
        return speed;
    }

    public void setSpeed(double[] speed) {
        this.speed = speed;
    }

    public void calculateNewSpeed(final double momentum, final double localW, final double[] globalW,  final Problem.Solution[] bestGlobalSolution) {
        for (int i = 0; i < speed.length; i++) {
            speed[i] = (int) (momentum * speed[i]*2 * rand.nextDouble() +
                    rand.nextDouble() * localW * (bestLocalSolution.getValue(i) - solution.getValue(i))
            );
            for(int j = 0; j < globalW.length; j++){
                if(rand.nextBoolean()){
                    speed[i] += rand.nextDouble() * globalW[j] * (bestGlobalSolution[j].getValue(i) - solution.getValue(i));
                    break;
                }
            }
        }
    }

    public boolean applySpeed() {
        for (int i = 0; i < speed.length; i++) {
            int value = (int) (solution.getValue(i) + speed[i]);
            while(value<0 || value>255){
                if (value < 0) {
                    //value = 255 + value;          // espace infini (les extremités sont connectées)
                    value = 0 -  value;             // espace fini (rebond sur le bords)
                } else if (value > 255) {
                    //value = 0 - (255 - value);    // espace infini (les extremités sont connectées)
                    value = 255 +  (255 - value);   // espace fini (rebond sur le bords)
                }
            }
            solution.setValue(i, value);
        }
        if (bestLocalSolution.getFitness() > solution.getFitness()) {
            bestLocalSolution = solution.copy();
            return true;
        }
        return false;
    }

    private void newRandomSpeed() {
        speed = new double[solution.size()];
        for (int i = 0; i < speed.length; i++) {
            speed[i] = rand.nextDouble()*255;
        }
    }

    private void newZeroSpeed(){
        speed = new double[solution.size()];
        for (int i = 0; i < speed.length; i++) {
            speed[i] = 0;
        }
    }
}