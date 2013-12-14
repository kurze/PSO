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
                //"-maxevaluations", "10000",
                "-runCount", "8",
                "-solver", "org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization.ColorParticleSwarmOptimizationSearch",
                "-output", "swarm-10-1",
                "-detailedoutput", "false",
                "-momentum", "0.8",
                "-bestLocalWeight", "0.5",
                "-bestGlobalWeight", "0.9",
                "-nbParticles", "10000"
        };

        Master.run(myArgs);
    }

}