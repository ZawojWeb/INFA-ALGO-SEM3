package eu.jpereira.trainings.designpatterns.creational.singleton;

public enum ReportBuilderEnumSingleton implements ReportBuilderInterfaceForEnum {
    INSTANCE;

    @Override
    public ReportBuilderEnumSingleton getEnumInstance() {
        return INSTANCE;
    }
}