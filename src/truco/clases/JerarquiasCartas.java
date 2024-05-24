package truco.clases;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface JerarquiasCartas {
    Map<Integer, Map<String, List<String>>> JERARQUIA = new HashMap<>() {{
        put(15, new HashMap<>() {{
            put("1", new LinkedList<>(List.of("Espada")));
        }});
        put(14, new HashMap<>() {{
            put("1", new LinkedList<>(List.of("Basto")));
        }});
        put(13, new HashMap<>() {{
            put("7", new LinkedList<>(List.of("Espada")));
        }});
        put(12, new HashMap<>() {{
            put("7", new LinkedList<>(List.of("Oro")));
        }});
        put(11, new HashMap<>() {{
            put("3", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(10, new HashMap<>() {{
            put("2", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(9, new HashMap<>() {{
            put("1", new LinkedList<>(List.of("Oro", "Copa")));
        }});
        put(8, new HashMap<>() {{
            put("12", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(7, new HashMap<>() {{
            put("11", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(6, new HashMap<>() {{
            put("10", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(5, new HashMap<>() {{
            put("7", new LinkedList<>(List.of("Basto", "Copa")));
        }});
        put(4, new HashMap<>() {{
            put("6", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(3, new HashMap<>() {{
            put("5", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
        put(2, new HashMap<>() {{
            put("4", new LinkedList<>(List.of("Oro", "Basto", "Espada", "Copa")));
        }});
    }};













}
