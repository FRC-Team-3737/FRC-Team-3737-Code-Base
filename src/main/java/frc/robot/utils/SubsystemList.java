package frc.robot.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SubsystemList {

    private final Map<String, SubsystemBase> m_map = new LinkedHashMap<>();
    
    public SubsystemList(SubsystemBase[] subsystems) {
        for (SubsystemBase subsystem : subsystems) {
            String name = subsystem.getName();
            m_map.put(name, subsystem);
        }
    }

    public SubsystemBase getSubsystem(String name) {
        return m_map.get(name);
    }
    
}
