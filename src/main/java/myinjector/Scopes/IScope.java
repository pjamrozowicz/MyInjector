package myinjector.Scopes;


import myinjector.AbstractSettings;

public interface IScope {
    public <T> T getInstance(Class clazz, AbstractSettings settings);
}
