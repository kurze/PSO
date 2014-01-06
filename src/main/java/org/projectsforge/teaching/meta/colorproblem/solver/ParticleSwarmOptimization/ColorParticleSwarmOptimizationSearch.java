package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import com.beust.jcommander.Parameter;
import org.projectsforge.teaching.meta.problem.Problem;
import org.projectsforge.teaching.meta.problem.Solver;

import java.util.Random;


public class ColorParticleSwarmOptimizationSearch implements Solver {

    private Problem problem;

    // parameter for normal particle swarm optimization
    @Parameter(names = "-momentum", description = "inertia strength of particles")
    public double momentum = 0.7298;

    @Parameter(names = "-bestLocalWeight", description = "weight of the best local solution")
    public double bestLocalWeight = 1.49618;

    @Parameter(names = "-bestGlobalWeight", description = "weight of the best global solution")
    public double bestGlobalWeight = 1.49618;

    @Parameter(names = "-nbParticles", description = "number of particles")
    public int nbParticles = 1000;

    // parameter for decrease addition
    @Parameter(names = "-decrease", description = "activation of decease addition")
    public boolean decrease = false;

    @Parameter(names = "-decreaseRate", description = "rate of decrease of other parameters")
    public double decreaseRate = 3;

    // parameter for display
    @Parameter(names = "-improvementmark", description = "Improvement mark")
    public String improvementMark = ".";

    private Particle[] particles;

    private double[] globalWeight;

    private Problem.Solution[] best;


    private Random rand;

    public ColorParticleSwarmOptimizationSearch() {
        best = null;
        rand = new Random();
    }

    @Override
    public void run() {
        // initialization
        particles = new Particle[nbParticles];
        globalWeight = new double[5];
        best = new Problem.Solution[5];

        for(int i=0; i < globalWeight.length; i++){
            globalWeight[i] = bestGlobalWeight;// / ((i*2) + 1);
        }

        for (int i = 0; i < nbParticles; i++) {
            particles[i] = new Particle(problem.newRandomSolution());
            for(int j = 0; j < best.length; j++){
                if(best[j] == null || best[0].getFitness() > particles[i].getSolution().getFitness()) {
                    best[j] = particles[i].getSolution().copy();
                    if(j==0){System.out.print(improvementMark);}
                    break;
                }
            }
        }
        int nbMvt = 0;
        System.out.print("!");
        // run
        while (!problem.shouldStop()) {
            nbMvt++;

            for (int i = 0; i < nbParticles; i++) {
                particles[i].calculateNewSpeed(momentum, bestLocalWeight, globalWeight, best);
                if (particles[i].applySpeed()){
                    for(int j = 0; j < best.length; j++){
                        if(best[j].getFitness() > particles[i].getBestLocalSolution().getFitness()){
                            best[j] = particles[i].getSolution().copy();
                            if(j==0){System.err.print(improvementMark);}
                            break;
                        }
                    }
                }
            }
            if (decrease) {
                //bestGlobalWeight = bestGlobalWeight - (bestGlobalWeight * 100 / (100+decreaseRate));
                //bestLocalWeight = bestLocalWeight - (bestLocalWeight * 100 / (100+decreaseRate));
                momentum = momentum - (momentum * 100 / (100 + decreaseRate));
            }
        }
        System.out.println("!"+nbMvt);
    }

    @Override
    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
