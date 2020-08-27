package br.com.uds.vendaacai.util;

import br.com.uds.vendaacai.constants.URLConstants;
import br.com.uds.vendaacai.model.dto.LinkDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkUtil {

    static List<LinkDTO> links;

    public static List<LinkDTO> adicionarLinks(Map<String, String> urls) {
        links = new ArrayList<>();
        for (Map.Entry<String, String> e : urls.entrySet()) {
            links.add(new LinkDTO(e.getKey(), e.getValue()));
        }
        return links;
    }

    public static Map<String, String> montarURL(String rel, String href) {
        Map<String, String> urls = new HashMap<>();
        urls.put(rel, href);
        return urls;
    }
}
