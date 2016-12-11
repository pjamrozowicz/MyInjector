package myinjector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractSettings {

    private Map<Class, List<BindingInfo>> bindings;

    public AbstractSettings() {
        this.bindings = new HashMap<Class, List<BindingInfo>>();
        load();
    }

    public abstract void load();

    protected BindingInfo addBinding(Class sourceClass, Class outputClass){
            // @TODO: check if outputClass isn't abstract or interface
            BindingInfo bindingInfo = new BindingInfo(outputClass);
            List<BindingInfo> bindingInfoList;
            if(bindings.containsKey(sourceClass)){
                bindingInfoList   = bindings.get(sourceClass);
            } else {
                bindingInfoList = new LinkedList<BindingInfo>();
            }
            bindingInfoList.add(bindingInfo);
            this.bindings.put(sourceClass, bindingInfoList);
            return bindingInfo;
    }

    public BindingInfo getBindingInfo(Class sourceClass){
        List<BindingInfo> bindingInfoList = getAllBindingInfos(sourceClass);
        if(bindingInfoList != null){
            return bindingInfoList.get(0);
        }
        return null;
    }

    public BindingInfo getNamedBindingInfo(Class sourceClass, String name){
        System.out.println("Getting named binding info for class " + sourceClass.getName() + " and string " + name);
        List<BindingInfo> bindingInfoList = getAllBindingInfos(sourceClass);
        for (BindingInfo bindingInfo:bindingInfoList) {
            System.out.println("Checking binding info: " + bindingInfo.getName());
            if(bindingInfo.getName() != null){
                if(bindingInfo.getName().equals(name)){
                    System.out.println("I found binding!");
                    return bindingInfo;
                }
            }
        }
        System.out.println("Returning null");
        return null; //@TODO replace with: display warning there is no such binding
    }

    private List<BindingInfo> getAllBindingInfos(Class sourceClass){
       return bindings.get(sourceClass);
    }

}
