package org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization;

import com.beust.jcommander.Parameter;
import org.projectsforge.teaching.meta.problem.Problem;
import org.projectsforge.teaching.meta.problem.Solver;


public class ColorParticleSwarmOptimizationSearch implements Solver {

    private Problem problem;

    @Parameter(names = "-momentum", description = "inertia strength of particles")
    public int momentum = 1;

    @Parameter(names = "-BestLocalWeight", description = "weight of the best local solution")
    public int bestLocalWeight = 1;

    @Parameter(names = "-BestGlobalWeight", description = "weight of the best global solution")
    public int bestGlobalWeight = 1;

    @Parameter(names = "-particles", description = "number of particles")
    public int nbParticles = 100;

    @Override
    public void run() {

    }

    @Override
    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
