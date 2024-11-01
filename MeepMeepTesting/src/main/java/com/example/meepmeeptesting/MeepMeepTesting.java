package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-23.5, -60, Math.toRadians(-90)))
                        .lineToConstantHeading(new Vector2d(-5, -35)) //to front of clip
                        .waitSeconds(1) //bar clippos
                        .lineToConstantHeading(new Vector2d(-5, -33)) // to lcip pos
                        .waitSeconds(0.5) //bar upclippos
                        .lineToConstantHeading(new Vector2d(-5, -35))
                        .waitSeconds(0.3) // claw open
                        .waitSeconds(0.5) // bar ground
                        .splineToSplineHeading(new Pose2d(-48, -37, Math.toRadians(90)), Math.toRadians(90)) // 1st neutral sample
                        .waitSeconds(0.2) // claw closed
                        .lineToSplineHeading(new Pose2d(-53, -53, Math.toRadians(45))) //to bucket
                        .waitSeconds(1) // Bar up and claw open
                        .waitSeconds(1) //bar ground
                        .lineToLinearHeading(new Pose2d(-58, -37, Math.toRadians(90))) // 2nd neutral sample
                        .waitSeconds(0.2) // claw closed
                        .lineToSplineHeading(new Pose2d(-53, -53, Math.toRadians(45))) //to bucket
                        .waitSeconds(1) // Bar up and claw open
                        .waitSeconds(1) // bar ground
                        .lineToSplineHeading(new Pose2d(-57, -33, Math.toRadians(140))) //3rd neutral sample
                        .waitSeconds(0.2) //claw closed
                        .lineToSplineHeading(new Pose2d(-53, -53, Math.toRadians(45))) //to bucket
                        .waitSeconds(1) // Bar up and claw open
                        .waitSeconds(1) // bar ground
                        .lineToLinearHeading(new Pose2d(-60, -45, Math.toRadians(0)))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}