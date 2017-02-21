package myinjector.Scopes;

import myinjector.ComponentBuilder;

public interface IScope {
    <T> T getInstance(ComponentBuilder componentBuilder);
}
