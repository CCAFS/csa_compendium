package org.cgiar.ccafs.csa.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

import javax.faces.context.FacesContext;
import java.util.Iterator;
import java.util.Map;

/**
 * This uses the FacesContext view map as a data store for a scope in the Spring
 * Framework. More simply this is a view scope implementation that works in spring
 */
public class ViewScope implements Scope {

    public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public synchronized Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Map lruMap = (Map) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("com.sun.faces.application.view.activeViewMaps");
            if (lruMap != null && !lruMap.isEmpty() && lruMap.size() > 1) {

                Iterator itr = lruMap.entrySet().iterator();
                Map.Entry entry = (Map.Entry) itr.next();
                Map<String, Object> map = (Map<String, Object>) entry.getValue();
                map.clear();
                itr.remove();
            }
            Object object = objectFactory.getObject();
            viewMap.put(name, object);
            return object;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object remove(String name) {
        Object instance = FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
        if (instance == null) {
            Map<String, Runnable> callbacks = (Map<String, Runnable>) FacesContext.getCurrentInstance().
                    getViewRoot().getViewMap().get(VIEW_SCOPE_CALLBACKS);
            if (callbacks != null) callbacks.remove(name);
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        Map<String, Runnable> callbacks = (Map<String, Runnable>) FacesContext.getCurrentInstance().
                getViewRoot().getViewMap().get(VIEW_SCOPE_CALLBACKS);
        if (callbacks != null)
            callbacks.put(name, callback);
    }

    @Override
    public String getConversationId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
        return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
    }

    @Override
    public Object resolveContextualObject(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
        return facesRequestAttributes.resolveReference(key);
    }
}
