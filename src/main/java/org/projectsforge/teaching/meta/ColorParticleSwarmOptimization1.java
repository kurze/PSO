package org.projectsforge.teaching.meta;

import org.projectsforge.teaching.meta.master.Master;

public class ColorParticleSwarmOptimization1 {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        final String[] myArgs = new String[]{
                "-problem", "org.projectsforge.teaching.meta.colorproblem.problem.ColorProblem",
                "-instance", "10-1",
                "-maxevaluations", "40000",
                "-runCount", "20",
                "-solver", "org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization.ColorParticleSwarmOptimizationSearch",
                "-output", "swarm-10-1",
                "-detailedoutput", "false",
                "-momentum", "0.7298",
                "-bestLocalWeight", "1.49618",
                "-bestGlobalWeight", "1.49618",
                "-nbParticles", "1600",
                "-decreaseRate", "0.3",
                "-decrease", "true"

        };

        Master.run(myArgs);
    }

}