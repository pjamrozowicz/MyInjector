package CyclicDependencyInMethodsAndFields;

import myinjector.Annotations.Inject;

public class Computer {
    ImportantPart importantPart;

    @Inject
    public void setImportantPart(ImportantPart importantPart){
        this.importantPart = importantPart;
    }
}
