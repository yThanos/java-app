package fraporti.magro.backend.java_app.util;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private static final ModelMapper mapper = new ModelMapper();

    public static <T> T map(Object source, Class<T> destinationType) {
        return mapper.map(source, destinationType);
    }

    public static <T> List<T> mapAll(List<?> source, Class<T> destinationType) {
        return source.stream().map(e -> map(e, destinationType)).collect(Collectors.toList());
    }
}
