package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import org.projectsforge.teaching.meta.problem.Problem;

public class Particle {
    private Problem.Solution solution;
    private Problem.Solution bestSolution;
    private int[] speed;

    public Particle(Problem.Solution solution, int[] speed) {
        this.solution = solution;
        this.speed = speed;
    }

    public Problem.Solution getBestSolution() {
        return bestSolution;
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

    public boolean applySpeed() {
        for (int i = 0; i < speed.length; i++) {
            int value = solution.getValue(i) + speed[i];
            solution.setValue(i, value);
        }
        if (bestSolution.getFitness() > solution.getFitness()) {
            bestSolution = solution;
            return false;
        }
        return true;
    }
}