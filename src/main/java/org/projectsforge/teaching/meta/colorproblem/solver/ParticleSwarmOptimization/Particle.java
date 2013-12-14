package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import org.projectsforge.teaching.meta.problem.Problem;

import java.util.Random;

public class Particle {
    private Problem.Solution solution;
    private Problem.Solution bestLocalSolution;
    private int[] speed;
    Random rand;

    public Particle(Problem.Solution solution, int[] speed) {
        this.solution = solution;
        bestLocalSolution = solution.copy();
        this.speed = speed;
        rand = new Random();
    }

    public Problem.Solution getBestLocalSolution() {
        return bestLocalSolution;
    }

    public Problem.Solution getSolution() {
        return solution;
    }

    public int[] getSpeed() {
        return speed;
    }

    public void setSpeed(int[] speed) {
        this.speed = speed;
    }

    public void calculateNewSpeed(final double momentum, final double localW, final double globalW, final Problem.Solution bestGlobalSolution) {
        for (int i = 0; i < speed.length; i++) {
            speed[i] = (int) (momentum * speed[i] +
                    rand.nextInt() / Integer.MAX_VALUE * localW * bestLocalSolution.getValue(i) +
                    rand.nextInt() / Integer.MAX_VALUE * globalW * bestGlobalSolution.getValue(i));
        }
    }

    public boolean applySpeed() {
        for (int i = 0; i < speed.length; i++) {
            int value = solution.getValue(i) + speed[i];
            if (value < 0) {
                value = 0;
            } else if (value > 255) {
                value = 255;
            }
            solution.setValue(i, value);
        }
        if (bestLocalSolution.getFitness() > solution.getFitness()) {
            bestLocalSolution = solution.copy();
            return true;
        }
        return false;
    }
}