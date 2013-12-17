package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import com.beust.jcommander.Parameter;
import org.projectsforge.teaching.meta.problem.Problem;
import org.projectsforge.teaching.meta.problem.Solver;

import java.util.Random;


public class ColorParticleSwarmOptimizationSearch implements Solver {

    private Problem problem;

    // parameter for normal particle swarm optimization
    @Parameter(names = "-momentum", description = "inertia strength of particles")
    public double momentum = 0.8;

    @Parameter(names = "-bestLocalWeight", description = "weight of the best local solution")
    public double bestLocalWeight = 0.5;

    @Parameter(names = "-bestGlobalWeight", description = "weight of the best global solution")
    public double bestGlobalWeight = 0.9;

    @Parameter(names = "-nbParticles", description = "number of particles")
    public int nbParticles = 10000;

    // parameter for decrease addition
    @Parameter(names = "-decrease", description = "activation of decease addition")
    public boolean decrease = true;

    @Parameter(names = "-decreaseRate", description = "rate of decrease of other parameters")
    public double decreaseRate = 3;

    // parameter for display
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
            particles[i] = new Particle(problem.newRandomSolution());
            if (best == null || best.getFitness() > particles[i].getSolution().getFitness()) {
                best = particles[i].getSolution().copy();
                System.out.print(improvementMark);
            }
        }
        int iii = 0;
        System.out.print("!");
        // run
        while (!problem.shouldStop()) {
            iii++;

            for (int i = 0; i < nbParticles; i++) {
                particles[i].calculateNewSpeed(momentum, bestLocalWeight, bestGlobalWeight, best);
                if (particles[i].applySpeed() &&
                        best.getFitness() > particles[i].getBestLocalSolution().getFitness()) {
                    best = particles[i].getSolution().copy();
                    System.err.print(improvementMark);
                }
            }
            if (decrease) {
                //  bestGlobalWeight = bestGlobalWeight - (bestGlobalWeight * 100 / decreaseRate);
                //  bestLocalWeight = bestLocalWeight - (bestLocalWeight * 100 / decreaseRate);
                momentum = momentum - (momentum * 100 / decreaseRate);
            }
        }
        System.out.println(iii);
    }

    @Override
    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
