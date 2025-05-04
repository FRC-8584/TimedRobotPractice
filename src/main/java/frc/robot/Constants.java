package frc.robot;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.controls.StaticBrake;

public class Constants {
    public class Stat{

        static final double Rotation_Rate = 0.73266666;
        static final double Height_Init = 0.0;
        static final double SafeAngle = 15.0;
        static final double SafeHeight = 60.0;
        
    };

    public class ID {
        
        static final int Mecanum_Left_Front_ID  = 1;
        static final int Mecanum_Right_Front_ID = 2;
        static final int Mecanum_Right_Rear_ID  = 3;
        static final int Mecanum_Left_Rear_ID   = 4;

        static final int Elevator_Left_ID       = 5;
        static final int Elevator_Right_ID      = 6;

        static final int Claw_Shaft_ID          = 7;
        static final int Claw_Power_Id          = 8;

        static final int Coral_Intake_ID        = 9;
                
    }
}
