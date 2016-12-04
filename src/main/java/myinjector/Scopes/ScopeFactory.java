package myinjector.Scopes;


public class ScopeFactory {
    public static IScope getScope(ScopeEnum scopeEnum){
        switch (scopeEnum){
            case SINGLETON:
                return new Singleton();
            case PROTOTYPE:
                return new Prototype();
            default:
                return new Prototype();
        }
    }
}
