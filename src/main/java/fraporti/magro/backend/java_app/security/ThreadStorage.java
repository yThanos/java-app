package fraporti.magro.backend.java_app.security;

import java.util.Map;

public class ThreadStorage {
    private static final String IP = "ip";

    private static final InheritableThreadLocal<Map<String, Object>> threadStorage = new InheritableThreadLocal<>();

    private static Map<String, Object> storage(){
        Map<String, Object> map = threadStorage.get();
        if(map == null){
            map = new java.util.HashMap<>();
            threadStorage.set(map);
        }
        return map;
    }

    public static void setItem(final String key, final Object value){
        if(key != null){
            if(value == null){
                storage().remove(key);
            } else {
                storage().put(key, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getItem(String key){
        if(key != null){
            if(storage().containsKey(key)){
                return (T) storage().get(key);
            }
        }
        return null;
    }

    public static void clear(){
        storage().clear();
        threadStorage.remove();
    }

    public static void setIp(String ip){
        setItem(IP, ip);
    }

    public static String getIp(){
        return getItem(IP);
    }
}
