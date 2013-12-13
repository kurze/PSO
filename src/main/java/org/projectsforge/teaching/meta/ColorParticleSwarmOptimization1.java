package org.projectsforge.teaching.meta;

import org.projectsforge.teaching.meta.master.Master;

/**
 * Created by kurze on 12/13/13.
 */
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
                "-maxevaluations", "10000",
                "-runCount", "50",
                "-solver", "org.projectsforge.teaching.meta.colorproblem.solver.ParticleSwarmOptimization.ColorParticleSwarmOptimizationSearch",
                "-output", "particle-10-1",
                "-detailedoutput", "false",
                "-maxneightborhoodtries", "100"
        };

        Master.run(myArgs);
    }

}