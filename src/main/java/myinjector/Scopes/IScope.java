package myinjector.Scopes;


public interface IScope {
    public <T> T getInstance(Class clazz);
}
