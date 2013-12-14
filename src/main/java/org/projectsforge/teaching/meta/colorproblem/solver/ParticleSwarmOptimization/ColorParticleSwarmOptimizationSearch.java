package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import com.beust.jcommander.Parameter;
import org.projectsforge.teaching.meta.problem.Problem;
import org.projectsforge.teaching.meta.problem.Solver;

import java.util.Random;


public class ColorParticleSwarmOptimizationSearch implements Solver {

    private Problem problem;

    @Parameter(names = "-momentum", description = "inertia strength of particles")
    public double momentum = 1;

    @Parameter(names = "-bestLocalWeight", description = "weight of the best local solution")
    public double bestLocalWeight = 1;

    @Parameter(names = "-bestGlobalWeight", description = "weight of the best global solution")
    public double bestGlobalWeight = 1;

    @Parameter(names = "-nbParticles", description = "number of particles")
    public int nbParticles = 100;

    /**
     * The improvement mark.
     */
    @Parameter(names = "-improvementmark", description = "Improvement mark")
    public String improvementMark = ".";

    private Particle[] particles;

    private Problem.Solution best;

    private Random rand;

    public ColorParticleSwarmOptimizationSearch() {
        best = null;
        rand = new Random();
    }

    @Override
    public void run() {
        // initialization
        particles = new Particle[nbParticles];
        for (int i = 0; i < nbParticles; i++) {
            particles[i] = new Particle(problem.newRandomSolution(), newRandomSpeed());
            if (best == null || best.getFitness() > particles[i].getSolution().getFitness()) {
                best = particles[i].getSolution().copy();
                System.out.print(improvementMark);
            }
        }
        System.out.print("!");
        // run
        while (!problem.shouldStop()) {

            for (int i = 0; i < nbParticles; i++) {
                particles[i].calculateNewSpeed(momentum, bestLocalWeight, bestGlobalWeight, best);
                if (particles[i].applySpeed() &&
                        best.getFitness() > particles[i].getBestLocalSolution().getFitness()) {
                    best = particles[i].getSolution().copy();
                    System.err.print(improvementMark);
                }
            }
        }
        System.out.println();
    }

    @Override
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    private int[] newRandomSpeed() {
        int[] result = new int[3];
        for (int i = 0; i < result.length; i++) {
            result[i] = rand.nextInt(255);
        }
        return result;
    }
}
